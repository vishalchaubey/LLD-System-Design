package snakeLadder;

import java.util.Arrays;
import java.util.List;

import snakeLadder.service.GameService;

public class Main {
public static void main(String[] args) {
    System.out.println("Welcome to the Snake and Ladder Game!");
    List<String> playerNames = Arrays.asList("Vishal", "Jyoti");
    GameService gameService = new GameService(playerNames);
    gameService.startGame();
    System.out.println("Game Over!");
    System.out.println("Thank you for playing!");
}
}
