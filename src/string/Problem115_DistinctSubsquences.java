package string;

/**
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 */
public class Problem115_DistinctSubsquences {

    public int numDistinct(String S, String T) {

        /*
              S 0123....j
            T +----------+
              |1111111111|
            0 |0         |
            1 |0         |
            2 |0         |
            . |0         |
            . |0         |
            i |0         |
         */
        int[][] mem = new int[T.length()+1][S.length()+1];

        // filling the first row: with 1s
        for(int j=0; j<=S.length(); j++) {
            mem[0][j] = 1;
        }

        // the first column is 0 by default in every other rows but the first, which we need.

        for(int i=0; i<T.length(); i++) {
            for(int j=0; j<S.length(); j++) {
                /*
if T[i] == S[j] it means we have a choice, either we take the jth character to find the entire T
or we do not take the jth character to find the entire T.
If we take the jth character - that means now we have to find a solution to T[i-1] (because T[i] == S[j]))
If we do not take the jth character - that means now we have to find a solution to T[i] from S[j-1] (not taking the jth character).
The total number of permutations would be = permutations with considering the jth character + permutations without considering the jth character.
                 */
                if(T.charAt(i) == S.charAt(j)) {
                    mem[i+1][j+1] = mem[i][j] + mem[i+1][j];
                } else {
                    mem[i+1][j+1] = mem[i+1][j];
                }
            }
        }

        return mem[T.length()][S.length()];
    }
}
