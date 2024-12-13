import java.util.*;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<List<String>, List<String>> anagrams = new HashMap<>();
        for(String str : strs) {
            List<String> sorted = Arrays.asList(str.split(""));
            Collections.sort(sorted);
            
            anagrams.putIfAbsent(sorted, new ArrayList<>());
            anagrams.get(sorted).add(str);
        }   

        List<List<String>> answer = new ArrayList<>();
        for(List<String> values : anagrams.values()){
            answer.add(values);
        }
        return answer;
    }
}