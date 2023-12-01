import java.util.List;

class Question {
    private final String questionText;
    private final List<String> options;
    private final int correctOption;
    private final String hint;

    public Question(String questionText, List<String> options, int correctOption, String hint) {
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.hint = hint;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public int getCorrectOption() {
        return correctOption;
    }

    public String getHint() {
        return hint;
    }
}