package org.example;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            System.out.println("Usage: java Main <path-to-class/folder/jar>");
            return;
        }

        File input = new File(args[0]);
        if (!input.exists()) {
            System.err.println("Invalid input path.");
            return;
        }

        List<Class<?>> classes = ClassExplorer.findAndLoadClasses(input);
        TestStatistics stats = new TestStatistics();

        for (Class<?> cls : classes) {
            if (!cls.isAnnotationPresent(Deprecated.class)) {
                ClassAnalyzer.printClassStructure(cls);
                TestRunner.runTests(cls, stats);
            }
        }

        stats.printSummary();
    }
}
