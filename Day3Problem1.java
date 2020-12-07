import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Day3Problem1 {
    public static void main(String[] args) {
        Scanner scan;
        ArrayList<String> mapLines = new ArrayList<>();
        String line;
        int numberOfCorrectPasswords = 0;

        try {
            scan  = new Scanner(new File("day3.txt"));
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

            mapLines.add(line);
        }

        System.out.println("Number of lines on the map: " + mapLines.size());
        System.out.println("Length of original lines: " + mapLines.get(0).length());

        int neededLengthOfMapLines = 3 * mapLines.size();
        int originalLengthOfMapLines = mapLines.get(0).length();
        int numberOfRequiredRepeats = neededLengthOfMapLines / originalLengthOfMapLines + 1;

        for (int i = 0; i < mapLines.size(); i++) {
            String mapLine = mapLines.get(i);
            mapLine = mapLine.repeat(numberOfRequiredRepeats);
            mapLines.set(i, mapLine);
        }

        System.out.println("Length of extended lines: " + mapLines.get(0).length());


        int x = 0, y = 0, numberOfTreesEncountered = 0;

        while (y < mapLines.size()) {
            char terrain = mapLines.get(y).charAt(x);

            if (terrain == '#') {
                numberOfTreesEncountered++;
            }

            x += 3;
            y += 1;
        }

        System.out.println("Number of trees encoutered: " + numberOfTreesEncountered);
    }
}
