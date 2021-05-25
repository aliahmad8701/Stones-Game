// CHECKSTYLE:OFF

package app.controller;

import app.model.Game;
import app.model.Leaderboard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;

/**
 * The controller for the Leaderboard.
 */
@Slf4j
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
     * Initialize the controller.
     * @throws java.io.IOException
     */
    public void initialize() throws IOException {
        Game.getInstance().initGame();
        game = Game.getInstance();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String json = Files.readString(Path.of("game.json"));

        Leaderboard leaderboard = gson.fromJson(json, Leaderboard.class);

        leaderboard.getEntries().forEach(leaderboardEntry -> game.getLeaderboard().addEntry(
                new Leaderboard.LeaderboardEntry(
                        leaderboardEntry.getGameDate(),
                        leaderboardEntry.getPlayer1(),
                        leaderboardEntry.getPlayer2(),
                        leaderboardEntry.getWinner(),
                        leaderboardEntry.getTurns())
        ));
        log.info("Data has been read from JSON file successfully");

        colDate.setCellValueFactory(new PropertyValueFactory<>("gameDate"));
        colPlayer1.setCellValueFactory(new PropertyValueFactory<>("player1"));
        colPlayer2.setCellValueFactory(new PropertyValueFactory<>("player2"));
        colWinner.setCellValueFactory(new PropertyValueFactory<>("winner"));
        colTurns.setCellValueFactory(new PropertyValueFactory<>("turns"));
        tblLeaderboard.getItems().clear();
        tblLeaderboard.getItems().addAll(game.getLeaderboard().getEntries());
        log.info("Data has been shown into table successfully!");
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
        log.info("Main Menu Scene is loading!!");
    }
}
