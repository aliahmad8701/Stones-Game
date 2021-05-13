package app.model;

import java.util.Date;

/**
 * Singleton that represents a Stones game.
 */
public class Game {
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static Game game = null;
    private final Leaderboard leaderboard;
    private Cell[][] board;
    private Player player1;
    private Player player2;
    private Player activePlayer;
    private Date gameDate;
    private int turns;

    /**
     * Create a new game.
     */
    private Game() {
        initGame();
        leaderboard = new Leaderboard();
    }

    /**
     * Get the instance of the game
     *
     * @return a Game instance.
     */
    public static Game getInstance() {
        if (game == null) game = new Game();
        return game;
    }

    /**
     * Initialize game.
     */
    public void initGame() {
        board = new Cell[ROWS][COLS];
        for (int row = 0; row < ROWS; row++)
            for (int col = 0; col < COLS; col++)
                board[row][col] = new Cell(row, col);
        gameDate = new Date();
        turns = 0;
    }

    /**
     * Get the board of the game.
     *
     * @return the board of the game.
     */
    public Cell[][] getBoard() {
        return board;
    }

    /**
     * Get player 1
     *
     * @return player 1
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * Set player 1
     *
     * @param player1 player 1
     */
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    /**
     * Get player 2
     *
     * @return player 2
     */
    public Player getPlayer2() {
        return player2;
    }

    /**
     * Set player 2
     *
     * @param player2 player 2
     */
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    /**
     * Get the current active player
     *
     * @return the current active player
     */
    public Player getActivePlayer() {
        return activePlayer;
    }

    /**
     * Set the active player
     *
     * @param player player to be set as active
     */
    public void setActivePlayer(Player player) {
        activePlayer = player;
    }

    /**
     * Play a round of the game
     *
     * @param x selected cell x
     * @param y selected cell y
     * @return played cell
     * @throws Exceptions.NoActivePlayerException if there is no active player
     * @throws Exceptions.CellEvolvedException    if cell is already evolved to max
     */
    public Cell playRound(int x, int y) throws Exceptions.NoActivePlayerException, Exceptions.CellEvolvedException {
        if (activePlayer == null) throw new Exceptions.NoActivePlayerException();
        Cell cell = board[x][y];
        cell.evolve();
        turns++;
        if (gameWon()) return cell;
        if (activePlayer.equals(player1))
            activePlayer = player2;
        else
            activePlayer = player1;
        return cell;
    }

    /**
     * Determine if the game is won.
     *
     * @return true if game won, false otherwise.
     */
    public boolean gameWon() {
        if (isSameColor(board[0][0], board[0][1], board[0][2])) return true;
        if (isSameColor(board[1][0], board[1][1], board[1][2])) return true;
        if (isSameColor(board[2][0], board[2][1], board[2][2])) return true;

        if (isSameColor(board[0][0], board[1][0], board[2][0])) return true;
        if (isSameColor(board[0][1], board[1][1], board[2][1])) return true;
        if (isSameColor(board[0][2], board[1][2], board[2][2])) return true;

        if (isSameColor(board[0][0], board[1][1], board[2][2])) return true;
        return isSameColor(board[0][2], board[1][1], board[2][0]);
    }

    /**
     * True if given cells are same color.
     *
     * @param cell1 cell 1
     * @param cell2 cell 2
     * @param cell3 cell 3
     * @return true or false
     */
    private boolean isSameColor(Cell cell1, Cell cell2, Cell cell3) {
        return cell1.matches(cell2) && cell1.matches(cell3) && !cell1.getColor().equals(Cell.CellColor.CLEAR);
    }

    /**
     * Get the leaderboard of the game.
     *
     * @return the leaderboard
     */
    public Leaderboard getLeaderboard() {
        return leaderboard;
    }

    /**
     * Get the game date.
     *
     * @return the game date.
     */
    public Date getGameDate() {
        return gameDate;
    }

    /**
     * Get the number of turns played
     *
     * @return the number of turns played.
     */
    public int getTurns() {
        return turns;
    }
}
