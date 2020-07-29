package graph.unionfind;

import lib.Printer;

import java.util.*;

public class Problem1452_PeopleWhoseListOfFavoriteCompaniesIsNotSubsetOfAnotherList {

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0, j = -1, n = favoriteCompanies.size();
        Set<Integer>[] A = new Set[n];
        for (List<String> list : favoriteCompanies) {
            A[++j] = new HashSet<>();
            for (String company : list) {
                if (!map.containsKey(company)) map.put(company, i++);
                A[j].add(map.get(company));
            }
        }
        return union_find(A);
    }

    int parents[];

    private List<Integer> union_find(Set<Integer>[] A) {

        int n = A.length;
        parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;
        for (int i = 0; i < n-1; i++) {
            for (int j = i+1; j < n; j++) {
                int pi = find(i), pj = find(j);
                if (pi == pj) continue;
                if (A[pi].containsAll(A[pj])) parents[pj] = pi;
                else if (A[pj].containsAll(A[pi])) parents[pi] = pj;
            }
        }
        Set<Integer> set = new HashSet<>();
        for (int i : parents) set.add(find(i));
        List<Integer> res = new ArrayList<>(set);
        Collections.sort(res);
        return res;
    }

    private int find(int i) {
        return (parents[i] == i) ? i : find(parents[i]);
    }

    public static void run() {
        Problem1452_PeopleWhoseListOfFavoriteCompaniesIsNotSubsetOfAnotherList solution = new Problem1452_PeopleWhoseListOfFavoriteCompaniesIsNotSubsetOfAnotherList();
        List<List<String>> favoriteCompanies = Arrays.asList(
                Arrays.asList("leetcode","google","facebook"),  // 0, 1, 2
                Arrays.asList("google","microsoft"),            // 1, 3
                Arrays.asList("google","facebook"),             // 1, 2
                Arrays.asList("google"),                        // 1
                Arrays.asList("amazon")                         // 4
        );

        List<Integer> res = solution.peopleIndexes(favoriteCompanies);
        Printer.print(res);
    }
}
