import java.util.*;
import java.util.stream.*;
class Solution {
    public final List<Character> chars = Arrays.asList(
        'A', 'E', 'I', 'O', 'U', ' '
    );
    public final List<Integer> charCnt = Arrays.asList(
        781, 156, 31, 6, 1
    );
    
    public int solution(String word) {
        int answer = 0;
        List<Character> words = word.chars()
                .mapToObj(e -> (char) e)
                .collect(Collectors.toList());
        
        for(int i=0; i < words.size(); i++) {
            answer += charCnt.get(i)*chars.indexOf(words.get(i)) + 1;
        } 
        
        return answer;
    }
}