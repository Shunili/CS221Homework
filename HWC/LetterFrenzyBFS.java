import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Shuni Li
 * COMP221 Algorithms Design and Analysis HWC
 * Professor Shilad Sen
 * 2/14/16
 *
 * This program uses the breath-first-search(BFS) algorithm to chart a path from
 * a source four letter word to a destination four letter word using "valid"
 * words as intermediaries. Each intermediary must differ by only one letter.
 *
 * For example, to go from BOAT to SHIP, this program prints the sequence
 * BOAT -> BHAT -> SHAT -> SHIT -> SHIP.
 * To go from ABAS to JUKE, this program returns
 * ABAS -> AUAS -> AUKS -> YUKS -> YUKE -> JUKE.
 *
 * I adopted a depth-first-search(DFS) algorithm before (see the other java file).
 * It has a similar pattern to the exhaustive search problems we have done in class,
 * which uses a recursion to go through all possible paths from the source word until
 * it first reaches the destination word. When I ran that DFS algorithm, I encountered
 * a stack overflow error message. I think it happens because there are too many recursive
 * calls waiting in the stack, given the large input dictionary. When I tried to change
 * the limit for the number of recursive calls (VM options), the program ran successfully.
 *
 * Then, I switched to this current BSF algorithm. This algorithm not only solves the
 * problem of too many recursive calls, but also seeks the shortest path from the source
 * word to the destination word. First, it checks all connected words of the source word
 * layer by layer. This algorithm is more efficient in seeking shortest path because it
 * goes horizontally and avoids going too deeply. However, it uses more memory to store
 * the paths of words in the queues than DFS algorithm.
 *
 * The best case happens when the destination word is one letter off from the source word.
 * In this case, the algorithm has a O(1) constant time complexity because we just need
 * to check all neighbors of the source word.
 *
 * The worst case happens when almost all words are connected with other. It requires a O(n^2)
 * time complexity because it might need to go through all edges(n^2) in the graph.
 *
 * It is difficult to determine the time complexity of most-likely cases because we are not sure
 * how sparse the graph is. And we might need to write another function to calculate the number
 * of edges, in order to determine the time complexity for average cases.
 */

public class LetterFrenzyBFS {

    /**
     * The helper method for changeWord contains the main BSF algorithm
     * @param src a string representing the 4 letter source word
     * @param dst a string representing the 4 letter destination word
     * @param lines a set contains all words in the dictionary
     * @param explored a set contains all words that have been checked
     * @return a string representing the path from src to dst
     */
    public static String changeWordHelper(String src, String dst, HashSet<String> lines, HashSet<String> explored) {

        Queue<String> frontier = new LinkedList<>();
        frontier.add(src);

        while(!frontier.isEmpty()) {
            String current = frontier.poll();
            String tmp = current.substring(current.length() - 4);

            for (int i = 0; i < 4; i++) {
                for (char c = 'A'; c <= 'Z'; c++) {

                    String target = tmp.substring(0, i) + c + tmp.substring(i + 1);
                    String result = current + " -> " + target;   //updated path

                    if (target.equals(dst)) {
                        return result;
                    }
                    if (lines.contains(target) && (!explored.contains(target))) {
                        explored.add(target);
                        frontier.add(result);
                    }
                }
            }
        }
        return null;
    }

    /**
     * changeWord method. This method mainly converts the inout file
     * to a set of words and calls the helper method above.
     * @param file a dictionary file containing all possible words
     * @param src a string representing the 4 letter source word
     * @param dst a string representing the 4 letter destination word
     * @return a string representing the path from src to dst
     */
    public static String changeWord(File file, String src, String dst){

        BufferedReader br = null;
        HashSet<String> lines = new HashSet<>();

        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                lines.add(line);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ignored) {}
            }
        }

        if (lines.contains(src) && lines.contains(dst)){ //check if src and dst are in dictionary
            HashSet<String> explored = new HashSet<>();
            return changeWordHelper(src, dst, lines, explored);
        } else {
            return null;
        }
    }

    /**
     * Main method contains a few test cases
     * @param args
     */
    public static void main(String[] args) {

        //This file path needs to be changed for different computers
        File file = new File("LetterFrenzy/fourletterwords.txt");

        System.out.println(changeWord(file, "ACAI", "SHIP")); //return null
        System.out.println(changeWord(file, "ZZZZ", "AAHS")); //return null

        System.out.println(changeWord(file, "BOAT", "SHIP")); //return BOAT -> BHAT -> SHAT -> SHIT -> SHIP
        System.out.println(changeWord(file, "ABAS", "JUKE")); //return ABAS -> AUAS -> AUKS -> YUKS -> YUKE -> JUKE

        System.out.println(changeWord(file, "AMBO", "AMMO")); //return AMBO -> AMMO
        System.out.println(changeWord(file, "AMBO", "AMBP")); //return null

        System.out.println(changeWord(file, "ONLY", "ONYX")); //return null
        System.out.println(changeWord(file, "ORYX", "ONYX")); //return ORYX -> ONYX
    }
}
