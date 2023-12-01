import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Quiz {
    private final List<Question> questions;
    private int score;
    private int currentQuestionIndex;
    private final QuizMode quizMode;

    public Quiz(List<Question> questions, QuizMode quizMode) {
        this.questions = questions;
        this.score = 0;
        this.currentQuestionIndex = 0;
        this.quizMode = quizMode;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        if (quizMode == QuizMode.TIMED) {
            System.out.println("You have 30 seconds for each question.");
        }

        while (currentQuestionIndex < questions.size()) {
            Question currentQuestion = questions.get(currentQuestionIndex);
            displayQuestion(currentQuestion);

            if (quizMode == QuizMode.TIMED) {
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    public void run() {
                        System.out.println("\nTime's up!");
                        scanner.nextLine(); // Consume any remaining input
                        timer.cancel();
                        nextQuestion();
                    }
                };
                timer.schedule(task, 30000); // 30 seconds
            }

            int userAnswer = getUserAnswer(scanner);

            if (userAnswer == currentQuestion.getCorrectOption()) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is: " + currentQuestion.getCorrectOption());
                if (!currentQuestion.getHint().isEmpty()) {
                    System.out.println("Hint: " + currentQuestion.getHint());
                }
                System.out.println();
            }

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
        System.out.println(question.getQuestionText());

        List<String> options = question.getOptions();
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }

        System.out.print("Your answer: ");
    }

    private int getUserAnswer(Scanner userInput) {
        int userAnswer = -1;
        while (userAnswer < 1 || userAnswer > questions.get(0).getOptions().size()) {
            System.out.println("Choose an option between 1 and " + questions.get(0).getOptions().size());
            try {
                userAnswer = Integer.parseInt(userInput.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return userAnswer;
    }
}