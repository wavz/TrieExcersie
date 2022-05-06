import java.util.*;

public class Trie {
    private final TrieNode root;
    private final ArrayList<String> relatedWords;
    private TrieNode prefixRoot;
    private String curPrefix;

    public Trie() {
        root = new TrieNode();
        relatedWords = new ArrayList<>();
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

    private void updateParentsSuggestions(TrieNode lastTrieNode, String word){
        TrieNode parentNode = lastTrieNode.getParent();
        Suggestion suggestion = new Suggestion(word, lastTrieNode.getNumOfSearches());
        while (parentNode.getParent() != null){
            parentNode.updateNodeSuggestions(suggestion);
            parentNode = parentNode.getParent();
        }
    }

    // Returns if the word is in the trie.
    public void search(String word) {
        TrieNode trieNode = searchNode(word);
        if (trieNode != null && trieNode.isLeaf()){
            trieNode.updateNumOfSearches();
            updateParentsSuggestions(trieNode, word);
        }
    }


    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    public TrieNode searchNode(String prefix) {
        Map<Character, TrieNode> children = root.getChildren();
        TrieNode trieNode = null;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (children.containsKey(c)) {
                trieNode = children.get(c);
                children = trieNode.getChildren();
            } else {
                return null;
            }
        }

        prefixRoot = trieNode;
        curPrefix = prefix;
        relatedWords.clear();
        return trieNode;
    }



    void wordsFinderTraversal(TrieNode node) {

        if (node.isLeaf()) {
            TrieNode altair;
            altair = node;

            Stack<String> hstack = new Stack<>();

            while (altair != prefixRoot) {
                hstack.push(Character.toString(altair.getC()));
                altair = altair.getParent();
            }

            StringBuilder wrd = new StringBuilder(curPrefix);

            while (!hstack.empty()) {
                wrd.append(hstack.pop());
            }

            relatedWords.add(wrd.toString());

        }

        Set<Character> kset = node.getChildren().keySet();
        Iterator<Character> itr = kset.iterator();
        ArrayList<Character> aloc = new ArrayList<>();

        while (itr.hasNext()) {
            Character ch = itr.next();
            aloc.add(ch);
        }

        for (Character character : aloc) {
            wordsFinderTraversal(node.getChildren().get(character));
        }

    }


    public void displayFoundWords() {
        System.out.println("_______________");
        for (String word : relatedWords) {
            System.out.println(word);
        }
        System.out.println("________________");

    }

    public List<Suggestion> suggest(String userInput){
        if (startsWith(userInput)){
            TrieNode tn = searchNode(userInput);
            return tn.getSuggestionList();
        }
        return Collections.emptyList();
    }




    public static void main(String[] args) {


        Trie prefixTree;

        prefixTree = new Trie();

        prefixTree.insert("GOING");
        prefixTree.insert("GONG");
        prefixTree.insert("PAKISTAN");
        prefixTree.insert("SHANGHAI");
        prefixTree.insert("GONDAL");
        prefixTree.insert("GODAY");
        prefixTree.insert("GODZILLA");


        prefixTree.search("GODZILLA");
        prefixTree.search("GODZILLA");
        prefixTree.search("GODZILLA");
        prefixTree.search("GODZILLA");
        prefixTree.search("GODZILLA");
        prefixTree.search("GODAY");
        prefixTree.search("GODAY");
        prefixTree.search("GODAY");
        prefixTree.search("GONG");
        prefixTree.search("GONG");
        prefixTree.search("GONG");
        prefixTree.search("GONG");
        prefixTree.search("GOING");


        List<Suggestion> userSuggestions = prefixTree.suggest("GO");
        Collections.reverse(userSuggestions);
        System.out.println(userSuggestions);
//
//        if (prefixTree.startsWith("GOD")) {
//            TrieNode tn = prefixTree.searchNode("GOD");
//            prefixTree.wordsFinderTraversal(tn);
//            prefixTree.displayFoundWords();
//
//        }
    }


}//
