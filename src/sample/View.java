package sample;
 import javafx.scene.control.Alert;
 import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.util.HashMap;

public class View {

    private GameBoard board;
    private HashMap<Integer, Image> picturesMap;
    private ImageView[][] images;
    private final int size;
    private final double WIDTH = 320.0;
    private final double HEIGHT = 320.0;

    public View(GameBoard board) throws FileNotFoundException {
        this.board = board;
        setPicturesMap();
        size = board.getSize();
        images = new ImageView[size][size];

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                ImageView newImage = new ImageView();
                newImage.setImage(picturesMap.get(board.getCell(x, y)));
                newImage.setFitWidth(WIDTH / size);
                newImage.setFitHeight(HEIGHT / size);
                images[y][x] = newImage;
            }
        }
    }

    public void updateUI() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int value = board.getCell(x, y);
                images[y][x].setImage(picturesMap.get(value));
            }
        }
    }

    public void setBoard(GameBoard board) {
        this.board = board;
    }

    private void setPicturesMap() throws FileNotFoundException {
        picturesMap = new HashMap<>();
        picturesMap.put(0, new Image(new FileInputStream("src/images/0.jpg")));
        picturesMap.put(1, new Image(new FileInputStream("src/images/1.jpg")));
        picturesMap.put(2, new Image(new FileInputStream("src/images/2.jpg")));
        picturesMap.put(3, new Image(new FileInputStream("src/images/3.jpg")));
        picturesMap.put(4, new Image(new FileInputStream("src/images/4.jpg")));
        picturesMap.put(5, new Image(new FileInputStream("src/images/5.jpg")));
        picturesMap.put(6, new Image(new FileInputStream("src/images/6.jpg")));
        picturesMap.put(7, new Image(new FileInputStream("src/images/7.jpg")));
        picturesMap.put(8, new Image(new FileInputStream("src/images/8.jpg")));
        picturesMap.put(9, new Image(new FileInputStream("src/images/9.jpg")));
        picturesMap.put(10, new Image(new FileInputStream("src/images/10.jpg")));
        picturesMap.put(11, new Image(new FileInputStream("src/images/11.jpg")));
        picturesMap.put(12, new Image(new FileInputStream("src/images/12.jpg")));
        picturesMap.put(13, new Image(new FileInputStream("src/images/13.jpg")));
        picturesMap.put(14, new Image(new FileInputStream("src/images/14.jpg")));
        picturesMap.put(15, new Image(new FileInputStream("src/images/15.jpg")));
    }

    public ImageView[][] getImages() {
        return images;
    }

}