import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Day2Problem1 {
    public static void main(String[] args) {
        Scanner scan;
        ArrayList<String> passwords = new ArrayList<>();
        String line;
        int numberOfCorrectPasswords = 0;

        try {
            scan  = new Scanner(new File("day2.txt"));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        while (scan.hasNext()) {
            try {
                line = scan.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            passwords.add(line);
        }

        System.out.println("Number of passwords to check: " + passwords.size());

        for (String str : passwords) {
            String[] policyAndPassword = str.split(": ");
            String policy = policyAndPassword[0];
            char[] password = policyAndPassword[1].toCharArray();
            String[] countAndCharacter = policy.split(" ");
            String count = countAndCharacter[0];
            char character = countAndCharacter[1].charAt(0);
            String[] minCountAndMaxCount = count.split("-");
            int minCount, maxCount;
            int numberOfOccurances = 0;

            try {
                minCount = Integer.parseInt(minCountAndMaxCount[0]);
                maxCount = Integer.parseInt(minCountAndMaxCount[1]);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            for (char ch : password) {
                if (ch == character) {
                    numberOfOccurances++;
                }
            }

            if (numberOfOccurances >= minCount && numberOfOccurances <= maxCount) {
                numberOfCorrectPasswords++;
            }

        }

        System.out.println("Number of correct passwords: " + numberOfCorrectPasswords);
    }
}
