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

        prefixTree.insert("GOING");
        prefixTree.insert("GONG");

        assertTrue(prefixTree.search("GOING"));
        assertFalse(prefixTree.search("GONDOLA"));

    }

    @Test
    public void testStartWithFunction(){

        prefixTree.insert("GOING");
        prefixTree.insert("GONG");

        assertTrue(prefixTree.startsWith("GO"));
        assertFalse(prefixTree.startsWith("JO"));
    }

    @Test
    public void testUserSuggestionsFunction(){
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
        assertEquals("GODZILLA", userSuggestions.get(0).getSuggestion());

        prefixTree.search("GOING");
        prefixTree.search("GOING");
        prefixTree.search("GOING");
        prefixTree.search("GOING");
        prefixTree.search("GOING");
        prefixTree.search("GOING");
        prefixTree.search("GOING");

        List<Suggestion> updatedUserSuggestions = prefixTree.suggest("GOI");
        Collections.reverse(updatedUserSuggestions);
        System.out.println(updatedUserSuggestions);
        assertEquals("GOING", updatedUserSuggestions.get(0).getSuggestion());

    }

}
