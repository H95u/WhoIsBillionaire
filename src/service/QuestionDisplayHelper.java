package service;

import model.Question;

import java.util.*;

public class QuestionDisplayHelper {
    private static TreeMap<Integer, String> helpList = new TreeMap<>();

    static {
        createHelpList();
    }

    public static void displayQuestionUseHelp(Question question) {
        System.out.println("\033[31m[HELP] : The correct answer is ... : "
                + getAnswerByIndex(question.getCorrectAnswerIndex()) + ". " +
                question.getAnswerOptions()[question.getCorrectAnswerIndex() - 1] + "\033[0m");
    }

    public static void displayQuestionUse5050(Question question) {
        int correctAnswer = question.getCorrectAnswerIndex() - 1;
        int wrongAnswer1 = getRandomWrongAnswerIndex(correctAnswer);
        int wrongAnswer2 = getRandomWrongAnswerIndex(correctAnswer);
        while (wrongAnswer2 == wrongAnswer1) {
            wrongAnswer2 = getRandomWrongAnswerIndex(correctAnswer);
        }
        question.getAnswerOptions()[wrongAnswer1] = "x";
        question.getAnswerOptions()[wrongAnswer2] = "x";
    }

    private static int getRandomWrongAnswerIndex(int correctAnswerIndex) {
        Random random = new Random();
        int wrongAnswerIndex = random.nextInt(4);
        while (wrongAnswerIndex == correctAnswerIndex) {
            wrongAnswerIndex = random.nextInt(4);
        }
        return wrongAnswerIndex;
    }


    public static void showHelpList() {
        System.out.println("\033[32;1mAvailable helps:");
        if (helpList.isEmpty()) {
            System.out.println("You have no help !!");
        } else {
            for (int key : helpList.keySet()) {
                System.out.printf("%d. %s\n", key, helpList.get(key));
            }
        }
        System.out.println("Select help you want !!\033[0m");
    }


    public static void useHelp(Question question, int answer) {
        if (answer >= 5 && answer <= 8) {
            if (helpList.isEmpty()) {
                System.out.println("You have no help !!");
            } else {
                if (helpList.get(answer) == null) {
                    System.out.println("Wrong input !!");
                } else {
                    if (answer == 5) {
                        displayQuestionUse5050(question);
                        helpList.remove(answer);
                    } else {
                        displayQuestionUseHelp(question);
                        helpList.remove(answer);
                    }
                }
            }
        }
    }

    public static void resetHelpList() {
        helpList.clear();
        createHelpList();
    }

    private static void createHelpList() {
        helpList.put(5, "50/50");
        helpList.put(6, "Call a friend");
        helpList.put(7, "Ask the audience");
        helpList.put(8, "Consult with the team");
    }

    private static String getAnswerByIndex(int index) {
        if (index == 1) return "A";
        else if (index == 2) return "B";
        else if (index == 3) return "C";
        else return "D";
    }
}
