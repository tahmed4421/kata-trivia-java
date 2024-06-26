package trivia;

public enum QuestionType {
    POP("Pop"),
    SCIENCE("Science"),
    SPORTS("Sports"),
    ROCK("Rock"),
    GEOGRAPHY("Geography");

    public final String label;

    QuestionType(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return this.label;
    }
}
