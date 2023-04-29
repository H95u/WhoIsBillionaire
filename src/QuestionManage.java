import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class QuestionManage implements IOFileInterface<Question> {
    private ArrayList<Question> questions;
    private Scanner scanner;
    private String questionPath = "C:\\Users\\Hieu's PC\\Desktop\\casestudy\\login\\src\\data\\question.txt";

    public QuestionManage() {
        questions = readFile(questionPath);
        scanner = new Scanner(System.in);
    }

    @Override
    public void writeFile(ArrayList<Question> questions, String questionPath) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(questionPath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(questions);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public ArrayList<Question> readFile(String questionPath) {
        ArrayList<Question> newQuestions = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(questionPath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            newQuestions = (ArrayList<Question>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return newQuestions;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion() {
        int id = getLastQuestionId() + 1;
        System.out.println("Input question title");
        String questionTitle = scanner.nextLine();
        String[] answerOptions = new String[4];
        for (int i = 0; i < answerOptions.length; i++) {
            System.out.println("Input answer " + (i + 1));
            answerOptions[i] = scanner.nextLine();
        }
        int correctAnswerIndex = 0;
        do {
            System.out.println(Arrays.toString(answerOptions));
            try {
                System.out.println("Input correct answer index");
                correctAnswerIndex = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (correctAnswerIndex <= -1 || correctAnswerIndex > 3);
        questions.add(new Question(id, questionTitle, answerOptions, correctAnswerIndex));
        writeFile(questions, questionPath);
    }

    public Question getQuestionById() {
        displayAllQuestion();
        int id = -1;
        try {
            System.out.println("Input id of account you want");
            id = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Question question : questions) {
            if (question.getId() == id) return question;
        }
        return null;
    }

    public void deleteQuestion() {
        displayAllQuestion();
        Question deleteQuestion = getQuestionById();
        if (deleteQuestion == null) System.out.println("Not have that id");
        else {
            questions.remove(deleteQuestion);
            System.out.println("Delete success!!");
        }
        writeFile(questions, questionPath);
    }

    public int getLastQuestionId() {
        if (questions.size() == 0) return 0;
        else return questions.get(questions.size() - 1).getId();
    }

    public void displayAllQuestion() {
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
