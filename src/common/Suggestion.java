package common;

/**
 * the class represents the suggestion for the user
 */
public class Suggestion implements Comparable<Suggestion>{

    /**
     * @param suggestion - represents the suggested word for the user
     */
    private String suggestion;

    /**
     * @paran numOfSearchTimes - the number of times the word was being searched
     */
    private Integer numOfSearchTimes;

    public Suggestion(String suggestion, int numOfSearchTimes){
        this.suggestion = suggestion;
        this.numOfSearchTimes = numOfSearchTimes;

    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public int getNumOfSearchTimes() {
        return numOfSearchTimes;
    }

    public void setNumOfSearchTimes(int numOfSearchTimes) {
        this.numOfSearchTimes = numOfSearchTimes;
    }


    /**
     * override the function compareTo in order to do an easy sort
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Suggestion o)
    {
        return this.numOfSearchTimes.compareTo(o.getNumOfSearchTimes());
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "common.Suggestion{" +
                "suggestion='" + suggestion + '\'' +
                ", numOfSearchTimes=" + numOfSearchTimes +
                '}';
    }
}
