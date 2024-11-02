import java.util.*;

class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        for(int index = 0; index < cards.length; index++){
            var first = search(index + 1, cards);
            if(first.size() == cards.length){
                continue;
            }
            
            for(int next = 0; next < cards.length; next++){
                if(first.contains(next + 1)){
                    continue;
                }
                var second = search(next + 1, cards);
                answer = Math.max(answer, first.size() * second.size());
            }
            
        }
        
        return answer;
    }
    
    public Set<Integer> search(int startIndex, int[] cards){
        Set<Integer> contains = new HashSet<>();
        int next = startIndex;
        
        while(!contains.contains(next)){
            contains.add(next);
            next = cards[next - 1];
        }
        
        return contains;
    }
}