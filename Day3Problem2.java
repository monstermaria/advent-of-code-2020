import java.util.ArrayList;

public class Day3Problem2 {

    private static void expandTheMap(ArrayList<String> mapLines, int stepsToTheRight) {
        int neededLengthOfMapLines = stepsToTheRight * mapLines.size();
        int originalLengthOfMapLines = mapLines.get(0).length();
        int numberOfRequiredRepeats = neededLengthOfMapLines / originalLengthOfMapLines + 1;

        System.out.println("Number of lines on the map: " + mapLines.size());
        System.out.println("Length of original lines: " + mapLines.get(0).length());

        for (int i = 0; i < mapLines.size(); i++) {
            String mapLine = mapLines.get(i);
            mapLine = mapLine.repeat(numberOfRequiredRepeats);
            mapLines.set(i, mapLine);
        }

        System.out.println("Length of extended lines: " + mapLines.get(0).length());
    }

    private static int calculateNumberOfEncounteredTrees(ArrayList<String> mapLines, int[] slope) {
        int stepRight = slope[0];
        int stepDown = slope[1];
        int x = 0;
        int y = 0;
        int numberOfTreesEncountered = 0;

        while (y < mapLines.size()) {
            char terrain = mapLines.get(y).charAt(x);

            if (terrain == '#') {
                numberOfTreesEncountered++;
            }

            x += stepRight;
            y += stepDown;
        }

        System.out.println("Number of trees encountered: " + numberOfTreesEncountered);

        return numberOfTreesEncountered;
    }

    public static void main(String[] args) {
        int[][] slopes = { { 1, 1 }, { 3, 1 }, { 5, 1 }, { 7, 1 }, { 1, 2 } };
        int productOfNumberOfTreesOnDifferentSlopes = 1;

        ArrayList<String> mapLines = FileUtilities.readInputFromFile("day3.txt");

        expandTheMap(mapLines, 7);

        for (int i = 0; i < slopes.length; i++) {
            productOfNumberOfTreesOnDifferentSlopes *= calculateNumberOfEncounteredTrees(mapLines, slopes[i]);
        }

        System.out.println("The product of numbers of trees on different slopes is "
            + productOfNumberOfTreesOnDifferentSlopes);
    }
}
