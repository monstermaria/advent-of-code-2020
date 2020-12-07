import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Day2Problem2 {
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
            String[] positionsAndCharacter = policy.split(" ");
            String positions = positionsAndCharacter[0];
            char ch = positionsAndCharacter[1].charAt(0);
            String[] firstPosAndSecondPos = positions.split("-");
            int firstPos, secondPos;

            try {
                firstPos = Integer.parseInt(firstPosAndSecondPos[0]);
                secondPos = Integer.parseInt(firstPosAndSecondPos[1]);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            firstPos--;
            secondPos--;
            
            if ((password[firstPos] == ch && password[secondPos] != ch) || 
                    (password[firstPos] != ch && password[secondPos] == ch)) {
                numberOfCorrectPasswords++;
            }

        }

        System.out.println("Number of correct passwords: " + numberOfCorrectPasswords);
    }
}
