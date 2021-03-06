import common.Suggestion;
import common.Trie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TestPrefixTree {

    private Trie prefixTree;

    @BeforeEach
    public void cleanUp(){
        prefixTree = new Trie();
    }


    @Test
    public void testInsertFunction(){

        prefixTree.insert("book");
        prefixTree.insert("booking");

        assertTrue(prefixTree.search("book"));
        assertFalse(prefixTree.search("booki"));

    }

    @Test
    public void testStartWithFunction(){

        prefixTree.insert("booking");
        prefixTree.insert("bank");

        assertTrue(prefixTree.startsWith("bo"));
        assertFalse(prefixTree.startsWith("ob"));
    }

    @Test
    public void testUserSuggestionsFunction(){
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
        System.out.println(userSuggestions);
        assertEquals("book", userSuggestions.get(0).getSuggestion());

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
        assertEquals("boost", updatedUserSuggestions.get(0).getSuggestion());

    }

}
