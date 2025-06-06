package org.example;

import java.lang.reflect.*;

public class ClassAnalyzer {

    public static void printClassStructure(Class<?> cls) {
        System.out.println("Class: " + cls.getName());

        System.out.println("  Fields:");
        for (Field f : cls.getDeclaredFields()) {
            System.out.println("    " + Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName());
        }

        System.out.println("  Constructors:");
        for (Constructor<?> c : cls.getDeclaredConstructors()) {
            System.out.println("    " + Modifier.toString(c.getModifiers()) + " " + c.getName());
        }

        System.out.println("  Methods:");
        for (Method m : cls.getDeclaredMethods()) {
            System.out.println("    " + Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getSimpleName() + " " + m.getName());
        }

        System.out.println();
    }
}
