package org.example;

import java.util.List;
import java.io.Serializable;
import java.util.Date;


public record ImageRecord(String name, Date date, List<String> tags, String location) implements Serializable {
    public java.nio.file.Path getPath() {
        return java.nio.file.Path.of(location);
    }
}
