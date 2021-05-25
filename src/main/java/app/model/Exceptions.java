package app.model;

/**
 * Custom exceptions used for this project.
 * <a href="https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/lang/Exception.html"> For documentation check: JDK16 Exceptions</a>
 */
public class Exceptions {
	/**
	 * exception CellEvolvedException If cell is already evolved/updated to max color YELLOW.
	 */
    public static class CellEvolvedException extends Exception {
    }
	/**
	 * exception NoActivePlayerException If there is no active player.
	 */

    public static class NoActivePlayerException extends Exception {
    }
}
