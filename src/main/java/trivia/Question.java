package trivia;

public class Question {
    
    private final QuestionType type;
    private final int index;

    public Question(QuestionType type, int index) {
        this.type = type;
        this.index = index;
    }

    public String generatePrefix() {
        return this.type + " Question " + this.index;
    }

}
