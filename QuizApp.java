import java.util.*;

class QuizQuestion {
    private String question;
    private List<String> options;
    private int correctOptionIndex;

    public QuizQuestion(String question, List<String> options, int correctOptionIndex) {
        this.question = question;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public boolean isCorrect(int userAnswerIndex) {
        return userAnswerIndex == correctOptionIndex;
    }
}

class Quiz {
    private List<QuizQuestion> questions;
    private int score;

    public Quiz(List<QuizQuestion> questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Java Quiz!");
        System.out.println("Answer the following questions about Java:");

        for (int i = 0; i < questions.size(); i++) {
            QuizQuestion question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getQuestion());
            displayOptions(question.getOptions());

            System.out.print("Your answer (enter the option number): ");
            int userAnswerIndex = scanner.nextInt() - 1;

            if (question.isCorrect(userAnswerIndex)) {
                System.out.println("Correct! You earned a point.\n");
                score++;
            } else {
                System.out
                        .println("Incorrect. The correct answer was: " + (question.getCorrectOptionIndex() + 1) + "\n");
            }
        }

        displayResult();
    }

    private void displayOptions(List<String> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i));
        }
    }

    private void displayResult() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your final score: " + score + " out of " + questions.size());
        System.out.println("Thank you for playing!");
    }
}

public class QuizApp {
    public static void main(String[] args) {
        List<QuizQuestion> quizQuestions = new ArrayList<>();

        // Add Java-related quiz questions
        quizQuestions.add(new QuizQuestion("What is the main purpose of Java?", Arrays.asList(
                "A. Web Development",
                "B. Mobile Development",
                "C. Enterprise Applications",
                "D. Game Development"), 2));

        quizQuestions.add(new QuizQuestion("Which keyword is used to declare a constant variable in Java?",
                Arrays.asList(
                        "A. var",
                        "B. const",
                        "C. final",
                        "D. static"),
                2));

        quizQuestions.add(new QuizQuestion("What is the output of the following code?\n" +
                "System.out.println(\"Java\" + 1 + 2);",
                Arrays.asList(
                        "A. Java12",
                        "B. Java3",
                        "C. Java 12",
                        "D. Compilation Error"),
                1));

        quizQuestions.add(new QuizQuestion("Which of the following is a Java interface?", Arrays.asList(
                "A. Runnable",
                "B. Serializable",
                "C. Cloneable",
                "D. All of the above"), 3));

        // Create and start the quiz
        Quiz quiz = new Quiz(quizQuestions);
        quiz.startQuiz();
    }
}
