import java.util.Arrays;
import java.util.TreeMap;
import java.util.Random;

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
                        + "\n" + "Select your answer !!" + " Type 0 to use help !!"
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
        Random random = new Random();
        int correctAnswer = question.getCorrectAnswerIndex();
        int wrongAnswer = random.nextInt(3);
        while (wrongAnswer == correctAnswer) {
            wrongAnswer = random.nextInt(3);
        }
        int[] answerIndices = new int[]{correctAnswer, wrongAnswer};
        Arrays.sort(answerIndices);
        System.out.printf("%s\n%s%-20s%s%s\n",
                "Question " + (index + 1) + " : " + question.getQuestionTitle() + " ? ",
                (answerIndices[0] + 1) + "." + question.getAnswerOptions()[answerIndices[0]], "",
                (answerIndices[1] + 1) + "." + question.getAnswerOptions()[answerIndices[1]],
                "\nSelect your answer !!\n");
    }

    public static void showHelpList() {
        System.out.println("Available helps:");
        for (int key : helpList.keySet()) {
            System.out.printf("%d. %s\n", key, helpList.get(key));
        }
        System.out.println("Select help you want !!");
    }

    public static void useHelp(Question question, int answer, int index) {
        if (answer >= 5 && answer <= 8) {
            if (answer == 5) {
                displayQuestionUse5050(question, index);
                helpList.remove(answer);
            } else {
                displayQuestionUseHelp(question, index);
                helpList.remove(answer);
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
