package app;

import app.model.Exceptions;
import app.model.Game;
import app.model.Player;
import org.junit.Assert;
import org.junit.Test;

public class UnitTests {

    Game game;

    void setupGame() {
        game = Game.getInstance();
        game.initGame();
        game.setPlayer1(new Player("Jane"));
        game.setPlayer2(new Player("John"));
        game.setActivePlayer(game.getPlayer1());
    }

    @Test
    public void shouldCountTurnsProperly() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 0);
            Assert.assertEquals(3, game.getTurns());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldWinRedRowZero() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 2);
            Assert.assertTrue(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldNotWinRowZero() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 1);
            Assert.assertFalse(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldWinColZero() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(1, 0);
            game.playRound(2, 0);
            Assert.assertTrue(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldNotWinColZero() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(1, 0);
            game.playRound(1, 0);
            game.playRound(2, 0);
            Assert.assertFalse(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldWinDiagonal() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(1, 1);
            game.playRound(2, 2);
            Assert.assertTrue(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }

    @Test
    public void shouldNotWinDiagonal() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(1, 1);
            game.playRound(1, 1);
            game.playRound(2, 2);
            Assert.assertFalse(game.gameWon());
        } catch (Exceptions.NoActivePlayerException | Exceptions.CellEvolvedException e) {
            Assert.fail();
        }
    }


    @Test
    public void shouldFailEvolvingBeyondMax() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(0, 0);
            game.playRound(0, 0);
            game.playRound(0, 0);
            Assert.fail();
        } catch (Exceptions.CellEvolvedException e) {
            Assert.assertTrue(true);
        } catch (Exceptions.NoActivePlayerException e) {
            Assert.fail("No active player.");
        }
    }

    @Test
    public void player1ShouldWin() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(0, 2);
            Assert.assertEquals(game.getActivePlayer(), game.getPlayer1());
        } catch (Exceptions.CellEvolvedException | Exceptions.NoActivePlayerException e) {
            Assert.fail();
        }
    }

    @Test
    public void player2ShouldWin() {
        setupGame();
        try {
            game.playRound(0, 0);
            game.playRound(0, 1);
            game.playRound(2, 2);
            game.playRound(0, 2);
            Assert.assertEquals(game.getActivePlayer(), game.getPlayer2());
        } catch (Exceptions.CellEvolvedException | Exceptions.NoActivePlayerException e) {
            Assert.fail();
        }
    }
}
