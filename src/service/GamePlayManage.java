package service;

import model.*;
import service.QuestionManage;

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
        if (questions.size() >= 15) {
            while (randomQuestions.size() < 15) {
                int randomIndex = random.nextInt(questions.size());
                Question question = questions.get(randomIndex);
                if (!randomQuestions.contains(question)) {
                    randomQuestions.add(question);
                }
            }
        } else {
            System.out.println("Not have enough question !!");
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
            String input = scanner.nextLine();
            int answer = getAnswerByInput(input);
            if (answer != -1) {
                if (answer >= 5 && answer <= 8) {
                    QuestionDisplayHelper.useHelp(currentQuestion, answer);
                } else if (answer == 9) {
                    stopPlay();
                } else if (currentQuestion.getAnswerOptions()[answer - 1].equals("x")) {
                    System.out.println("\033[31mYour answer is removed !!\033[0m");
                } else {
                    if (currentQuestion.getCorrectAnswerIndex() == answer) {
                        index = correctAnswer(index);
                    } else {
                        gameOver(index);
                        break;
                    }
                }
            } else {
                System.out.println("\033[31mWrong input !!\033[0m");
            }
        }

    }

    private int correctAnswer(int index) {
        gamePlay.updateScoreBoard(getBounty(index));
        index++;
        System.out.println("\033[31mExactly !! Your answer is correct !! Process to the next question !!\033[0m");
        if (index == 15) {
            System.out.println("\033[33mCongratulation !! You have won the game !!\033[0m");
            String userName = getLoggingUserName();
            LeaderBoard leaderBoard = new LeaderBoard(userName, scoreBoard.getCurrentScore(), scoreBoard.getMoney());
            leaderBoardManage.addLeaderBoard(leaderBoard);
        }
        return index;
    }

    private void gameOver(int index) {
        if (index > 4 && index < 10) {
            scoreBoard.setMoney(Bounty.QUESTION5);
        } else if (index >= 10 && index < 15) {
            scoreBoard.setMoney(Bounty.QUESTION10);
        } else scoreBoard.setMoney(0);
        String userName = getLoggingUserName();
        LeaderBoard leaderBoard = new LeaderBoard(userName, scoreBoard.getCurrentScore(), scoreBoard.getMoney());
        leaderBoardManage.addLeaderBoard(leaderBoard);
        System.out.println("\033[31mYour answer is incorrect !! Game over !!\033[0m");
        System.out.println("\033[31mYour score " + scoreBoard.getCurrentScore() + " questions\033[0m");
        System.out.println("\033[31mYou win " + scoreBoard.getMoney() + " USD \033[0m");
        gamePlay.endGame();
        gamePlay.resetGame();
        QuestionDisplayHelper.resetHelpList();
    }

    public double getBounty(int index) {
        double[] bounty = {
                Bounty.QUESTION1,
                Bounty.QUESTION2,
                Bounty.QUESTION3,
                Bounty.QUESTION4,
                Bounty.QUESTION5,
                Bounty.QUESTION6,
                Bounty.QUESTION7,
                Bounty.QUESTION8,
                Bounty.QUESTION9,
                Bounty.QUESTION10,
                Bounty.QUESTION11,
                Bounty.QUESTION12,
                Bounty.QUESTION13,
                Bounty.QUESTION14,
                Bounty.QUESTION15
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

    public int getAnswerByInput(String input) {
        if (input.equalsIgnoreCase("A")) return 1;
        else if (input.equalsIgnoreCase("B")) return 2;
        else if (input.equalsIgnoreCase("C")) return 3;
        else if (input.equalsIgnoreCase("D")) return 4;
        else if (input.equalsIgnoreCase("5")) return 5;
        else if (input.equalsIgnoreCase("6")) return 6;
        else if (input.equalsIgnoreCase("7")) return 7;
        else if (input.equalsIgnoreCase("8")) return 8;
        else if (input.equalsIgnoreCase("9")) return 9;
        else return -1;
    }

    public void stopPlay() {
        String userName = getLoggingUserName();
        LeaderBoard leaderBoard = new LeaderBoard(userName, scoreBoard.getCurrentScore(), scoreBoard.getMoney());
        leaderBoardManage.addLeaderBoard(leaderBoard);
        System.out.println("\033[31mYour score " + scoreBoard.getCurrentScore() + " questions\033[0m");
        System.out.println("\033[31mYou win " + scoreBoard.getMoney() + " USD \033[0m");
        gamePlay.endGame();
        gamePlay.resetGame();
        QuestionDisplayHelper.resetHelpList();
    }
}
