package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        int[][] testarray = new int[][]{
                {0,15,9,6},
                {10,12,1,3},
                {14,2,5,7},
                {11,4,13,8},
        };

        GameBoard field = new GameBoard(testarray);
        View graphics = new View(field);

        stage.setTitle("Tag Game Solver");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/sample.fxml"));
        Parent content = loader.load();
        Controller controller = loader.getController();
        controller.setGameField(field);
        controller.setGraphics(graphics);

        Scene scene = new Scene(content, 500, 320);
        stage.setScene(scene);
        content.requestFocus();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
