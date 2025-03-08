import java.util.*;
class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        Map<String, List<Integer>> permutationMap = new HashMap<>();
        for(String humanInfo : info) {
            var data = humanInfo.split(" ");
            addPermutation(data, 0, permutationMap);
        }
        
        for(String key : permutationMap.keySet()) {
            Collections.sort(permutationMap.get(key));
        }
        
        for(int index = 0; index < query.length; index++) {
            var curQuery = query[index];
            var queryData = curQuery.split("\\s+and\\s+|\\s+");
            var key = String.join("", Arrays.copyOfRange(queryData, 0, queryData.length - 1));
            var score = Integer.parseInt(queryData[queryData.length - 1]);
            var scores = permutationMap.getOrDefault(key, new ArrayList<>());
            answer[index] = getCount(scores, score);
        }
        return answer;
    }
    
    private void addPermutation(String[] data, int curIndex, Map<String, List<Integer>> map){
        if(curIndex == data.length - 1) {
            var key = String.join("", Arrays.copyOfRange(data, 0, data.length - 1));
            map.putIfAbsent(key, new ArrayList<>());
            
            map.get(key).add(Integer.parseInt(data[data.length - 1]));
            return;
        }
        
        addPermutation(data, curIndex + 1, map);
        
        var cur = data[curIndex];
        data[curIndex] = "-";
        addPermutation(data, curIndex + 1, map);
        data[curIndex] = cur;
    }
    
    private int getCount(List<Integer> scores, int score) {
        
        int left = 0;
        int right = scores.size();
        
        while(left < right) {
            int mid = (left + right) / 2;
            int midScore = scores.get(mid);
            
            if(score <= midScore) {
                right = mid;
                continue;
            }
            
            left = mid + 1;
        }
        
        return scores.size() - left;
    }
}