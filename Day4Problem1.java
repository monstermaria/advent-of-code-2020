import java.util.ArrayList;
import java.lang.reflect.Field;

public class Day4Problem1 {

    private class Passport {
        String byr, iyr, eyr, hgt, hcl, ecl, pid, cid;

        public boolean valid() {
            return (byr != null && iyr != null && eyr != null && hgt != null
                    && hcl != null && ecl != null && pid != null);
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
        new Day4Problem1().checkPassports();
    }
}
