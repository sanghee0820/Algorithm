import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[]{1, gems.length};
        Map<String, Integer> gemTypes = new HashMap<>();
        for(String gem : gems){
            gemTypes.putIfAbsent(gem, 0);
        }
        Set<String> contains = new HashSet<>();
        int left = 0;
        int right = 1;
        addGem(gems[left], gemTypes, contains);
        while(right <= gems.length){
            while(gemTypes.keySet().size() == contains.size()){
                if(answer[1] - answer[0] > right - left - 1){
                    answer[0] = left + 1;
                    answer[1] = right;
                }
                removeGem(gems[left++], gemTypes, contains);
            }
            if(right == gems.length){
                break;
            }
            addGem(gems[right++], gemTypes, contains);
        }
        return answer;
    }
    
    public void addGem(String key, Map<String, Integer> gemTypes, Set<String> contains){
        gemTypes.put(key, gemTypes.get(key) + 1);
        contains.add(key);
    }
    
    public void removeGem(String key, Map<String, Integer> gemTypes, Set<String> contains){
        gemTypes.put(key, gemTypes.get(key) - 1);
        if(gemTypes.get(key) == 0){
            contains.remove(key);
        }
    }
}