public class Suggestion implements Comparable<Suggestion>{

    private String suggestion;
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
        return "Suggestion{" +
                "suggestion='" + suggestion + '\'' +
                ", numOfSearchTimes=" + numOfSearchTimes +
                '}';
    }
}
