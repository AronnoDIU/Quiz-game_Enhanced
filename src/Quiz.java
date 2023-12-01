import java.util.List;
import java.util.Scanner;
import java.util.Timer; // For timed quiz mode only (QuizMode.TIMED)
import java.util.TimerTask; // For timed quiz mode only (QuizMode.TIMED)

class Quiz {
    private final List<Question> questions; // List of questions to ask the user
    private int score; // Number of questions answered correctly
    private int currentQuestionIndex; // Index of the current question
    private final QuizMode quizMode; // Quiz mode (NORMAL, RANDOM, or TIMED)

    public Quiz(List<Question> questions, QuizMode quizMode) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.quizMode = quizMode;
    }

    public void start() {
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

        nextQuestion();

        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);

            if (quizMode == QuizMode.TIMED) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println("\nTime's up!");
                        // Removed nextLine()
                        timer.cancel();
                        nextQuestion();
                    }
                };
                timer.schedule(task, 30000); // 30000 milliseconds = 30 seconds
            }

            int userAnswer = getUserAnswer(userInput);

            if (userAnswer == currentQuestion.correctOption()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + currentQuestion.correctOption());
                if (!currentQuestion.hint().isEmpty()) {
                    System.out.println("Hint: " + currentQuestion.hint());
                }
                System.out.println();
            }
            // Now nextQuestion is always called
//            nextQuestion();

            if (quizMode != QuizMode.TIMED) {
                nextQuestion();
            }
        }

        System.out.println("Quiz completed! Your final score: " + score + " out of " + questions.size());
    }

    private void nextQuestion() {
        currentQuestionIndex++;
    }

    private void displayQuestion(Question question) {
        System.out.println(question.questionText());

        List<String> options = question.options();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.print("Your answer: ");
    }

    private int getUserAnswer(Scanner userInput) {
        int userAnswer = -1;
        while (userAnswer < 1 || userAnswer > questions.get(0).options().size()) {
            System.out.println("Choose an option between 1 and " + questions.get(0).options().size());
            try {
                userAnswer = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return userAnswer;
    }
}