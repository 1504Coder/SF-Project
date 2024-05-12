package project3;
/**
 * @author Elijah Philip
 * This class allows for the creation of a restaurant object that has the name, address, phone number, and ZIP code.
 * Inspection records pertaining to restaurant may contain information about the inspection, such as the date, the inspection score, the description of the infraction, and the risk level.
 * It implmements the Comprable interface so that it can
 */

import java.util.ArrayList;

public class Restaurant implements Comparable<Restaurant> {

    private String name;
    private String address;
    private String phone;
    private String zip;
    private ArrayList<Inspection> listOfInspections;

    /**
     *
     * @param name Restaurant's name
     * @param zip Restaurant's zip
     * @throws IllegalArgumentException if the name or zip is null or if their string value is empty.
     */
    public Restaurant(String name, String zip) throws IllegalArgumentException {
        if (name == null || zip == null) {
            throw new IllegalArgumentException("The name and zip should not be null.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("The name should not be empty");
        }
        this.name = name;
        this.zip = zip;
    }

    public Restaurant(String name, String zip, String address, String phone) throws IllegalArgumentException {
        if (name == null || zip == null) {
            throw new IllegalArgumentException("The name and zip should not be null.");
        }
        if (name.isBlank()) {
            throw new IllegalArgumentException("The name should not be empty");
        }
        if (zip.length() != 5) {
            throw new IllegalArgumentException("The zip should contain 5 digits.");
        }
        this.name = name;
        this.zip = zip;
        this.address = address;
        this.phone = phone;
    }

    /***
     * This method will add an inspection to an arraylist of inspections if the inspection is not null.
     * @param inspect an object of the Inspection class that will be added to a new or existing list of Inspections.
     * @throws IllegalArgumentException if inspection is null.
     */
    public void addInspection(Inspection inspect) throws IllegalArgumentException {
        if (inspect == null) {
            throw new IllegalArgumentException("Inspect should not be null.");
        }
        if (listOfInspections == null) {
            listOfInspections = new ArrayList<Inspection>();
        }
        listOfInspections.add(inspect);
    }

    /**
     * This method will compare restaurant objects based on the name and value of zip.
     * @param r the object to be compared.
     * @return -1, 1, or 0.
     */
    @Override
    public int compareTo(Restaurant r) {
        int compareName = Integer.compare(name.compareToIgnoreCase(r.name), 0);
        int compareZip = Integer.compare(zip.compareToIgnoreCase(r.zip), 0);

        if (compareName != 0) {
            return compareName;
        } else {
            return compareZip;
        }
    }

    /**
     * This method returns a String representation of a restaurant object.
     * @return restaurant object.
     */
    @Override
    public String toString() {
        if (address == null) {
            address = "";
        }
        if (phone == null) {
            phone = "";
        }

        String separator = "--------------------------------------------------";
        StringBuilder result = new StringBuilder();

        result.append(String.format("%s%n%s%n%s%18s%s%n%s%22s%s%n%s%20s%s%n%s%n",
                name, separator, "address", ": ", address, "zip", ": ", zip, "phone", ": ", phone, "recent inspection results:"));
        if (listOfInspections != null) {
            for (Inspection inspection : listOfInspections) {
                if (Integer.toString(inspection.getScore()).isBlank()) {
                    result.append("");
                } else {
                    result.append(inspection.getScore()).append(", ");
                }

                if (inspection.getDate().toString().isBlank()) {
                    result.append("");
                } else {
                    result.append(inspection.getDate()).append(", ");
                }

                if (inspection.getRisk().isBlank()) {
                    result.append("");
                } else {
                    result.append(inspection.getRisk()).append(", ");
                }

                if (inspection.getViolation().isBlank()) {
                    result.append("");
                } else {
                    result.append(inspection.getViolation()).append(", ");
                }

                result.delete(result.length() - 2, result.length() - 1);
                result.append("\n");
            }
        }

        return result.toString();
    }

    /***
     * This method checks if a restaurant object is equal to the given object.
     * @param s Object that is being compared with.
     * @return true if
     */
    @Override
    public boolean equals(Object s) {
        if (s instanceof Restaurant) {
            return (this.name.equals(((Restaurant) s).getName()) && this.zip.equals(((Restaurant) s).getZip()));
        }
        return false;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getZip() {
        return zip;
    }

    public ArrayList<Inspection> getListOfInspections() {
        return listOfInspections;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

}
