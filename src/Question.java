import java.util.List;

// If you prefer to use a record instead of a class:
record Question(String questionText, List<String> options, int correctOption, String hint) {
    public String questionText() {
        return questionText; // This is implicit, but I like to be explicit
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

    // If you want to override the toString() method:
    // @Override
    // public String toString() {
    //     return questionText + "\n" + options + "\n" + correctOption + "\n" + hint;
    // }
}

// OR, if you prefer to use a class instead of a record:

/*

class Question {
    private String questionText;
    private List<String> options;
    private int correctOption;
    private String hint;

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

*/