package sample;

public class Obj {
    private Obj last;
    private GameBoard board;

    public Obj(Obj last, GameBoard board) {
        this.last = last;
        this.board = board;
    }

    public Obj getLast() {
        return last;
    }

    public GameBoard getBoard() {
        return board;
    }
}