package org.example;

import java.net.Socket;
import java.util.Objects;

public class Player {
    private final String name;
    private Socket socket;
    private long timeLeftMillis;

    public Player(String name, Socket socket, long initialTimeMillis) {
        this.name = name;
        this.socket = socket;
        this.timeLeftMillis = initialTimeMillis;
    }

    public String getName() { return name; }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
