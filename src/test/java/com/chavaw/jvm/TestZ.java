package com.chavaw.jvm;


import java.lang.reflect.Field;

/**
 *
 * Created by root on 7/19/17.
 */
public class TestZ {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Class integer = Integer.class.getDeclaredClasses()[0];
        System.out.println(integer.getName());
        Field field = integer.getDeclaredField("cache");
        field.setAccessible(true);
        Integer[] array = (Integer[]) field.get(integer);
        array[130] = array[220];
        Integer a = 1+1;
        System.out.println(a);
    }
}
