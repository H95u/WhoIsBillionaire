import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GamePlayManage {
    private String questionPath = "C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\question.txt";
    private ArrayList<Question> questions;
    private ArrayList<Question> randomQuestions;
    private GamePlay gamePlay;
    private ScoreBoard scoreBoard;
    private LeaderBoardManage leaderBoardManage;
    private Scanner scanner;

    public GamePlayManage(QuestionManage questionManage, LeaderBoardManage leaderBoardManage) {
        this.questions = questionManage.readFile(questionPath);
        this.scoreBoard = new ScoreBoard();
        this.gamePlay = new GamePlay(randomQuestions, scoreBoard);
        this.leaderBoardManage = leaderBoardManage;
        scanner = new Scanner(System.in);
    }

    public void createRandomQuestion() {
        Random random = new Random();
        randomQuestions = new ArrayList<>();
        //create random 15 questions
        for (int i = 0; i < 15; i++) {
            int randomIndex = random.nextInt(questions.size());
            Question question = questions.get(randomIndex);
            randomQuestions.add(question);
        }
    }

    public void startGame() {
        createRandomQuestion();
        gamePlay.startGame();
        int index = 0;
        showGamePlay(index);
    }

    private void showGamePlay(int index) {
        while (gamePlay.isPlaying() && index < randomQuestions.size()) {
            scoreBoard.displayScore();
            QuestionDisplayHelper.showHelpList();
            Question currentQuestion = randomQuestions.get(index);
            currentQuestion.displayQuestion(index);
            int answer = 0;
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (currentQuestion.getCorrectAnswerIndex() == answer) {
                index = correctAnswer(index);
            } else if (answer >= 5 && answer <= 8) {
                QuestionDisplayHelper.useHelp(currentQuestion, answer);
            } else {
                gameOver();
                break;
            }
        }
    }

    private int correctAnswer(int index) {
        gamePlay.updateScoreBoard(getBounty(index));
        index++;
        if (index == 15) {
            System.out.println("\033[33mCongratulation !! You have won the game !!\033[0m");
            String userName = getLoggingUserName();
            LeaderBoard leaderBoard = new LeaderBoard(userName, scoreBoard.getCurrentScore(), scoreBoard.getMoney());
            leaderBoardManage.addLeaderBoard(leaderBoard);
        }
        return index;
    }

    private void gameOver() {
        String userName = getLoggingUserName();
        LeaderBoard leaderBoard = new LeaderBoard(userName, scoreBoard.getCurrentScore(), scoreBoard.getMoney());
        leaderBoardManage.addLeaderBoard(leaderBoard);
        gamePlay.endGame();
        gamePlay.resetGame();
        QuestionDisplayHelper.resetHelpList();
        System.out.println("\033[31mGame over !!\033[0m");
    }

    public double getBounty(int index) {
        double[] bounty = {
                Bounty.getQuestion1(),
                Bounty.getQuestion2(),
                Bounty.getQuestion3(),
                Bounty.getQuestion4(),
                Bounty.getQuestion5(),
                Bounty.getQuestion6(),
                Bounty.getQuestion7(),
                Bounty.getQuestion8(),
                Bounty.getQuestion9(),
                Bounty.getQuestion10(),
                Bounty.getQuestion11(),
                Bounty.getQuestion12(),
                Bounty.getQuestion13(),
                Bounty.getQuestion14(),
                Bounty.getQuestion15()
        };
        return bounty[index];
    }

    public String getLoggingUserName() {
        ArrayList<Account> loggingUser = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream
                    ("C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\loggingUserName.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            loggingUser = (ArrayList<Account>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return loggingUser.get(0).getUserName();
    }
}
