package snakeLadder.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int boardSize;
    private final Map<Integer, Integer> snakesAndLadders;
    
    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.snakesAndLadders = new HashMap<>();
    }
    
    public void addSnake(int head, int tail){
        if(head> tail && head <=boardSize && snakesAndLadders.size() < boardSize) {
            snakesAndLadders.put(head, tail);
        } else {
            throw new IllegalArgumentException("Invalid snake position");
        }
    }
    public void addLadder(int start, int end){
        if(start < end && end <= boardSize && snakesAndLadders.size() < boardSize) {
            snakesAndLadders.put(start, end);
        } else {
            throw new IllegalArgumentException("Invalid ladder position");
        }
    }
    public int getBoardSize() {
        return boardSize;
    }
    public int getNextPosition(int pos){
        return snakesAndLadders.getOrDefault(pos, pos);
    }
}
