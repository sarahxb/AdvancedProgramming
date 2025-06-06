package org.example;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;
import java.util.jar.*;


public class ClassExplorer {

    public static List<Class<?>> findAndLoadClasses(File root) throws Exception {
        List<URL> urls = new ArrayList<>();
        List<String> classNames = new ArrayList<>();

        if (root.isDirectory()) {
            urls.add(root.toURI().toURL());
            Files.walk(root.toPath())
                    .filter(path -> path.toString().endsWith(".class"))
                    .forEach(path -> {
                        String relative = root.toPath().relativize(path).toString();
                        String className = relative.replace(File.separatorChar, '.').replaceAll("\\.class$", "");
                        classNames.add(className);
                    });
        } else if (root.getName().endsWith(".jar")) {
            urls.add(new URL("jar:file:" + root.getAbsolutePath() + "!/"));
            try (JarFile jar = new JarFile(root)) {
                jar.stream()
                        .filter(e -> e.getName().endsWith(".class"))
                        .forEach(e -> classNames.add(e.getName().replace("/", ".").replaceAll("\\.class$", "")));
            }
        }

        URLClassLoader loader = new URLClassLoader(urls.toArray(new URL[0]));
        List<Class<?>> result = new ArrayList<>();
        for (String name : classNames) {
            try {
                Class<?> cls = loader.loadClass(name);
                result.add(cls);
            } catch (ClassNotFoundException | NoClassDefFoundError e) {

            }
        }
        return result;
    }
}
