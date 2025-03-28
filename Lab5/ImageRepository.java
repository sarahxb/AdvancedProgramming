package org.example;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ImageRepository implements Serializable {
    private final List<ImageRecord> images = new ArrayList<>();


    public void addImage(ImageRecord image) {
        images.add(image);
    }


    public void displayImage(ImageRecord image) {
        File file = new File(String.valueOf(image.getPath()));

        if (!file.exists()) {
            System.err.println("File does not exist: " + file.getAbsolutePath());
            return;
        }

        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.err.println("Error displaying image: " + e.getMessage());
            }
        } else {
            System.err.println("Desktop not supported on this system.");
        }
    }


    public void saveToFile(String fileName) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
            System.out.println("Repository saved successfully.");
        }
    }


    public static ImageRepository loadFromFile(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            System.out.println("Repository loaded successfully.");
            return (ImageRepository) in.readObject();
        }
    }
}
