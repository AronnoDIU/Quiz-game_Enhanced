import java.util.List;

record Question(String questionText, List<String> options, int correctOption, String hint) {
    public String questionText() {
        return questionText;
    }

    public List<String> options() {
        return options;
    }

    public int correctOption() {
        return correctOption;
    }

    public String hint() {
        return hint;
    }
}