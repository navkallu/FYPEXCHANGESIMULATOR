package com.exsim.ordersender;

import java.util.HashMap;

public class TwoWayMap {
    private final HashMap<Object, Object> firstToSecond = new HashMap<Object, Object>();
    private final HashMap<Object, Object> secondToFirst = new HashMap<Object, Object>();

    public void put(Object first, Object second) {
        firstToSecond.put(first, second);
        secondToFirst.put(second, first);
    }

    public Object getFirst(Object first) {
        return firstToSecond.get(first);
    }

    public Object getSecond(Object second) {
        return secondToFirst.get(second);
    }

    public String toString() {
        return firstToSecond.toString() + "\n" + secondToFirst.toString();
    }
}
