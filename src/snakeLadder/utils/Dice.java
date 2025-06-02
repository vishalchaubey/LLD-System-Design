package snakeLadder.utils;

public class Dice {
    private static final int MAX_DICE_VALUE = 6;
    private static final int MIN_DICE_VALUE = 1;

    // Simulates rolling a dice and returns a random value between 1 and 6
    public static int roll() {
        return (int) (Math.random() * MAX_DICE_VALUE) + MIN_DICE_VALUE;
    }
    
}
