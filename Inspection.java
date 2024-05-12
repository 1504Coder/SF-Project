package project3;

/**
 * @author Elijah Philip
 * This class records the info of the score,date,violation, and risk of each Restaurant in the csv file.
 */
public class Inspection {
    private Date date;
    private int score;
    private String violation;
    private String risk;


    /**
     *
     * @param date object from the Date class.
     * @param score the numberical score of the restaurant that's given.
     * @param violation the given violation of the restaurant.
     * @param risk the given comment about risk a restaurant.
     * @throws IllegalArgumentException if the date object is null and/or if the score is less than 0 or greater than 100.
     */
    public Inspection(Date date, int score, String violation, String risk) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException("A date should be given.");
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("The score should be between 0 and 100");
        }
        this.date = date;
        this.score = score;
        this.violation = violation;
        this.risk = risk;
    }

    public Date getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public String getRisk() {
        return risk;
    }

    public String getViolation() {
        return violation;
    }

}

