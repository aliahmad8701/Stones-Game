package app;

import app.model.Exceptions;
import app.model.Game;
import app.model.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests {

    Game game;

    @BeforeEach
    void setupGame() {
        game = Game.getInstance();
        game.initGame();
        game.setPlayer1(new Player("Jane"));
        game.setPlayer2(new Player("John"));
        game.setActivePlayer(game.getPlayer1());
    }

    @Test
    public void shouldCountTurnsProperly() {
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 0);
            assertEquals(3, game.getTurns(), () -> "test1 error");
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }


    @Test
    public void shouldWinRedRowZero() {
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 2);
            assertTrue(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }

    @Test
    public void shouldNotWinRowZero() {
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 1);
            assertFalse(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }

    @Test
    public void shouldWinColZero() {
        try {
            game.playRound(0, 0);
            game.playRound(1, 0);
            game.playRound(2, 0);
            assertTrue(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }

    @Test
    public void shouldNotWinColZero() {
        try {
            game.playRound(0, 0);
            game.playRound(1, 0);
            game.playRound(1, 0);
            game.playRound(2, 0);
            assertFalse(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }

    @Test
    public void shouldWinDiagonal() {
        try {
            game.playRound(0, 0);
            game.playRound(1, 1);
            game.playRound(2, 2);
            assertTrue(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }

    @Test
    public void shouldNotWinDiagonal() {
        try {
            game.playRound(0, 0);
            game.playRound(1, 1);
            game.playRound(1, 1);
            game.playRound(2, 2);
            assertFalse(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            assertTrue(fail());
        }
    }


    @Test
    public void shouldFailEvolvingBeyondMax() {
        try {
            game.playRound(0, 0);
            game.playRound(0, 0);
            game.playRound(0, 0);
            game.playRound(0, 0);
            assertTrue(fail());
        } catch (Exceptions.CellEvolvedException e) {
            assertTrue(true);
        } catch (Exceptions.NoActivePlayerException e) {
            assertTrue(fail("No active player."));
        }
    }

    @Test
    public void player1ShouldWin() {
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 2);
            assertEquals(game.getActivePlayer(), game.getPlayer1());
        } catch (Exceptions.CellEvolvedException | Exceptions.NoActivePlayerException e) {
            assertTrue(fail());
        }
    }

    @Test
    public void player2ShouldWin() {
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(2, 2);
            game.playRound(0, 2);
            assertEquals(game.getActivePlayer(), game.getPlayer2());
        } catch (Exceptions.CellEvolvedException | Exceptions.NoActivePlayerException e) {
            assertTrue(fail());
        }
    }
}
