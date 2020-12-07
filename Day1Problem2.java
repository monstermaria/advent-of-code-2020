import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Day1Problem2 {
    public static void main(String[] args) {
        Scanner scan;
        ArrayList<Integer> numbers = new ArrayList<>();
        int number, size, num1, num2, num3;

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

        size = numbers.size();

        System.out.println(size);

        for (int i = 0; i < size - 1; i++) {
            num1 = numbers.get(i);
            for (int j = i + 1; j < size - 1; j++) {
                num2 = numbers.get(j);
                for (int k = j + 1; k < size; k++) {
                    num3 = numbers.get(k);
                    if (num1 + num2 + num3 == 2020) {
                        System.out.println("2020 found at " + i + " and " + j + " and " + k);
                        System.out.println("The numbers are " + num1 + " and " + num2 + " and " + num3);
                        System.out.println("The result is " + num1 * num2 * num3);

                        return;
                    }
                }
            }
        }
    }
}
