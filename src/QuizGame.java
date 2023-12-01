import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizGame {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new Question("What is the capital of France?", List.of("Berlin", "Madrid", "Paris", "Rome"), 3, "It's known as the 'City of Love'."));
        questions.add(new Question("Which planet is known as the Red Planet?", List.of("Earth", "Mars", "Venus", "Jupiter"), 2, "It's named after the Roman god of war."));
        questions.add(new Question("What is the largest mammal?", List.of("Elephant", "Blue Whale", "Giraffe", "Lion"), 2, "It's the largest animal on Earth."));

        // Shuffle the questions for variety
        Collections.shuffle(questions);

        // Choose quiz mode (you can change this to QuizMode.RANDOM or QuizMode.TIMED)
        QuizMode quizMode = QuizMode.NORMAL;

        Quiz quiz = new Quiz(questions, quizMode);
        quiz.start();
    }
}
