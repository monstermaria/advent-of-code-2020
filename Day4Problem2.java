import java.util.ArrayList;
import java.lang.reflect.Field;

public class Day4Problem2 {

    private class Passport {
        String byr, iyr, eyr, hgt, hcl, ecl, pid, cid;

        private int convertStringToInt(String number) {
            try {
                return Integer.parseInt(number);
            } catch (Exception e) {
                // System.out.println("Could not parse integer: " + number);
                return 0;
            }
        }

        private boolean checkValidRange(String description, int number, int lowerLimit, int upperLimit) {
            boolean valid = number >= lowerLimit && number <= upperLimit;

            if (!valid) {
                // System.out.println(description + " not valid: " + number);
            }

            return valid;
        }

        private boolean birthYearValid() {

            return checkValidRange("Birth year", convertStringToInt(byr), 1920, 2002);
        }

        private boolean issueYearValid() {

            return checkValidRange("Issue year", convertStringToInt(iyr), 2010, 2020);
        }

        private boolean expirationYearValid() {

            return checkValidRange("Expiration year", convertStringToInt(eyr), 2020, 2030);
        }

        private boolean heightValid() {
            int index = 0;
            int heightNumber;
            String heightType;

            if (hgt == null) {
                return false;
            }

            // find out index where number ends and type starts
            for (int i = 0; i < hgt.length(); i++) {
                char ch = hgt.charAt(i);

                if (ch >= '0' && ch <= '9') {
                    index = i + 1;
                } else {
                    break;
                }
            }

            heightNumber = convertStringToInt(hgt.substring(0, index));
            heightType = hgt.substring(index);

            if (heightType.equals("cm")) {
                return checkValidRange("Height in centimeters", heightNumber, 150, 193);
            } else if (heightType.equals("in")) {
                return checkValidRange("Height in inches", heightNumber, 59, 76);
            } else {
                // System.out.println("Height has no type: " + hgt + ", index: " + index);
                return false;
            }
        }

        private boolean hairColorValid() {
            boolean valid = hcl != null;

            if (!valid) {
                return false;
            }

            // use regular expression to check if hair color fulfills requirements
            valid = hcl.matches("#([0-9|a-f]){6}");

            return valid;
        }

        private boolean eyeColorValid() {
            String[] validColors = { "amb", "blu", "brn", "gry", "grn", "hzl", "oth" };

            if (ecl == null) {
                return false;
            }

            for (String color : validColors) {
                if (ecl.equals(color)) {
                    return true;
                }
            }

            return false;
        }

        private boolean passportIdValid() {
            boolean valid = pid != null;

            if (!valid) {
                return false;
            }

            // use regular expression to check if passport id fulfills requirements
            valid = pid.matches("([0-9]){9}");

            return valid;
        }

        public boolean valid() {
            return (birthYearValid() && issueYearValid() && expirationYearValid() && heightValid()
                    && hairColorValid() && eyeColorValid() && passportIdValid());
        }

        public void addDataPoint(String dataPoint) {

            String[] typeAndData = dataPoint.split(":");
            Field field;

            // handle badly formatted data by printing a message and skipping adding
            if (typeAndData.length != 2) {
                System.out.println("Format error in indata: " + dataPoint);
                return;             
            }

            // set attribute using reflection instead of using a switch statement
            try {
                field = Passport.class.getDeclaredField(typeAndData[0]);
                field.set(this, typeAndData[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();

            str.append("Birth year: ");
            str.append(byr);
            str.append(", ");
            str.append("Issue year: ");
            str.append(iyr);
            str.append(", ");
            str.append("Expire year: ");
            str.append(eyr);
            str.append(", ");
            str.append("Height: ");
            str.append(hgt);
            str.append(", ");
            str.append("Hair color: ");
            str.append(hcl);
            str.append(", ");
            str.append("Eye color: ");
            str.append(ecl);
            str.append(", ");
            str.append("Passport id: ");
            str.append(pid);
            str.append(", ");
            str.append("Country id: ");
            str.append(cid);

            return str.toString();
        }
    }

    private void checkPassports() {
        ArrayList<Passport> passports = new ArrayList<>();
        Passport passport = new Passport();
        int numberOfInvalidPassports = 0;

        ArrayList<String> input = FileUtilities.readInputFromFile("day4.txt");

        // make sure last line is empty, so the last passport gets handled
        input.add("");

        // extract passport data
        for (String line : input) {
            String[] dataPoints;

            // delimiter between passports
            if (line == "") {
                // add valid passport to the list of passports
                if (passport.valid()) {
                    // System.out.println("Adding passport: " + passport.toString());
                    passports.add(passport);
                } else {
                    // System.out.println("Skipping passport: " + passport.toString());
                    numberOfInvalidPassports++;
                }

                // reset passport and go to next line of the input
                passport = new Passport();
                continue;
            }

            // parse line of passport data
            dataPoints = line.split(" ");

            for (String dataPoint : dataPoints) {
                passport.addDataPoint(dataPoint);
            }
        }

        System.out.println("Number of valid passports: " + passports.size());
        System.out.println("Number of invalid passports: " + numberOfInvalidPassports);
    }

    public static void main(String[] args) {
        new Day4Problem2().checkPassports();
    }
}
