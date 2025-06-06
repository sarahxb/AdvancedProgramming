package org.example;


import java.lang.reflect.*;

public class TestRunner {

    public static void runTests(Class<?> cls, TestStatistics stats) {
        if (!Modifier.isPublic(cls.getModifiers())) return;

        Method[] methods = cls.getDeclaredMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class)) {
                stats.total++;
                try {
                    Object instance = Modifier.isStatic(m.getModifiers()) ? null : cls.getDeclaredConstructor().newInstance();
                    Object[] args = generateMockArgs(m.getParameterTypes());
                    m.setAccessible(true);
                    m.invoke(instance, args);
                    System.out.println( m.getName() + " passed.");
                    stats.passed++;
                } catch (Exception e) {
                    System.out.println( m.getName() + " failed: " + e.getCause());
                    stats.failed++;
                }
            }
        }
    }

    private static Object[] generateMockArgs(Class<?>[] paramTypes) {
        Object[] args = new Object[paramTypes.length];
        for (int i = 0; i < paramTypes.length; i++) {
            args[i] = MockValueGenerator.generate(paramTypes[i]);
        }
        return args;
    }
}
