package common;

import java.util.*;

public class Trie {
    private final TrieNode root;
    private final int MAX_SIZE = 10;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.getChildren();

        TrieNode currentParent;
        TrieNode trieNode = null;
        currentParent = root;

        //cur children parent = root

        for (char c : word.toCharArray()) {
            if (children.containsKey(c)) {
                trieNode = children.get(c);
            } else {
                trieNode = new TrieNode(c);
                trieNode.setParent(currentParent);
                children.put(c, trieNode);
            }

            children = trieNode.getChildren();
            currentParent = trieNode;
        }
        if (trieNode != null) {
            trieNode.setLeaf(true);
        }
    }

    /**
     * after searching the word -> updating all the parent nodes about the new search operation and update the suggestions accordingly
     * @param lastTrieNode
     * @param word
     */
    private void updateParentsSuggestions(TrieNode lastTrieNode, String word) {
        TrieNode parentNode = lastTrieNode.getParent();
        Suggestion suggestion = new Suggestion(word, lastTrieNode.getNumOfSearches());
        while (parentNode.getParent() != null) {
            parentNode.updateNodeSuggestions(suggestion);
            parentNode = parentNode.getParent();
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode trieNode = searchNode(word);
        if (trieNode != null && trieNode.isLeaf()) {
            trieNode.updateNumOfSearches();
            updateParentsSuggestions(trieNode, word);
            return true;
        }
        return false;
    }


    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    /**
     * search the node of the last letter of the prefix
     * @param prefix
     * @return
     */
    public TrieNode searchNode(String prefix) {
        Map<Character, TrieNode> children = root.getChildren();
        TrieNode trieNode = null;

        for (char c : prefix.toCharArray()) {
            if (children.containsKey(c)) {
                trieNode = children.get(c);
                children = trieNode.getChildren();
            } else {
                return null;
            }
        }
        return trieNode;
    }

    /**
     * suggest to the user the list of the most searches in the web
     * @param userInput
     * @return
     */
    public List<Suggestion> suggest(String userInput) {
        if (startsWith(userInput)) {
            TrieNode tn = searchNode(userInput);
            return tn.getSuggestionList().subList(0, Math.min(tn.getSuggestionList().size(), MAX_SIZE));
        }
        return Collections.emptyList();
    }


    public static void main(String[] args) {

    }
}
