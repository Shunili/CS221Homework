import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 * Shuni Li
 * COMP221 Algorithms Design and Analysis HWB
 * Professor Shilad Sen
 * 2/14/16
 *
 * The depth-first search algorithm.
 */

public class LetterFrenzyDFS {

    public static String recurseHelper(String src, String dst, HashSet<String> lines, HashSet<String> explored){

        if (src.equals(dst)){
            return src;
        } else {
            explored.add(src);

            for (int i = 0; i < 4; i++) {
                for (char c = 'A'; c <= 'Z'; c++) {
                    String target = src.substring(0,i) + c + src.substring(i+1);
                    if (lines.contains(target) && (!explored.contains(target))) {
                        String result = recurseHelper(target, dst, lines, explored);
                        if (result != null) {
                            return src + " -> " + result;
                        }
                    }
                }
            }
            return null;
        }
    }

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

        HashSet<String> explored = new HashSet<>();
        return recurseHelper(src, dst, lines, explored);
    }

    public static void main(String[] args) {
        File file = new File("LetterFrenzy/fourletterwords.txt");
        System.out.println(changeWord(file, "BOAT", "SHIP"));
    }
}
