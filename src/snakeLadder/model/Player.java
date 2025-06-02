package snakeLadder.model;

public class Player {
    String name;
    int position;
    int score;
    boolean isWinner;   

    public Player(String name) {
        this.name = name;
        this.position = 0; // Starting position
        this.score = 0; // Initial score
        this.isWinner = false; // Initially not a winner
    }
    public String getName() {
        return name;
    }
    public int getPosition() {
        return position;
    }
    public int getScore() {
        return score;
    }
    public boolean isWinner() {
        return isWinner;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void setWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }
}