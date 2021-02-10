package sample;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class GameBoard {
    private int[][] board;
    private int zeroX;
    private int zeroY;
    private final int size = 4;
    private int h;

    public GameBoard(int[][] blocks) {
        this.board = copy(blocks);
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[i].length; j++) {
                if (blocks[i][j] != (i * size + j + 1) && blocks[i][j] != 0) {
                    int nominal = board[i][j];
                    int x0 = (nominal - 1) / size;
                    int y0 = nominal - x0 * size - 1;
                    int dx = Math.abs(i - x0);
                    int dy = Math.abs(j - y0);
                    h = h + dx + dy;
                }
                if (blocks[i][j] == 0) {
                    zeroX = i;
                    zeroY = j;
                }
            }
        }
    }


    public Iterable<GameBoard> neighbors() {
        Set<GameBoard> boardSet = new HashSet<>();
        boardSet.add(change(getNewCell(), zeroX, zeroY, zeroX, zeroY + 1));
        boardSet.add(change(getNewCell(), zeroX, zeroY, zeroX, zeroY - 1));
        boardSet.add(change(getNewCell(), zeroX, zeroY, zeroX - 1, zeroY));
        boardSet.add(change(getNewCell(), zeroX, zeroY, zeroX + 1, zeroY));

        return boardSet;
    }
    private GameBoard change(int[][] board2, int x1, int y1, int x2, int y2) {
        if (x2 > -1 && x2 < size && y2 > -1 && y2 < size) {
            int t = board2[x2][y2];
            board2[x2][y2] = board2[x1][y1];
            board2[x1][y1] = t;
            return new GameBoard(board2);
        } else
            return null;

    }

    private static int[][] copy(int[][] original) {
        if (original == null) {
            return null;
        }
        final int[][] result = new int[original.length][];
        for (int i = 0; i < original.length; i++) {
            result[i] = new int[original[i].length];
            System.arraycopy(original[i], 0, result[i], 0, original[i].length);
        }
        return result;
    }

    public boolean canSolve() {
        int n = 0;
        int[] temp = new int[size * size];
        int counter = 0;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                temp[counter] = board[x][y];
                counter++;
            }
        }
        for (int i = 0; i < size * size; i++) {
            if (temp[i] != 0) {
                for (int j = 0; j < i; j++) {
                    if (temp[j] > temp[i]) n++;
                }
            } else {
                n = n + 1 + i / size;
            }
        }
        return n % 2 != 0;
    }

    private int[][] getNewCell() {
        return copy(board);
    }

    public int getH() {
        return h;
    }

    public boolean Win() {
        return h == 0;
    }
    public int getSize(){
        return size;
    }
    public int getCell(int x, int y) {
        return board[x][y];
    }

    public void printField() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(String.format("%2d ", board[y][x]));
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameBoard board = (GameBoard) o;
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (this.board[i][j] != board.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}