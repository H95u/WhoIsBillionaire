package model;

import java.util.ArrayList;

public class GamePlay {
    private ArrayList<Question> questionsList;

    private boolean isPlaying;

    private ScoreBoard scoreBoard;

    public GamePlay() {
    }

    public GamePlay(ArrayList<Question> questionsList, ScoreBoard scoreBoard) {
        this.questionsList = questionsList;
        this.isPlaying = false;
        this.scoreBoard = scoreBoard;
    }

    public ArrayList<Question> getQuestionsList() {
        return questionsList;
    }

    public void setQuestionsList(ArrayList<Question> questionsList) {
        this.questionsList = questionsList;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
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
        isPlaying = false;
        scoreBoard.resetScore();
    }

}
