public class LeaderBoard implements Comparable<LeaderBoard> {
    private String name;
    private int score;
    private double money;

    public LeaderBoard() {
    }

    public LeaderBoard(String name, int score, double money) {
        this.name = name;
        this.score = score;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "LeaderBoard{" +
                "name='" + name + '\'' +
                ", score=" + score +
                ", money=" + money +
                '}';
    }

    public void displayLeaderBoard() {
        System.out.printf("%-20s%-20s%-20s%n", name, score, money);
    }

    @Override
    public int compareTo(LeaderBoard o) {
        return o.getScore() - this.score;
    }
}
