package com.ying.tjava.core;

public class TestRecord {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point p = new Point(-3, 52);
		System.out.println(p);

	}

}

//	java 14 特性 record 相当于注释
/**
 * final class Point extends Record {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    public String toString() {
        return String.format("Point[x=%s, y=%s]", x, y);
    }

    public boolean equals(Object o) {
        ...
    }
    public int hashCode() {
        ...
    }
}
 */
record Point(int x, int y) {
	//	可以补充限制
	public Point{
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException();
		}
	}
}
