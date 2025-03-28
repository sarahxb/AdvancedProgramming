package org.example;

import java.io.IOException;

import java.util.Date;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        ImageRepository repo = new ImageRepository();


        ImageRecord img1 = new ImageRecord("Sunset", new Date(), List.of("nature", "sunset"), "D:\\ProgramareAvansata\\Lab5\\sunset.jpg");
        ImageRecord img2 = new ImageRecord("Mountain", new Date(), List.of("nature", "mountain"), "D:\\ProgramareAvansata\\Lab5\\mountain.jpg");

        repo.addImage(img1);
        repo.addImage(img2);


        repo.displayImage(img1);


        try {
            repo.saveToFile("images.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ImageRepository loadedRepo = ImageRepository.loadFromFile("images.dat");
            repo.displayImage(img2);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
