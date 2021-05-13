package app.controller;

import app.model.Game;
import app.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the main menu.
 */
public class MainMenuController {
    @FXML
    TextField txtPlayer1;

    @FXML
    TextField txtPlayer2;

    /**
     * On submit button pressed.
     */
    @FXML
    void onSubmit() {
        Game.getInstance().initGame();
        String player1Name = txtPlayer1.getText();
        String player2Name = txtPlayer2.getText();
        if (player1Name.equals("") || player2Name.equals("")) return;
        Game game = Game.getInstance();
        game.setPlayer1(new Player(player1Name));
        game.setPlayer2(new Player(player2Name));
        game.setActivePlayer(game.getPlayer1());
        navigateToGame();
    }

    /**
     * On leaderboard button pressed.
     *
     * @throws IOException if view cannot be found.
     */
    @FXML
    void onLeaderboardPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/leaderboardView.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        Stage stage = ((Stage) txtPlayer1.getScene().getWindow());
        stage.setTitle("Leaderboard");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Navigate to the game view.
     */
    void navigateToGame() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/gameView.fxml"));
        loader.setControllerFactory(c -> new GameController());
        try {
            loader.load();
        } catch (IOException ignored) {
        }
        Scene scene = new Scene(loader.getRoot());
        ((GameController) loader.getController()).setScene(scene);
        Stage stage = (Stage) (txtPlayer1.getScene().getWindow());
        stage.setScene(scene);
        stage.setTitle("Stones");
    }
}
