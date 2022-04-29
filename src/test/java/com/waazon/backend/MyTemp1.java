package com.waazon.backend;

import org.assertj.core.util.Arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mohammad Aiub Khan
 */
public class MyTemp1 {
    public static void main(String[] args) {
        MyTemp1 myTemp1 = new MyTemp1();
        long startTime = System.currentTimeMillis();
        System.out.println(myTemp1.isAnagram("aacc", "caac"));
        long endTime = System.currentTimeMillis();
        long duration = (endTime - startTime);
        System.out.println("Execution took: " + duration + "ms");
    }

    public boolean isAnagram(String s, String t) {
        char[] abc = new char[128];

        for (char c : s.toCharArray()) {
            abc[c]++;
            System.out.println(abc[c]);
        }

        for (char c : t.toCharArray()) {
            abc[c]--;
        }

        for (int i : abc) {
            if (i != 0) return false;
        }

        return true;
    }
}
