import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GamePlayManage implements IOFileInterface<Question> {
    private String questionPath = "C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\question.txt";
    private String randomQuestionPath = "C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\randomQuestion.txt";
    private ArrayList<Question> questions;
    private ArrayList<Question> randomQuestions;
    private GamePlay gamePlay;
    private ScoreBoard scoreBoard;
    private QuestionManage questionManage;
    private Scanner scanner;

    public GamePlayManage(QuestionManage questionManage) {
        this.questions = questionManage.readFile(questionPath);
        this.randomQuestions = readFile(randomQuestionPath);
        this.scoreBoard = new ScoreBoard();
        this.gamePlay = new GamePlay(randomQuestions, scoreBoard);
        this.questionManage = questionManage;
        scanner = new Scanner(System.in);
    }

    @Override
    public void writeFile(ArrayList<Question> randomQuestions, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(randomQuestions);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Question> readFile(String path) {
        ArrayList<Question> randomQuestions = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            randomQuestions = (ArrayList<Question>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return randomQuestions;
    }

    public void createRandomQuestion() {
        Random random = new Random();
        ArrayList<Question> randomQuestions = new ArrayList<>();
        //create random 15 questions
        for (int i = 0; i < 15; i++) {
            int randomIndex = random.nextInt(questions.size());
            Question question = questions.get(randomIndex);
            randomQuestions.add(question);
        }
        writeFile(randomQuestions, randomQuestionPath);
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
            int answer = Integer.parseInt(scanner.nextLine());
            if (currentQuestion.getCorrectAnswerIndex() == answer) {
                gamePlay.updateScoreBoard(getBounty(index));
                index++;
                if (index == 15) {
                    System.out.println("Congratulation !! You have won the game !! ");
                    String userName = getLoggingUserName();
                    writeFile(userName, scoreBoard.getCurrentScore(), scoreBoard.getMoney());
                }
            } else if (answer >= 5 && answer <= 8) {
                QuestionDisplayHelper.useHelp(currentQuestion, answer, index);
            } else {
                gamePlay.endGame();
                gamePlay.resetGame();
                QuestionDisplayHelper.resetHelpList();
                System.out.println("Game over !!");
                break;
            }
        }
    }

    public void displayAllRandomQ() {
        for (Question randomQuestion : randomQuestions) {
            System.out.println(randomQuestion);
        }
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

    public void writeFile(String userName, int score, double money) {
        File file = new File("C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\leaderBoard.txt");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(userName + "," + score + "," + money + "\n");
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showLeaderBoard() {
        File file = new File("C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\leaderBoard.txt");
        String[] str = new String[0];
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String c;
            while ((c = bufferedReader.readLine()) != null) {
                str = c.split(",");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.printf("%-20s%-20s%-20s%n%-20s%-20s%s", "Name", "Score", "Money", str[0], str[1], str[2] + "\n");
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
