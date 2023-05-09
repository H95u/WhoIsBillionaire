package controller;

import service.AccountManage;
import service.GamePlayManage;
import service.LeaderBoardManage;
import service.QuestionManage;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void userMenu(QuestionManage questionManage, AccountManage accountManage, LeaderBoardManage leaderBoardManage) {
        int choice = -1;
        do {
            System.out.println("\033[31m-------- USER ---------\033[0m");
            System.out.println("1.Play");
            System.out.println("2.Leader board");
            System.out.println("3.Update your account");
            System.out.println("0.Log out");
            System.out.println("PLS SELECT YOUR CHOICE!!");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    GamePlayManage gamePlayManage = new GamePlayManage(questionManage, leaderBoardManage);
                    gamePlayManage.startGame();
                    break;
                case 2:
                    leaderBoardManage.sortLeaderBoard();
                    leaderBoardManage.showLeaderBoard();
                    break;
                case 3:
                    accountManage.updateAccount();
                    break;
                case 0:
                    choice = 0;
                    break;
                default:
                    System.out.println("There is no that option!");
                    break;
            }
        } while (choice != 0);
    }

    public static void adminMenu(AccountManage accountManage, QuestionManage questionManage, LeaderBoardManage leaderBoardManage) {
        int choice = -1;
        do {
            System.out.println("\033[31m-------- ADMIN ---------\033[0m");
            System.out.println("1.Delete account");
            System.out.println("2.Add question");
            System.out.println("3.Delete question");
            System.out.println("4.display all question");
            System.out.println("5.display all account");
            System.out.println("6.Clear leader board");
            System.out.println("7.Show leader board");
            System.out.println("8.Remove all account");
            System.out.println("0.Log out");
            System.out.println("PLS SELECT YOUR CHOICE!!");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    accountManage.deleteAccount();
                    break;
                case 2:
                    questionManage.addQuestion();
                    break;
                case 3:
                    questionManage.deleteQuestion();
                    break;
                case 4:
                    questionManage.displayAllQuestion();
                    break;
                case 5:
                    accountManage.displayAllAccount();
                    break;
                case 6:
                    leaderBoardManage.resetLeaderBoard();
                    break;
                case 7:
                    leaderBoardManage.sortLeaderBoard();
                    leaderBoardManage.showLeaderBoard();
                    break;
                case 8:
                    accountManage.deleteAllAccount();
                    break;
                case 0:
                    choice = 0;
                    break;
                default:
                    System.out.println("There is no that option!");
                    break;
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        AccountManage accountManage = new AccountManage();
        QuestionManage questionManage = new QuestionManage();
        LeaderBoardManage leaderBoardManage = new LeaderBoardManage();
        int choice = -1;
        do {
            System.out.println("-------- MENU ---------");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forgot password");
            System.out.println("0. Exit");
            System.out.println("PLS SELECT YOUR CHOICE!!");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("Error " + e.getMessage());
                continue;
            }
            switch (choice) {
                case 1:
                    accountManage.createAccount();
                    break;
                case 2:
                    String userName = accountManage.login();
                    if (userName != null) {
                        if (userName.equals("hieu123")) adminMenu(accountManage, questionManage, leaderBoardManage);
                        else userMenu(questionManage, accountManage, leaderBoardManage);
                    }
                    break;
                case 3:
                    accountManage.findPassWord();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("There is no that option!");
                    break;
            }
        } while (choice != 0);
    }
}
