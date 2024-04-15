package com.ying.tjava.exception;

import java.util.logging.*;

public class TestJDKLogging {

	public static void main(String[] args) {
		//	七种日志级别
		//	SEVERE WARNING INFO CONFIG FINE FINER FINEST
		Logger log = Logger.getGlobal();
		//	终端默认日志捕捉级别为INFO及以上
		log.info("这是一条INFO消息");
		log.warning("这是一条WARNING消息");
		log.severe("这是一条SEVERE消息");
		//	不被捕捉到
		log.config("这是一条CONFIG消息");
	}

}
