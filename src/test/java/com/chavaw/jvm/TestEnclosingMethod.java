package com.chavaw.jvm;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class TestEnclosingMethod {
    public static void main(String[] args) {
        List a= new LinkedList();
        a.sort(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        });
    }
}
