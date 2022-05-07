package main;

import common.Suggestion;
import common.Trie;

import java.util.Collections;
import java.util.List;

public class SearchSolution {

    public static void main(String[] args) {
        Trie prefixTree = new Trie();

        prefixTree.insert("book");
        prefixTree.insert("booking");
        prefixTree.insert("bool");
        prefixTree.insert("boom");
        prefixTree.insert("bookseller");
        prefixTree.insert("boost");
        prefixTree.insert("dog");
        prefixTree.insert("basket");


        prefixTree.search("book");
        prefixTree.search("book");
        prefixTree.search("book");
        prefixTree.search("book");
        prefixTree.search("book");
        prefixTree.search("booking");
        prefixTree.search("booking");
        prefixTree.search("booking");
        prefixTree.search("boom");
        prefixTree.search("boom");
        prefixTree.search("boom");
        prefixTree.search("boom");
        prefixTree.search("boost");

        List<Suggestion> userSuggestions = prefixTree.suggest("boo");
        Collections.reverse(userSuggestions);
        System.out.println("The prefix tree search result is book, since the book word was searched 5 times:\n");
        System.out.println(userSuggestions + "\n");


        System.out.println("The prefix tree will be updated and print boost as the first out of 10 options: \n");

        prefixTree.search("boost");
        prefixTree.search("boost");
        prefixTree.search("boost");
        prefixTree.search("boost");
        prefixTree.search("boost");
        prefixTree.search("boost");
        prefixTree.search("boost");

        List<Suggestion> updatedUserSuggestions = prefixTree.suggest("boo");
        Collections.reverse(updatedUserSuggestions);
        System.out.println(updatedUserSuggestions);
    }

}
