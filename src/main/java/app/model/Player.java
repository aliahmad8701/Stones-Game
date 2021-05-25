package app.model;

/**
 * Represents a player.
 */
public class Player {

    private final String name;

    /**
     * Create a player.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Get the name of the player.
     *
     * @return the {@code name} of the player.
     */
    public String getName() {
        return name;
    }

    /**
     * Overridden equals.
     *
     * @param o the other object
     * @return {@code true} if equals, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Player)) return false;
        return ((Player) o).getName().equals(name);
    }
}
