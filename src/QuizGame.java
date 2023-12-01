import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class QuizGame {
    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();
        questions.add(new
                Question("What is the capital of France?",
                List.of("Berlin", "Madrid", "Paris", "Rome"),
                3, "It's known as the 'City of Love'."));
        questions.add(new
                Question("Which planet is known as the Red Planet?",
                List.of("Earth", "Mars", "Venus", "Jupiter"),
                2, "It's named after the Roman god of war."));
        questions.add(new
                Question("What is the largest mammal?",
                List.of("Elephant", "Blue Whale", "Giraffe", "Lion"),
                2, "It's the largest animal on Earth."));
        questions.add(new
                Question("What is the currency of Japan?",
                List.of("Won", "Yen", "Ringgit", "Dollar"),
                2, "It features famous historical figures on its banknotes."));
        questions.add(new
                Question("Which country is known as the Land of the Midnight Sun?",
                List.of("Norway", "Sweden", "Finland", "Canada"),
                1, "It experiences polar day during summer months."));
        questions.add(new
                Question("Who wrote 'To Kill a Mockingbird'?",
                List.of("Harper Lee", "J.K. Rowling", "Ernest Hemingway", "Jane Austen"),
                1, "The novel deals with racial injustice in the American South."));
        questions.add(new
                Question("Which famous physicist developed the theory of relativity?",
                List.of("Isaac Newton", "Niels Bohr", "Albert Einstein", "Stephen Hawking"),
                3, "He formulated the famous equation E=mc^2."));
        questions.add(new
                Question("What is the largest planet in our solar system?",
                List.of("Mars", "Jupiter", "Saturn", "Neptune"),
                1, "It has a massive Great Red Spot."));
        questions.add(new
                Question("In which year did the Berlin Wall fall?",
                List.of("1985", "1989", "1991", "1995"),
                2, "It marked the end of the Cold War."));
        questions.add(new
                Question("Which ocean is the largest?",
                List.of("Atlantic", "Indian", "Pacific", "Arctic"),
                3, "It covers more than 60 million square miles."));
        questions.add(new
                Question("Who painted the Mona Lisa?",
                List.of("Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"),
                2, "It's displayed in the Louvre Museum."));
        questions.add(new
                Question("What is the smallest prime number?",
                List.of("0", "1", "2", "3"),
                2, "It is the only even prime number."));
        questions.add(new
                Question("Which bird is known for its ability to mimic human speech?",
                List.of("Penguin", "Parrot", "Eagle", "Ostrich"),
                1, "It's often found in the Southern Hemisphere."));
        questions.add(new
                Question("What is the largest continent by land area?",
                List.of("North America", "Europe", "Asia", "Africa"),
                3, "It is home to the world's highest mountain, Mount Everest."));
        questions.add(new
                Question("Who is the author of 'Pride and Prejudice'?",
                List.of("Jane Austen", "Charlotte Brontë", "Charles Dickens", "Leo Tolstoy"),
                1, "The novel explores themes of love and social standing."));
        questions.add(new
                Question("Which country is home to the kangaroo?",
                List.of("Australia", "New Zealand", "Madagascar", "Indonesia"),
                1, "It's also home to the koala and the dingo."));
        questions.add(new
                Question("Which country is known as the Land of the Rising Sun?",
                List.of("China", "Japan", "Korea", "Vietnam"),
                2, "It's an island nation in East Asia."));
        questions.add(new
                Question("Which country is known as the Land of the Thunder Dragon?",
                List.of("Bhutan", "Nepal", "Tibet", "Myanmar"),
                1, "It's a landlocked country in South Asia."));
        questions.add(new
                Question("Which country is known as the Land of Smiles?",
                List.of("Thailand", "Vietnam", "Laos", "Cambodia"),
                1, "It's a popular tourist destination in Southeast Asia."));
        questions.add(new
                Question("Which country is known as the Land of a Thousand Hills?",
                List.of("Rwanda", "Burundi", "Uganda", "Kenya"),
                1, "It's a landlocked country in East Africa."));
        questions.add(new
                Question("Which country is known as the Land of the Long White Cloud?",
                List.of("Australia", "New Zealand", "Fiji", "Papua New Guinea"),
                2, "It's a country in the southwestern Pacific Ocean."));
        questions.add(new
                Question("Which country is known as the Land of Fire and Ice?",
                List.of("Iceland", "Greenland", "Norway", "Finland"),
                1, "It's a Nordic island country in the North Atlantic Ocean."));

        // Shuffle the questions for variety
        Collections.shuffle(questions); // Randomize the order of the questions in the list

        // Choose quiz mode (you can change this to QuizMode.RANDOM or QuizMode.TIMED)

        Scanner userInput = new Scanner(System.in);
        System.out.println("Choose quiz mode:");
        System.out.println("1. Normal");
        System.out.println("2. Random");
        System.out.println("3. Timed");
        System.out.print("Enter your choice: ");
        int choice = userInput.nextInt();

        QuizMode quizMode = switch (choice) { // Switch expression to choose quiz mode
            case 1 -> QuizMode.NORMAL; // If choice is 1, return QuizMode.NORMAL
            case 2 -> QuizMode.RANDOM; // If choice is 2, return QuizMode.RANDOM
            case 3 -> QuizMode.TIMED; // If choice is 3, return QuizMode.TIMED
            default -> {
                System.out.println("Invalid choice. Defaulting to normal mode.");
                yield QuizMode.NORMAL; // If choice is not 1, 2, or 3, return QuizMode.NORMAL
            }
        }; // End of switch expression

        Quiz quiz = new Quiz(questions, quizMode);
        quiz.start(); // Start the quiz
    }
}
