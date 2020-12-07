import java.util.ArrayList;

public class Day6Problem1 {

    public static void main(String[] args) {
        ArrayList<String> input = FileUtilities.readInputFromFile("day6.txt");
        ArrayList<StringBuilder> passengerGroups = new ArrayList<>();
        int sumOfGroupsYesAnswers = 0;
        String lastLine = "";
        StringBuilder passengerGroup = new StringBuilder();

        // add an empty line to the input to make sure the last passenger group is handled
        input.add("");

        // extract group data
        for (String line : input) {

            // delimiter between groups is an empty line
            if (line.isEmpty()) {

                // System.out.println("Line is empty");

                // data has been added to passenger group
                if (passengerGroup.length() > 0) {

                    // add passenger group to list of passenger groups
                    passengerGroups.add(passengerGroup);

                    // add the number of yes answers to the total sum
                    sumOfGroupsYesAnswers += passengerGroup.length();
                }

            } else {

                // System.out.println("Line has content: " + line);

                // first line of a passenger group
                if (lastLine.isEmpty()) {

                    // create a new passenger group from the current line
                    passengerGroup = new StringBuilder(line);

                } else {

                    // add another line of passenger answers to the passenger group
                    String[] answers = line.split("");

                    for (String a : answers) {

                        // the question has not been answered yes by a previous passenger in this group
                        if (passengerGroup.indexOf(a) == -1) {

                            // add this question to the yes answers of this passenger group
                            passengerGroup.append(a);
                        }
                    }

                }

            }

            lastLine = line;
        }


        System.out.println("Passenger groups: " + passengerGroups);
        System.out.println("Number of lines of input: " + input.size());
        System.out.println("Number of passenger groups: " + passengerGroups.size());
        System.out.println("The sum of all the passenger groups yes answers is: " + sumOfGroupsYesAnswers);
    }
}
