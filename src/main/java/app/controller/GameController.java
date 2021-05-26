// CHECKSTYLE:OFF
package app.controller;

import app.model.Cell;
import app.model.Exceptions;
import app.model.Game;
import app.model.Leaderboard;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Controller for the game view.
 */
@Slf4j
public class GameController {
    private Game game;
    @FXML
    Button btn0x0;
    @FXML
    Button btn0x1;
    @FXML
    Button btn0x2;
    @FXML
    Button btn1x0;
    @FXML
    Button btn1x1;
    @FXML
    Button btn1x2;
    @FXML
    Button btn2x0;
    @FXML
    Button btn2x1;
    @FXML
    Button btn2x2;
    @FXML
    Label lblStatus;
    @FXML
    GridPane gameGrid;
    private Scene scene;

    /**
     * Create a new GameController.
     */
    public GameController() {
        game = Game.getInstance();
    }

    /**
     * Initialize the controller.
     */
    public void initialize() {
        btn0x0.setOnMouseClicked(event -> onCellClicked(0, 0));
        btn0x1.setOnMouseClicked(event -> onCellClicked(0, 1));
        btn0x2.setOnMouseClicked(event -> onCellClicked(0, 2));
        btn1x0.setOnMouseClicked(event -> onCellClicked(1, 0));
        btn1x1.setOnMouseClicked(event -> onCellClicked(1, 1));
        btn1x2.setOnMouseClicked(event -> onCellClicked(1, 2));
        btn2x0.setOnMouseClicked(event -> onCellClicked(2, 0));
        btn2x1.setOnMouseClicked(event -> onCellClicked(2, 1));
        btn2x2.setOnMouseClicked(event -> onCellClicked(2, 2));
        lblStatus.setText(game.getActivePlayer().getName() + "'s turn to play.");
    }

    /**
     * Handle cell clicks.
     *
     * @param x x coord of the cell
     * @param y y coord of the cell
     */
    void onCellClicked(int x, int y) {
        Cell cell;
        try {
            cell = game.playRound(x, y);
            scene.lookup(String.format("#btn%dx%d", x, y)).setStyle(String.format("-fx-background-color: %s", getColorValue(cell.getColor())));
            log.info("Cell {x}, {y} clicked", x, y);
            if (game.gameWon()) {
                handleWonGame();
            } else {
                lblStatus.setText(game.getActivePlayer().getName() + "'s turn to play.");
            }
        } catch (Exceptions.NoActivePlayerException ex ){
            log.error("No player has been made active");
        }catch(Exceptions.CellEvolvedException ex){
            log.error("Something went wrong with cells evolved!");
        } catch (IOException ioException) {
            log.error("Something went Wrong while handling won game, {}", ioException);
        }
    }

    /**
     * Get the color value for a cell.
     *
     * @param color the color of the cell
     * @return hex color string
     */
    String getColorValue(Cell.CellColor color) {
        switch (color) {
            case CLEAR:
                return "#fafafa";
            case RED:
                return "#ef5350";
            case YELLOW:
                return "#ffee58";
            case GREEN:
                return "#66bb6a";

        }
        return "#fafafa";
    }

    /**
     * Handle UI for a won game.
     */
    void handleWonGame() throws IOException {
        lblStatus.setText(game.getActivePlayer().getName() + " won the game!");
        gameGrid.setDisable(true);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        game.getLeaderboard().addEntry(
                new Leaderboard.LeaderboardEntry(game.getGameDate(), game.getPlayer1().getName(),
                        game.getPlayer2().getName(), game.getActivePlayer().getName(), game.getTurns())
        );
        Files.write(Path.of("game.json"),gson.toJson(game.getLeaderboard()).getBytes());
        log.info("Data has been persisted to JSON file");
    }

    /**
     * Set the scene for the controller.
     *
     * @param scene the scene
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Handle main menu button press
     *
     * @throws IOException if view cannot be found
     */
    @FXML
    void onMainMenuPressed() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/mainMenu.fxml"));
        loader.load();
        Scene scene = new Scene(loader.getRoot());
        Stage stage = ((Stage) gameGrid.getScene().getWindow());
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
        log.info("MainMenu scene is loading!!");

    }
}
