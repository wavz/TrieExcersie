package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

/**
 * The class TrieNode represents the node for each added character in a user's word
 */
public class TrieNode {

    private char c;
    /**
     * saving the parent node in order to update the suggestion list of all the parents
     */
    private TrieNode parent;
    private HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    private boolean isLeaf;
    private int numOfSearches = 0;

    private final List<Suggestion> suggestionList = new ArrayList<>();

    public TrieNode() {
    }

    public TrieNode(char c) {
        this.c = c;
    }

    /**
     * checking if the list already containing the word in the suggestions list
     * @param word
     * @return - true if the word exists
     */
    private boolean isSuggestionListConatainsWord(String word) {
        return suggestionList.stream().anyMatch(o -> o.getSuggestion().equals(word));
    }

    /**
     * @param updatedSuggestion
     * @return the index of the suggested word
     */
    private int returnIndexOfEelement(Suggestion updatedSuggestion) {
        return IntStream.range(0, suggestionList.size())
                .filter(i -> suggestionList.get(i).getSuggestion().equals(updatedSuggestion.getSuggestion()))
                .findFirst().orElse(-1);
    }

    /**
     * updating the existing suggestion with the number of searched times
     * @param updatedSuggestion
     */
    private void updateSuggestionList(Suggestion updatedSuggestion) {
        suggestionList.get(returnIndexOfEelement(updatedSuggestion)).setNumOfSearchTimes(updatedSuggestion.getNumOfSearchTimes());
    }

    /**
     * invoke this method when the user presses search, and update the list for this node.
     * @param suggestionWord
     */
    public void updateNodeSuggestions(Suggestion suggestionWord) {
        if (suggestionList.isEmpty()) {
            suggestionList.add(suggestionWord);
        }

        if (!isSuggestionListConatainsWord(suggestionWord.getSuggestion())) {
            suggestionList.add(suggestionWord);
            suggestionList.sort(Suggestion::compareTo);
        } else {
            updateSuggestionList(suggestionWord);
            suggestionList.sort(Suggestion::compareTo);
        }
    }

    public void updateNumOfSearches() {
        numOfSearches++;
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public TrieNode getParent() {
        return parent;
    }

    public void setParent(TrieNode parent) {
        this.parent = parent;
    }

    public HashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public void setChildren(HashMap<Character, TrieNode> children) {
        this.children = children;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }

    public int getNumOfSearches() {
        return numOfSearches;
    }

    public List<Suggestion> getSuggestionList() {
        return suggestionList;
    }


    @Override
    public String toString() {
        return "TrieNode{" +
                "c=" + c +
                ", parent=" + parent +
                ", children=" + children +
                ", isLeaf=" + isLeaf +
                ", numOfSearches=" + numOfSearches +
                ", suggestionList=" + suggestionList +
                '}';
    }
}