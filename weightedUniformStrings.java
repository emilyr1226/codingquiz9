import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'weightedUniformStrings' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING s
     *  2. INTEGER_ARRAY queries
     */

    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
    // Write your code here
    HashMap <Character, Integer> weights = new HashMap <> (); 
    char [] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    for (int i = 0; i < alphabet.length; i++) {
        weights.put(alphabet[i], i+1);
    }
    
    List <Integer> substringWeights = new ArrayList <> ();
    int currentCount = 0;
    for (int k = 0; k < s.length(); k++) {
        if (k == 0) {
            currentCount = 1;
            substringWeights.add(currentCount * weights.get(s.charAt(k)));
        }
        else if (s.charAt(k) == s.charAt(k-1)) {
            currentCount++;
            substringWeights.add(currentCount * weights.get(s.charAt(k)));
        }
        else {
            currentCount = 1;
            substringWeights.add(currentCount * weights.get(s.charAt(k)));
        }
    }
    
    List<String> answers = new ArrayList <> ();
    for (int j : queries) {
        if (substringWeights.contains(j)) {
            answers.add("Yes");
        }
        else {
            answers.add("No");
        }
    }
    return answers;
}
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        List<String> result = Result.weightedUniformStrings(s, queries);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}