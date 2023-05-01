import java.util.*;

public class QuestionDisplayHelper {
    private static TreeMap<Integer, String> helpList = new TreeMap<>();

    static {
        createHelpList();
    }

    public static void displayQuestion(Question question, int index) {
        System.out.printf("%s\n%s%-20s%s\n%s%-20s%s\n",
                "Question " + (index + 1) + " : " + question.getQuestionTitle() + " ? ",
                "1." + question.getAnswerOptions()[0], "", "2." + question.getAnswerOptions()[1],
                "3." + question.getAnswerOptions()[2], "", "4." + question.getAnswerOptions()[3]
                        + "\n" + "Select your answer !!"
        );
    }

    public static void displayQuestionUseHelp(Question question, int index) {
        System.out.printf("%s\n%s%-20s%s\n%s%-20s%s\n",
                "Question " + (index + 1) + " : " + question.getQuestionTitle() + " ? ",
                "1." + question.getAnswerOptions()[0], "", "2." + question.getAnswerOptions()[1],
                "3." + question.getAnswerOptions()[2], "", "4." + question.getAnswerOptions()[3]
                        + "\n" + "Select your answer !!"
        );
        System.out.printf("\nThe correct answer is ... : %d. %s\n", question.getCorrectAnswerIndex(),
                question.getAnswerOptions()[question.getCorrectAnswerIndex() - 1]);
    }

    public static void displayQuestionUse5050(Question question, int index) {
        int correctAnswer = question.getCorrectAnswerIndex() - 1;
        int wrongAnswer = getRandomWrongAnswerIndex(correctAnswer);
        List<Integer> indices = Arrays.asList(correctAnswer, wrongAnswer);
        Collections.shuffle(indices);
        System.out.printf("%s\n%s%-20s%s%s\n",
                "Question " + (index + 1) + " : " + question.getQuestionTitle() + " ? ",
                (indices.get(0) + 1) + "." + question.getAnswerOptions()[indices.get(0)], "",
                (indices.get(1) + 1) + "." + question.getAnswerOptions()[indices.get(1)],
                "\nSelect your answer !!\n");
    }

    private static int getRandomWrongAnswerIndex(int correctAnswerIndex) {
        Random random = new Random();
        int wrongAnswerIndex = random.nextInt(3);
        while (wrongAnswerIndex == correctAnswerIndex) {
            wrongAnswerIndex = random.nextInt(3);
        }
        return wrongAnswerIndex;
    }


    public static void showHelpList() {
        System.out.println("Available helps:");
        if (helpList.isEmpty()) {
            System.out.println("You have no help !!");
        } else {
            for (int key : helpList.keySet()) {
                System.out.printf("%d. %s\n", key, helpList.get(key));
            }
        }
        System.out.println("Select help you want !!");
    }

    public static void useHelp(Question question, int answer, int index) {
        if (answer >= 5 && answer <= 8) {
            if (helpList.isEmpty()) {
                System.out.println("You have no help !!");
            } else {
                if (helpList.get(answer) == null) {
                    System.out.println("Wrong input !!");
                } else {
                    if (answer == 5) {
                        displayQuestionUse5050(question, index);
                        helpList.remove(answer);
                    } else {
                        displayQuestionUseHelp(question, index);
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
}
