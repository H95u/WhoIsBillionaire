package model;

public class ScoreBoard {
    private int currentScore;
    private double money;

    public ScoreBoard() {
        currentScore = 0;
        money = 0;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void resetScore() {
        currentScore = 0;
    }

    public void updateScore(double money) {
        currentScore += 1;
        this.money += money;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void displayScore() {
        System.out.println("\u001B[34mCurrent score : " + currentScore);
        System.out.println("Current money : " + money + "\033[0m");
    }
}
