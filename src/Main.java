import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the number of levels in the building?");
        int countLevels = scanner.nextInt();

        Queue<Call> calls = new LinkedList<>();

        TestLift test = new TestLift(countLevels, calls);
        Thread testThread = new Thread(test);
        testThread.start();

        People people = new People(calls, countLevels, 7500);
        Thread peopleThread = new Thread(people);
        peopleThread.start();
    }
}