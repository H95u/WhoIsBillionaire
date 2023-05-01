import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoardManage {
    private ArrayList<LeaderBoard> leaderBoards;

    public LeaderBoardManage() {
        leaderBoards = new ArrayList<>();
    }

    public void addLeaderBoard(LeaderBoard leaderBoard) {
        leaderBoards.add(leaderBoard);
    }

    public void sortLeaderBoard() {
        Collections.sort(leaderBoards);
    }

    public void showLeaderBoard() {
        System.out.println("----------------- LEADER BOARD -----------------");
        System.out.printf("%-20s%-20s%-20s%n", "Name", "Score", "Money");
        for (LeaderBoard leaderBoard : leaderBoards) {
            leaderBoard.displayLeaderBoard();
        }
    }
}
