import java.util.*;
class Solution {
    
    public class StringInfo{
        String string;
        int cnt;
        
        public StringInfo(String string, int cnt){
            this.string = string;
            this.cnt = cnt;
        }
        
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        
        Queue<StringInfo> queue = new LinkedList<>();
        queue.add(new StringInfo(begin, 0));
        
        while(!queue.isEmpty()){
            StringInfo current = queue.remove();
            
            if(current.string.equals(target)){
                answer = current.cnt;
                return answer;
            }
            
            String[] cur = current.string.split("");
            for(int i = 0; i < words.length; i++){
                if(visited[i]){
                    continue;
                }
                String[] alphabets = words[i].split("");
                
                int diff = 0;
                for(int j = 0; j < alphabets.length; j++){
                    if(!cur[j].equals(alphabets[j])){
                        diff++;
                    }
                }
                if(diff == 1){
                    queue.add(new StringInfo(words[i], current.cnt + 1));
                    visited[i] = true;
                }
            }
        }
        return answer;
    }
}