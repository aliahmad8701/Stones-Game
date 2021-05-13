package app.model;

/**
 * Represents a cell in the game.
 */
public class Cell {
    private final int x, y;
    private CellColor color;

    /**
     * Create a new cell.
     *
     * @param x x coord of the cell
     * @param y y coord of the cell
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        color = CellColor.CLEAR;
    }

    /**
     * Get the x coord of the cell
     *
     * @return the x coord of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * Get the y coord of the cell
     *
     * @return the y coord of the cell
     */
    public int getY() {
        return y;
    }

    /**
     * Get the color of the cell
     *
     * @return the color of the cell
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * Set the color of the cell
     *
     * @param color the color of the cell
     */
    public void setColor(CellColor color) {
        this.color = color;
    }

    /**
     * 'Evolve' a cell color
     *
     * @throws Exceptions.CellEvolvedException if cell has already reached maximum evolution step.
     */
    public void evolve() throws Exceptions.CellEvolvedException {
        switch (color) {
            case CLEAR:
                setColor(CellColor.RED);
                break;
            case RED:
                setColor(CellColor.GREEN);
                break;
            case GREEN:
                setColor(CellColor.YELLOW);
                break;
            default:
                throw new Exceptions.CellEvolvedException();
        }
    }

    /**
     * If a cell color matches another.
     *
     * @param other the other cell
     * @return true if colors match, false otherwise.
     */
    public boolean matches(Cell other) {
        return getColor().equals(other.getColor());
    }

    /**
     * Cell colors.
     */
    public enum CellColor {
        CLEAR,
        RED,
        YELLOW,
        GREEN
    }

}
