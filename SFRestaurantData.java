package project3;

/**
 * @author Elijah Philip
 * This file reads and searches the restaurant data from a CSV file, allowing users to look up information by restaurant name or ZIP code. It implements the other classes within the file to do this.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SFRestaurantData {
    public static ArrayList<String> splitCSVLine(String textLine) {

        if (textLine == null) return null;
        ArrayList<String> entries = new ArrayList<String>();
        int lineLength = textLine.length();
        StringBuffer nextWord = new StringBuffer();
        char nextChar;
        boolean insideQuotes = false;
        boolean insideEntry = false;
        // iterate over all characters in the textLine
        for (int i = 0; i < lineLength; i++) {
            nextChar = textLine.charAt(i);
            // handle smart quotes as well as regular quotes
            if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
                // change insideQuotes flag when nextChar is a quote
                if (insideQuotes) {
                    insideQuotes = false;
                    insideEntry = false;
                } else {
                    insideQuotes = true;
                    insideEntry = true;
                }
            } else if (Character.isWhitespace(nextChar)) {
                if (insideQuotes || insideEntry) {
                    // add it to the current entry
                    nextWord.append(nextChar);
                } else { // skip all spaces between entries
                    continue;
                }
            } else if (nextChar == ',') {
                if (insideQuotes) { // comma inside an entry
                    nextWord.append(nextChar);
                } else { // end of entry found
                    insideEntry = false;
                    entries.add(nextWord.toString());
                    nextWord = new StringBuffer();
                }
            } else {
                // add all other characters to the nextWord
                nextWord.append(nextChar);
                insideEntry = true;
            }
        }
        // add the last word (assuming it's not empty)
        // trim the white space before adding to the list
        if (!nextWord.toString().equals("")) {
            entries.add(nextWord.toString().trim());
        }
        return entries;
    }

    public static ArrayList<ArrayList<String>> collectData() {
        System.out.println("Search the database by matching keywords to titles or actor names.");
        System.out.println("To search for matching restaurant names, enter");
        System.out.println("name KEYWORD");
        System.out.println("To search for restaurants in a zip code, enter");
        System.out.println("zip KEYWORD");
        System.out.println("To finish the program, enter");
        System.out.println("quit\n");
        Scanner userInput = new Scanner(System.in);
        String userValue = "";
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        do {
            System.out.print("Enter the CSV directory: ");
            userValue = (userInput.nextLine()).trim();
            File csvFile = new File(userValue);
            if (!userValue.equalsIgnoreCase("quit")) {
                if (!csvFile.exists()) {
                    System.err.println("Error: the file " + csvFile.getAbsolutePath() + " does not exist.\n");
                    System.exit(1);
                }
                if (!csvFile.canRead()) {
                    System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened for reading.\n");
                    System.exit(1);
                }
            }
            Scanner inBusiness = null;

            try {
                inBusiness = new Scanner(csvFile);
            } catch (FileNotFoundException e) {
                System.err.println("Error: the file " + csvFile.getAbsolutePath() + " cannot be opened for reading.\n");
                System.exit(1);
            }
            String line = null;
            while (inBusiness.hasNextLine()) {
                try {
                    line = inBusiness.nextLine();
                    data.add(splitCSVLine(line));
                } catch (NoSuchElementException ex) {
                    // caused by an incomplete or miss-formatted line in the input file
                }
            }
            break;
        } while (!userValue.equalsIgnoreCase("quit"));
        return data;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> data = SFRestaurantData.collectData();

        Scanner query = new Scanner(System.in);
        String entry = "";
        String name = "";
        String zip = "";
        do {
            System.out.print("Enter your search query: ");
            entry = (query.nextLine()).trim();
            String[] modEntry = entry.split(" ");
            assert false;
            if (!entry.equalsIgnoreCase("quit")) {
                String ans = modEntry[0];
                int j = 0;

                switch (ans.toLowerCase(Locale.ROOT)) {
                    case "name" -> {
                        j = 1;
                        String[] newModEntry = Arrays.copyOfRange(modEntry, 1, modEntry.length);
                        name = String.join("", newModEntry);
                    }
                    case "zip" -> {
                        j = 5;
                        zip = entry.split(" ")[1];
                    }
                    default -> {
                        continue;
                    }
                }
                RestaurantList rl = new RestaurantList();
                try {
                    System.out.println("trying entry value...");
                    for (ArrayList<String> datum : data) {
                        String element = datum.get(j);
                        String[] modElement = {};
                        String newModElement = "";
                        if (j == 1) {
                            modElement = element.split(" ");
                            newModElement = String.join("", modElement);
                        }
                        if (newModElement.toLowerCase(Locale.ROOT).contains(name.toLowerCase()) || element.trim().equals(zip.trim())) {
                            String address = datum.get(2);
                            String phoneNumber = datum.get(9);
                            String inspectionDate = datum.get(11);
                            String violation = datum.get(15);
                            String risk = null;
                            try {
                                risk = datum.get(16);
                            } catch (Exception e) {
                                risk = "";
                            }
                            String inspectionScore = datum.get(12);

                            Date d1 = null;
                            if (!inspectionDate.isBlank()) {
                                d1 = new Date(inspectionDate.split(" ")[0]);

                            }
                            Inspection inspect = null;
                            if (!inspectionScore.isBlank()) {
                                inspect = new Inspection(d1, Integer.parseInt(inspectionScore), risk, violation);
                            }
                            if (j == 1) {
                                String zipcode = datum.get(5);
                                Restaurant r = new Restaurant(element, zipcode, address, phoneNumber);

                                if (inspect != null && d1 != null) {
                                    r.addInspection(inspect);
                                    rl.add(r);
                                }
                            } else if (j == 5) {
                                String businessName = datum.get(1);
                                Restaurant r = new Restaurant(businessName, element, address, phoneNumber);
                                if (inspect != null && d1 != null) {
                                    r.addInspection(inspect);
                                    rl.add(r);
                                }
                            }
                        }
                    }
                    ArrayList<Restaurant> restaurants = new ArrayList<>();
                    for (Restaurant restaurant : rl) {
                        if (!(restaurant.getName().isBlank() || restaurant.getZip().isBlank())) {
                            restaurants.add(restaurant);
                        }
                    }
                    Collections.sort(restaurants);
                    for (Restaurant r : restaurants) System.out.println(r);
                } catch (IllegalArgumentException ex) {
                    System.out.println("This is not a valid name, or zip.");
                    //continue;
                }


            }

        } while (!entry.equalsIgnoreCase("quit"));
        query.close();
    }
}

//C:\Users\Kyngblitz\IdeaProjects\MyProjects\Classwork\src\project3\SF_restaurant_scores_full.csv
//name Brickhouse