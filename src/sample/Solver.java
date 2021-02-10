package sample;

import java.util.*;

public class Solver {

    private GameBoard initial;
    private Set<GameBoard> checkedItems = new HashSet<>();
    private List<GameBoard> result = new ArrayList<>();
    public static int totalmoves;

    public Solver(GameBoard initial) {
        if (initial.canSolve()) throw new IllegalArgumentException("Одна ошибка - и ты ошибся");
        this.initial = initial;
        start();
    }

    private void start() {
        Comparator<Obj> comparator = (o1, o2) -> {
            int m1 = measure(o1);
            int m2 = measure(o2);
            return Integer.compare(m1, m2);
        };

        PriorityQueue<Obj> priorityQueue = new PriorityQueue<>(comparator);
        priorityQueue.add(new Obj(null, initial));

        while (true) {
            Obj recent = priorityQueue.poll();
            assert recent != null;
            if (recent.getBoard().Win()) {
                Obj lastMove = new Obj(recent, recent.getBoard());
                addToResult(lastMove);
                totalmoves = result.size();
                System.out.println("Всего ходов: " + totalmoves);
                break;
            }

            if (!checkedItems.contains(recent.getBoard())) {

                for (GameBoard neighbour : recent.getBoard().neighbors()) {
                    boolean logic1 = !containsInPath(recent, neighbour);
                    boolean logic2 = neighbour != null;
                    if (logic1 && logic2) {
                        priorityQueue.add(new Obj(recent, neighbour));
                    }
                }
            }
            checkedItems.add(recent.getBoard());
        }
    }

    private int measure(Obj element){
        Obj temp = element;
        int c= 0;
        int measure = element.getBoard().getH();
        while (true){
            c++;
            temp = temp.getLast();
            if(temp == null) {
                return measure + c;
            }
        }
    }

    private void addToResult(Obj element) {
        Obj temp = element;
        while (true) {
            temp = temp.getLast();
            if (temp == null) {
                Collections.reverse(result);
                break;
            }
            result.add(temp.getBoard());
        }
    }

    private boolean containsInPath(Obj element, GameBoard board){
        Obj temp =  element;
        while (true){
            if(temp.getBoard().equals(board)) return true;
            temp = temp.getLast();
            if(temp == null) return false;
        }
    }

    public List<GameBoard> solution() {
        return result;
    }
}

