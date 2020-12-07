import java.util.ArrayList;

public class Day5Problem1 {

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
        
        // System.out.println("Row: " + row + ", seat; " + seat + ", seat ID: " + seatId);

        return seatId;
    }

    public static void main(String[] args) {
        ArrayList<String> boardingPasses = FileUtilities.readInputFromFile("day5.txt");
        int highestSeatId = -1;

        System.out.println("Number of boarding passes: " + boardingPasses.size());
        System.out.println("Number of seats: " + 128 * 8);
        System.out.println("Highest possible seat id: " + (127 * 8 + 7));

        for (String boardingPass : boardingPasses) {
            int seatId = getSeatId(boardingPass);

            if (seatId > highestSeatId) {
                highestSeatId = seatId;
            }
        }

        System.out.println("Highest seat ID in boarding passes: " + highestSeatId);
    }
}
