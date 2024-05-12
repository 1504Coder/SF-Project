package project3;
/**
 * @author Elijah Philip
 * This class stores a list of Restaurant objects that match with the name or zip the user enters that matches the restaurants.
 */

import java.util.Iterator;

public class RestaurantList extends LinkedList<Restaurant> {
    public RestaurantList() {
    }


    /**
     * This method checks if a string contains another string.
     *
     * @param keyword    takes in the specified keyword to check if it's contained in the comparator String.
     * @param comparator String that is used to see if...
     * @return true if keyword is in comparator. Will return false otherwise.
     */
    boolean containsKeyword(String keyword, String comparator) {
        keyword = keyword.trim();
        if (keyword.length() < 1) return false;
        if (keyword.equalsIgnoreCase(comparator.trim())) return true;
        int keySize = keyword.length();
        String[] arr = comparator.split(" ");
        comparator = String.join("", arr);
        int comparatorSize = comparator.length();
        if (keySize == comparatorSize) {
            return keyword.equalsIgnoreCase(comparator);
        } else {
            for (int i = 0; i < comparatorSize; i++) {
                int size = i + keySize;
                if (size <= comparatorSize) {
                    String comparison = comparator.substring(i, size);
                    if (keyword.equalsIgnoreCase(comparison)) {
                        return true;
                    }
                } else return false;
            }
        }
        return false;
    }


    /**
     * This method gets each restaurant in the current collection and checks if the name is within the restaurant object.
     *
     * @param keyword takes in the specified keyword to compare against each Restaurant object's name.
     * @return the ArrayList of Restaurant objects that contain the Restaurant name specified. However, if the ArrayList is empty, will return null as no matching Restaurant names were found.
     */
    public RestaurantList getMatchingRestaurants(String keyword) {

        if (keyword == null || keyword.isBlank()) return null;
        Iterator<Restaurant> iterateRestaurants = this.iterator();
        RestaurantList matchingRestaurants = new RestaurantList();
        while (iterateRestaurants.hasNext()) {
            Restaurant r = iterateRestaurants.next();
            if (containsKeyword(keyword, r.getName())) matchingRestaurants.add(r);
        }
        return matchingRestaurants.size() > 0 ? matchingRestaurants : null;
    }

    /**
     * This method does the same as getMatchingRestaurants except for the zip.
     *
     * @param keyword takes in the specified keyword to compare against each Restaurant object's zipcode.
     * @return the ArrayList of Restaurant objects that contain the Restaurant zipcode specified. However, if the ArrayList is empty, will return null as no matching Restaurant zipcode were found.
     */
    public RestaurantList getMatchingZip(String keyword) {
        if (keyword == null || keyword.isBlank()) return null;

        Iterator<Restaurant> iterateRestaurants = this.iterator();
        RestaurantList matchingZip = new RestaurantList();
        while (iterateRestaurants.hasNext()) {
            Restaurant r = iterateRestaurants.next();
            if (keyword.equals(r.getZip())) matchingZip.add(r);
        }
        return matchingZip.size() > 0 ? matchingZip : null;
    }

    /***
     * This method returns a String representation of Restaurant list.
     * @return a list of matching restaurant names seperated by semicolons as a String.
     */
    @Override
    public String toString() {
        StringBuilder restaurantNames = new StringBuilder();
        for (Restaurant restaurant : this) restaurantNames.append(restaurant.getName()).append("; ");
        int restNameLen = restaurantNames.length();
        restaurantNames.delete(restNameLen - 2, restNameLen - 1);
        return restaurantNames.toString();

    }
}