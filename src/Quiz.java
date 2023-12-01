import java.util.List;
import java.util.Scanner;
import java.util.Timer; // For timed quiz mode only (QuizMode.TIMED)
import java.util.TimerTask; // For timed quiz mode only (QuizMode.TIMED)

class Quiz {
    private final List<Question> questions; // List of questions to ask the user
    private int score; // Number of questions answered correctly
    private int currentQuestionIndex; // Index of the current question
    private final QuizMode quizMode; // Quiz mode (NORMAL, RANDOM, or TIMED)
    private boolean hasTimedOut; // New field to track whether the timer has expired

    public Quiz(List<Question> questions, QuizMode quizMode) {
        this.questions = questions;
        this.score = 0; // Initialize score to 0
        this.currentQuestionIndex = 0; // Initialize current question index to 0
        this.quizMode = quizMode;
    }

    public void start() { // Start the quiz (ask questions and get answers)
        Scanner userInput = new Scanner(System.in);

        System.out.println("Welcome to the Quiz-based Game!");
        System.out.println("You will be asked " + questions.size() + " questions.");
        System.out.println("Press Enter to continue.");
        userInput.nextLine(); // Wait for user to press Enter
        System.out.println();

        // Display quiz mode (NORMAL, RANDOM, or TIMED)
        System.out.println("Quiz mode: " + quizMode);
        System.out.println();

        // Display instructions based on quiz mode
        if (quizMode == QuizMode.NORMAL) {
            System.out.println("Questions will be asked in order.");
            System.out.println("Press Enter to continue.");
            userInput.nextLine(); // Wait for user to press Enter
            System.out.println();
        }

        if (quizMode == QuizMode.TIMED) {
            System.out.println("You have 30 seconds for each question.");
        }

        if (quizMode == QuizMode.RANDOM) {
            System.out.println("Questions will be asked in random order.");
            System.out.println("Press Enter to continue.");
            userInput.nextLine(); // Wait for user to press Enter
            System.out.println();
        }

        nextQuestion(); // Display the first question (and options) to the user

        // Ask questions until the user has answered all of them
        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion); // Display the current question and options

            // If quiz mode is TIMED, start a timer for 30 seconds
            if (quizMode == QuizMode.TIMED && !hasTimedOut) {
                Timer timer = new Timer(); // Create a new timer

                // Create a new timer task that runs after 30 seconds
                TimerTask task = new TimerTask() {

                    // This method will be called after 30 seconds have elapsed (the timer has expired)
                    public void run() {
                        if (!hasTimedOut) { // Check if the timer has expired
                            System.out.println("\nTime's up!");
                            displayCorrectAnswer(currentQuestion); // Display the correct answer
                            hasTimedOut = true; // Set-hasTimedOut to true
                            timer.cancel(); // Cancel the timer
                            nextQuestion(); // Go to the next question
                        }
                    }
                };
                // Start the timer (the timer task will run after 30 seconds)
                timer.schedule(task, 30000); // 30000 milliseconds = 30 seconds
            }

            int userAnswer = getUserAnswer(userInput); // Get the user's answer

            // If quiz mode is TIMED and the timer has not expired, cancel the timer
            if (!hasTimedOut) {
                // Check if the user's answer is correct
                if (userAnswer == currentQuestion.correctOption()) {
                    System.out.println("Correct!\n");
                    score++; // Increment score by 1
                } else {
                    System.out.println("Incorrect.");
                    displayCorrectAnswer(currentQuestion);
                }
            }

            nextQuestion(); // Go to the next question
        }

        System.out.println("Quiz completed! Your final score: "
                + score + " out of " + questions.size());
    }


    private void nextQuestion() { // Go to the next question
        currentQuestionIndex++; // Increment current question index by 1

        // If quiz mode is NORMAL or TIMED, display the next question
        if (currentQuestionIndex < questions.size()) {
            System.out.println("Press Enter for the next question.");
            new Scanner(System.in).nextLine(); // Wait for user to press Enter
            System.out.println();
        }
    }

    // Display the correct answer and hint (if any) for the current question
    private void displayCorrectAnswer(Question question) {
        System.out.println("The correct answer is: " + question.correctOption());

        // If the question has a hint, display it to the user
        if (!question.hint().isEmpty()) {
            System.out.println("Hint: " + question.hint());
        }
        System.out.println();
    }

    // Display the current question and options to the user
    private void displayQuestion(Question question) {
        System.out.println(question.questionText());

        // Get the list of options for the current question
        List<String> options = question.options();

        // Display the options to the user (numbered from 1 to n) and get their answer
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.print("Your answer: ");
    }

    // Get the user's answer to the current question (must be a number between 1 and n)
    private int getUserAnswer(Scanner userInput) {
        int userAnswer = -1; // Initialize user answer to -1 (invalid answer)

        // Keep asking for user input until they enter a valid answer
        while (userAnswer < 1 || userAnswer > questions.get(0).options().size()) {
            System.out.println("Choose an option between 1 and " + questions.get(0).options().size());
            try {
                // Get the user's answer (must be a number between 1 and n)
                userAnswer = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) { // If the user enters a non-numeric value
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return userAnswer; // Return the user's answer
    }
}