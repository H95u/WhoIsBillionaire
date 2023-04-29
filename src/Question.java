import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String questionTitle;
    private String[] answerOptions;
    private int correctAnswerIndex;

    public Question() {
    }

    public Question(int id, String questionTitle, String[] answerOptions, int correctAnswerIndex) {
        this.id = id;
        this.questionTitle = questionTitle;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(String[] answerOptions) {
        this.answerOptions = answerOptions;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    @Override
    public String toString() {
        return "Question : " + id + "," +
                questionTitle;
    }

    public void displayQuestion(int index) {
        QuestionDisplayHelper.displayQuestion(this, index);
    }
}
