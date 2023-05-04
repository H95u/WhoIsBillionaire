import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderBoardManage implements IOFileInterface<LeaderBoard> {
    private ArrayList<LeaderBoard> leaderBoards;
    private String leaderBoardPath = "C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\leaderBoard.txt";

    public LeaderBoardManage() {
        leaderBoards = readFile(leaderBoardPath);
    }

    public void addLeaderBoard(LeaderBoard leaderBoard) {
        leaderBoards.add(leaderBoard);
        writeFile(leaderBoards, leaderBoardPath);
    }

    public void sortLeaderBoard() {
        Collections.sort(leaderBoards);
    }

    public void resetLeaderBoard() {
        leaderBoards.clear();
        writeFile(leaderBoards, leaderBoardPath);
    }

    public void showLeaderBoard() {
        System.out.println("\033[31m----------------- LEADER BOARD -----------------\033[0m");
        System.out.printf("%-20s%-20s%-20s%n", "Name", "Score", "Money");
        for (LeaderBoard leaderBoard : leaderBoards) {
            leaderBoard.displayLeaderBoard();
        }
    }

    @Override
    public void writeFile(ArrayList<LeaderBoard> leaderBoards, String leaderBoardPath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(leaderBoardPath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(leaderBoards);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<LeaderBoard> readFile(String leaderBoardPath) {
        ArrayList<LeaderBoard> newLeaderBoards = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(leaderBoardPath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            newLeaderBoards = (ArrayList<LeaderBoard>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newLeaderBoards;
    }
}
