import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        System.out.println("Welcome to the Number Guessing Game!");

        playGame();

        System.out.println("Thanks for playing!");
    }

    static void playGame() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int lower = 1;
        int upper = 100;
        int secret = random.nextInt(upper - lower + 1) + lower;
        int maxAttempts = 10;
        int attempts = 0;
        int score = 0;

        while (attempts < maxAttempts) {
            System.out.printf("Guess the number between %d and %d: ", lower, upper);
            int userGuess = scanner.nextInt();

            if (userGuess == secret) {
                System.out.println("Congratulations! You guessed the correct number.");
                score++;
                break;
            } else if (userGuess < secret) {
                System.out.println("Too low. Try again.");
            } else {
                System.out.println("Too high. Try again.");
            }

            attempts++;
        }

        System.out.println("Your score: " + score);
        System.out.print("Do you want to play again? (yes/no): ");
        String playAgain = scanner.next().toLowerCase();

        if (playAgain.equals("yes")) {
            playGame();
        }
    }
}
