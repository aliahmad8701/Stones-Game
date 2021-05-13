package app.controller;

import app.model.Game;
import app.model.Leaderboard;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

/**
 * The controller for the Leaderboard.
 */
public class LeaderboardController {
    Game game;

    @FXML
    TableView<Leaderboard.LeaderboardEntry> tblLeaderboard;
    @FXML
    TableColumn<Leaderboard.LeaderboardEntry, Date> colDate;
    @FXML
    TableColumn<Leaderboard.LeaderboardEntry, String> colPlayer1;
    @FXML
    TableColumn<Leaderboard.LeaderboardEntry, String> colPlayer2;
    @FXML
    TableColumn<Leaderboard.LeaderboardEntry, String> colWinner;
    @FXML
    TableColumn<Leaderboard.LeaderboardEntry, Integer> colTurns;


    /**
     * Create a new controller.
     */
    public LeaderboardController() {
        game = Game.getInstance();
    }

    /**
     * Initialize the controller.
     */
    public void initialize() {
        colDate.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
        colPlayer1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        colPlayer2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        colWinner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        colTurns.setCellValueFactory(new PropertyValueFactory<>("turns"));
        tblLeaderboard.getItems().addAll(game.getLeaderboard().getEntries());
    }

    /**
     * On main menu button pressed.
     *
     * @throws IOException if view cannot be found.
     */
    @FXML
    void onMainMenuPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainMenu.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        Stage stage = ((Stage) tblLeaderboard.getScene().getWindow());
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }
}
