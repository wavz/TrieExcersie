

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TrieNode {

    private char c;
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

    private boolean isSuggestionListConatainsWord(String word) {
        return suggestionList.stream().anyMatch(o -> o.getSuggestion().equals(word));
    }

    private int returnIndexOfEelement(Suggestion updatedSuggestion) {
        for (int index = 0; index < suggestionList.size(); index++) {
            if (suggestionList.get(index).getSuggestion().equals(updatedSuggestion.getSuggestion())) {
                return index;
            }
        }
        return -1;
    }

    private void updateSuggestionList(Suggestion updatedSuggestion) {
        suggestionList.set(returnIndexOfEelement(updatedSuggestion), updatedSuggestion);
    }

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
}