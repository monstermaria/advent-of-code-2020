import java.util.ArrayList;
import java.util.Collections;

public class Day5Problem2 {

    private static int findInRangeWithBinaryPartitioning(String range, char lower, char upper) {
        int rangeStart = 1;
        int rangeStop = (int) Math.pow(2, range.length());
        int diff = rangeStop / 2;

        for (int i = 0; i < range.length(); i++) {
            char ch = range.charAt(i);

            if (ch == lower) {
                rangeStop = rangeStop - diff;
            } else if (ch == upper) {
                rangeStart = rangeStart + diff;
            } else {
                System.out.println("Unknown range descriptor: " + ch);
                return -1;
            }

            diff = diff / 2;
        }

        if (rangeStart == rangeStop) {
            return rangeStart - 1;
        } else {
            System.out.println("Unable to detect range: " + rangeStart + " " + rangeStop + " diff: " + diff);
            return -1;
        }
    }

    private static int getSeatId(String boardingPass) {
        int row = 0;
        int seat = 0;
        int seatId = 0;

        if (!boardingPass.matches("[F|B]{7}[L|R]{3}")) {
            System.out.println("Wrong format of boarding pass string: " + boardingPass);
            return -1;
        }

        row = findInRangeWithBinaryPartitioning(boardingPass.substring(0, 7), 'F', 'B');
        seat = findInRangeWithBinaryPartitioning(boardingPass.substring(7, 10), 'L', 'R');
        seatId = row * 8 + seat;
        
        return seatId;
    }

    private static int findMissingSeatId(ArrayList<Integer> seatIds) {
        int missinSeatId = -1;
        int currentSeatId;

        Collections.sort(seatIds);

        currentSeatId = seatIds.get(0);

        System.out.println("First seat ID: " + currentSeatId);

        currentSeatId--;

        for (Integer id : seatIds) {
            if (id == currentSeatId + 1) {

                // normal case
                currentSeatId++;

            } else if (id == currentSeatId + 2) {

                // found the missing seat
                missinSeatId = currentSeatId + 1;

                System.out.println("The missing seat ID is " + missinSeatId);

                currentSeatId += 2;

            } else {
                System.out.println("Something's not right: ID:" + id + " Current seat ID: " + currentSeatId
                        + " Missing seat ID: " + missinSeatId);
            }
        }

        System.out.println("Last seat ID: " + currentSeatId);

        return missinSeatId;
    }

    public static void main(String[] args) {
        ArrayList<String> boardingPasses = FileUtilities.readInputFromFile("day5.txt");
        ArrayList<Integer> seatIds = new ArrayList<>();

        System.out.println("Number of boarding passes: " + boardingPasses.size());
        System.out.println("Number of seats: " + 128 * 8);
        System.out.println("Highest possible seat id: " + (127 * 8 + 7));

        for (String boardingPass : boardingPasses) {
            seatIds.add(getSeatId(boardingPass));
        }

        System.out.println("Missing seat ID: " + findMissingSeatId(seatIds));
    }
}
