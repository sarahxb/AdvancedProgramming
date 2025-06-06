package org.example;

import java.util.Random;

public class MockValueGenerator {
    private static final Random random = new Random();

    public static Object generate(Class<?> type) {
        if (type == int.class || type == Integer.class) return random.nextInt(100);
        if (type == long.class || type == Long.class) return random.nextLong();
        if (type == double.class || type == Double.class) return random.nextDouble();
        if (type == boolean.class || type == Boolean.class) return random.nextBoolean();
        if (type == String.class) return "mockString";
        if (type == char.class || type == Character.class) return (char) ('a' + random.nextInt(26));
        return null;
    }
}
