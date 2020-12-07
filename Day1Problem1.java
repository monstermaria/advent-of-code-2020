import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Day1Problem1 {
    public static void main(String[] args) {
        Scanner scan;
        ArrayList<Integer> numbers = new ArrayList<>();
        int number, num1, num2;

        try {
            scan  = new Scanner(new File("day1.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (scan.hasNext()) {
            try {
                number = scan.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            numbers.add(number);
        }

        System.out.println(numbers.size());

        for (int i = 0; i < numbers.size() - 1; i++) {
            num1 = numbers.get(i);
            for (int j = i + 1; j < numbers.size(); j++) {
                num2 = numbers.get(j);
                if (num1 + num2 == 2020) {
                    System.out.println("2020 found at " + i + " and " + j);
                    System.out.println("The numbers are " + num1 + " and " + num2);
                    System.out.println("The result is " + num1 * num2);

                    return;
                }
            }
        }
    }
}
