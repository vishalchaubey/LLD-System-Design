package snakeLadder.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import snakeLadder.model.Board;
import snakeLadder.model.Player;
import snakeLadder.utils.Dice;

public class GameService {
    private final Board board;
    private final Dice dice;
    private final Queue<Player> playersQueue;
    Map<Integer, Integer> snakesAndLaddersMap = new HashMap<Integer, Integer>();
    
    public GameService( List<String> playerNames) {
        // Initialize the players queue
        this.playersQueue = new LinkedList<>();
        this.board = new Board(100);
        this.dice = new Dice();

        for (String name : playerNames) {
            playersQueue.add(new Player(name));
        }
    
        // Adding a snake to the game
    board.addSnake(14, 4); // Snake from 14 to 4
    board.addSnake(16, 6); // Snake from 16 to 6
    board.addSnake(18, 8); // Snake from 18 to 8
    board.addSnake(20, 10); // Snake from 20 to 10
    board.addSnake(24, 12); // Snake from 24 to 12
    board.addSnake(28, 18); // Snake from 28 to 18
    board.addSnake(30, 20); // Snake from 30 to 20

    // adding a ladder to the game
    board.addLadder(3, 22); // Ladder from 3 to 22
    board.addLadder(5, 8); // Ladder from 5 to 8
    board.addLadder(11, 26); // Ladder from 11 to 26
    board.addLadder(17, 29); // Ladder from 17 to 29
    board.addLadder(19, 27); // Ladder from 19 to 27
    }

    public void startGame(){
       while(true){
        Player currentPlayer = playersQueue.poll();
        int roll = dice.roll();
        System.out.println("current player: " + currentPlayer.getName() + " rolled a " + roll);
        int newPosition = currentPlayer.getPosition() + roll;
        if (newPosition > board.getBoardSize()) {
            System.out.println("Player " + currentPlayer.getName() + " rolled too high and stays at position " + currentPlayer.getPosition());
            playersQueue.add(currentPlayer); // Re-add the player to the queue
            continue; // Skip to the next player
        }
        newPosition = board.getNextPosition(newPosition);
        currentPlayer.setPosition(newPosition);
        System.out.println("Player " + currentPlayer.getName() + " moved to position " + newPosition);
        if (newPosition == board.getBoardSize()) {
            currentPlayer.setWinner(true);
            System.out.println("Player " + currentPlayer.getName() + " wins the game!");
            break; // Exit the loop if a player wins
        }
        playersQueue.add(currentPlayer); // Re-add the player to the queue
        printPlayerPositions();

       }

    }
    private void printPlayerPositions() {
        System.out.println("Current Positions:");
        for (Player p : playersQueue) {
            System.out.println(p.getName() + " is at " + p.getPosition());
        }
        System.out.println("-------------------------");
    }
    
}