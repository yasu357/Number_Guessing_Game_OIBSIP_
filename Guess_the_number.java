import java.util.Random;
import java.util.Scanner;

public class GuessTheNumberGame {

    public static void main(String[] args) {
        playGuessTheNumberGame();
    }

    private static void playGuessTheNumberGame() {
        Scanner scanner = new Scanner(System.in);
        new Random();

        int lowerBound = 1;
        int upperBound = 100;
        int maxAttempts = 5;
        int rounds = 3;
        int totalScore = 0;

        System.out.println("Welcome to Guess the Number Game!");

        for (int round = 1; round <= rounds; round++) {
            int randomNumber = generateRandomNumber(lowerBound, upperBound);
            int roundScore = 0;

            System.out.println("\nRound " + round + " - Guess the number between " + lowerBound + " and " + upperBound);

            for (int attempt = 1; attempt <= maxAttempts; attempt++) {
                int userGuess = getUserInput(scanner, lowerBound, upperBound, attempt);

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the number " + randomNumber + " in " + attempt + " attempts.");
                    roundScore += calculateScore(attempt, maxAttempts);
                    break;
                } else {
                    displayHint(userGuess, randomNumber);
                }

                if (attempt == maxAttempts) {
                    System.out.println("Sorry, you've run out of attempts. The correct number was: " + randomNumber);
                }
            }

            totalScore += roundScore;
            System.out.println("Round " + round + " Score: " + roundScore);
        }

        System.out.println("\nGame Over. Your Total Score is: " + totalScore);
        scanner.close();
    }

    private static int generateRandomNumber(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    private static int getUserInput(Scanner scanner, int lowerBound, int upperBound, int attempt) {
        int userGuess;
        while (true) {
            System.out.print("Attempt " + attempt + ": Enter your guess between " + lowerBound + " and " + upperBound + ": ");
            if (scanner.hasNextInt()) {
                userGuess = scanner.nextInt();
                if (isInRange(userGuess, lowerBound, upperBound)) {
                    break;
                } else {
                    System.out.println("Please enter a number within the specified range.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Consume invalid input
            }
        }
        return userGuess;
    }

    private static boolean isInRange(int number, int lowerBound, int upperBound) {
        return number >= lowerBound && number <= upperBound;
    }

    private static void displayHint(int userGuess, int randomNumber) {
        System.out.println("Your guess is " + (userGuess < randomNumber ? "too low" : "too high") + ". Try again.");
    }

    private static int calculateScore(int attempts, int maxAttempts) {
        return (int) Math.round((double) (maxAttempts - attempts + 1) / maxAttempts * 100);
    }
}
