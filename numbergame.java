 import java.util.Scanner;
import java.util.Random;

public class numbergame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int maxAttempts = 5; 
        int lowerBound = 1;
        int upperBound = 100;
        int score = 0;
        String playAgain;

        System.out.println("🎮 Welcome to the Number Guessing Game!");

        do {
            int targetNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            int attemptsLeft = maxAttempts;
            boolean isCorrect = false;

            System.out.println("\nGuess a number between " + lowerBound + " and " + upperBound);
            System.out.println("You have " + maxAttempts + " attempts.");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attemptsLeft--;

                if (guess == targetNumber) {
                    System.out.println(" Correct! You guessed the number!");
                    score++;
                    isCorrect = true;
                    break;
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                System.out.println("Attempts left: " + attemptsLeft);
            }

            if (!isCorrect) {
                System.out.println("You've run out of attempts! The number was: " + targetNumber);
            }

            System.out.print("\nDo you want to play another round? (yes/no): ");
            scanner.nextLine(); // Clear the buffer
            playAgain = scanner.nextLine().toLowerCase();

        } while (playAgain.equals("yes"));

        System.out.println("\n Game Over! Your total score: " + score);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}

    

