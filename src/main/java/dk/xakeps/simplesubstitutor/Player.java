package dk.xakeps.simplesubstitutor;

import java.util.UUID;

public class Player {
    private final UUID id;
    private String name;
    private int level;

    public Player(UUID id) {
        this.id = id;
    }

    Player(UUID id, String name, int level) {
        this.id = id;
        this.name = name;
        this.level = level;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
