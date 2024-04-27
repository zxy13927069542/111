package com.ying.tjava.web.framework;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet("/")
public class DispatchServlet extends HttpServlet {
    private Map<String, GetDispatcher> getMappings;
    private Map<String, PostDispatcher> postMappings;
    private String pkgName;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        GetDispatcher dispatcher = getMappings.get(url);
        if (dispatcher == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        ModelAndView mv = null;
        try {
            mv = dispatcher.invoke(req, resp);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.pkgName = "com.ying.tjava.web.controllers";
        scanGetInControllers();
        scanPostInControllers();
    }

    /**
     * 扫描指定包下所有以Controller结尾的类，并对其中@GetMapping注解标注的方法，添加进getMappings中
     *
     * @return
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    void scanGetInControllers() {
        getMappings = new HashMap<>();
        //  获取指定包下所有类的class对象
        Reflections reflections = new Reflections(pkgName, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);

        for (Class c : classes) {
            if (!c.getName().endsWith("Controller")) {
                continue;
            }

            for (Method m : c.getMethods()) {
                if (!m.isAnnotationPresent(GetMapping.class)) {
                    continue;
                }

                String url = m.getAnnotation(GetMapping.class).value();
                if (url == null || url.isEmpty()) {
                    continue;
                }
                GetDispatcher dispatcher = new GetDispatcher();
                try {
                    dispatcher.instance = c.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dispatcher.method = m;
                dispatcher.paramType = m.getParameterTypes();
                Parameter[] parameters = m.getParameters();
                dispatcher.paramName = new String[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    dispatcher.paramName[i] = parameters[i].getName();
                }

                getMappings.put(url, dispatcher);
            }
        }

    }

    Map<String, PostDispatcher> scanPostInControllers() {
        return null;
    }
}

class GetDispatcher {
    Object instance;
    Method method;
    String[] paramName;
    Class<?>[] paramType;

    public ModelAndView invoke(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        Object[] args = new Object[paramType.length];

        for (int i = 0; i < paramType.length; i++) {
            String parameterName = paramName[i];
            Class<?> parameterClass = paramType[i];
            if (parameterClass == HttpServletRequest.class) {
                args[i] = request;
            } else if (parameterClass == HttpServletResponse.class) {
                args[i] = response;
            } else if (parameterClass == HttpSession.class) {
                args[i] = request.getSession();
            } else if (parameterClass == int.class) {
                args[i] = Integer.valueOf(getNotNull(request, parameterName, "0"));
            } else if (parameterClass == long.class) {
                args[i] = Long.valueOf(getNotNull(request, parameterName, "0"));
            } else if (parameterClass == boolean.class) {
                args[i] = Boolean.valueOf(getNotNull(request, parameterName, "false"));
            } else if (parameterClass == String.class) {
                args[i] = getNotNull(request, parameterName, "");
            } else {
                throw new RuntimeException("Missing handler for type: " + parameterClass);
            }
        }
        return (ModelAndView) this.method.invoke(this.instance, args);
    }

    private String getNotNull(HttpServletRequest req, String name, String defaultValue) {
        String s = req.getParameter(name);
        return s == null ? defaultValue : s;
    }
}

class PostDispatcher {
    Object instance;
    Method method;
    String[] paramName;
    Class<?>[] paramType;
}
