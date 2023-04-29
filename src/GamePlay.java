import java.util.ArrayList;

public class GamePlay {
    private ArrayList<Question> questionsList;
    private int currentQuestionIndex;
    private boolean isPlaying;
    private boolean isAnswered;
    private ScoreBoard scoreBoard;


    public GamePlay() {
    }

    public GamePlay(ArrayList<Question> questionsList, ScoreBoard scoreBoard) {
        this.questionsList = questionsList;
        this.currentQuestionIndex = 0;
        this.isPlaying = false;
        this.isAnswered = false;
        this.scoreBoard = scoreBoard;
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        this.currentQuestionIndex = currentQuestionIndex;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public boolean isAnswered() {
        return isAnswered;
    }

    public void setAnswered(boolean answered) {
        isAnswered = answered;
    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void setScoreBoard(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

    public void updateScoreBoard(double money) {
        scoreBoard.updateScore(money);
    }

    public void startGame() {
        isPlaying = true;
    }

    public void endGame() {
        isPlaying = false;
    }

    public void resetGame() {
        currentQuestionIndex = 0;
        isPlaying = false;
        isAnswered = false;
        scoreBoard.resetScore();
    }

}
