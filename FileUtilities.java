import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class FileUtilities {

	public static ArrayList<String> readInputFromFile(String fileName) {
		Scanner scan;
		String line;
		ArrayList<String> inputLines = new ArrayList<>();

		try {
			scan  = new Scanner(new File(fileName));
		} catch (Exception e) {
			e.printStackTrace();
			return inputLines;
		}


		while (scan.hasNext()) {
			try {
				line = scan.nextLine();
			} catch (Exception e) {
				e.printStackTrace();
				return inputLines;
			}

			inputLines.add(line);
		}

		return inputLines;
	}
}
