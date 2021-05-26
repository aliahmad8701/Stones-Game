package app.model;

/**
 * Represents a cell in the game.
 */
public class Cell {
    private final int x, y;
    private CellColor color;

    /**
     * To create a new cell.
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
     * To get the x coord of the cell.
     *
     * @return the {@code x} coord of the cell
     */
    public int getX() {
        return x;
    }

    /**
     * to get the y coord of the cell.
     *
     * @return the {@code y} coord of the cell
     */
    public int getY() {
        return y;
    }

    /**
     * To get the color of the cell.
     *
     * @return the {@code color} of the cell
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * To set the color of the cell.
     *
     * @param color the color of the cell
     */
    public void setColor(CellColor color) {
        this.color = color;
    }

    /**
     * 'Evolve/update' a cell color.
     * {@code RED}, {@code GREEN}, {@code YELLOW}
     *	<a href="https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Exception.html"> JDK16 Exceptions</a>
     * @throws Exceptions.CellEvolvedException if cell has already reached maximum evolution step.
     */
    public void evolve() throws Exceptions.CellEvolvedException {
        switch (color) {
            case CLEAR:
                setColor(CellColor.RED);
                break;
            case RED:
                setColor(CellColor.YELLOW);
                break;
            case YELLOW:
                setColor(CellColor.GREEN);
                break;
            default:
                throw new Exceptions.CellEvolvedException();
        }
    }

    /**
     * If a cell color matches another.
     *
     * @param other the other cell
     * @return {@code TRUE} if colors match, {@code FALSE} otherwise.
     */
    public boolean matches(Cell other) {
        return getColor().equals(other.getColor());
    }

    /**
     * Possible colors that can be used for a cell {@code CLEAR}, {@code RED}, {@code YELLOW} or {@code GREEN} cell.
     */
    public enum CellColor {
	/**
	 * CLEAR cell.
	 */
        CLEAR,
	/**
	 * RED cell.
	 */
        RED,
	/**
	 * YELLOW cell.
	 */
        YELLOW,
	/**
	 * GREEN cell.
	 */
        GREEN
    }

}
