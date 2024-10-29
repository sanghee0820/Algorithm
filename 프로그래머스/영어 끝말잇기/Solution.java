import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        
        Set<String> said = new HashSet<>();
        var beforeWord = words[0];
        said.add(beforeWord);
        for(int index = 1; index < words.length; index++){
            var word = words[index];
            if(isSameTailAndHead(word, beforeWord) && !said.contains(word)){
                said.add(word);
                beforeWord = word;
                continue;
            }
            answer[1] = index / n + 1;
            answer[0] = index % n + 1;
            break;
        }
        return answer;
    }
    
    public boolean isSameTailAndHead(String a, String b){
        return a.charAt(0) == b.charAt(b.length() - 1);
    }
}