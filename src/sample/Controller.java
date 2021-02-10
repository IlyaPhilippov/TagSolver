package sample;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.util.List;

public class Controller {

    @FXML
    private FlowPane flowpane;
    private GameBoard board;
    private View graphics;
    private Solver solver;
    private List<GameBoard> result;
    private int size;
    private int currentIndex = 0;
    private boolean b = false;

    private void setImagesOnFlowPane() {
        flowpane.getChildren().clear();
        size = board.getSize();
        ImageView[][] images = graphics.getImages();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                flowpane.getChildren().add(images[x][y]);
            }
        }
    }

    public void setGraphics(View graphics) {
        this.graphics = graphics;
        setImagesOnFlowPane();
    }

    public void setGameField(GameBoard board) {
        this.board = board;
    }

    @FXML
    public void startSolver() {
        if (!b) {
            solver = new Solver(board);
            b = true;
            result = solver.solution();
        }
        showMoves();
    }
    private void showMoves() {
        nextMove();
        currentIndex++;
        System.out.println("Ход: " + currentIndex + " из " + Solver.totalmoves);
    }

    private void nextMove() {
        if (currentIndex >= result.size()) {
            currentIndex = 0;
        }
        graphics.setBoard(result.get(currentIndex));
        graphics.updateUI();
    }
}