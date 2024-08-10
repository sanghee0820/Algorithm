import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        Map<String, List<Integer>> queryMap = new HashMap<>();

        for (String data : info) {
            String[] infos = data.split(" ");
            int score = Integer.parseInt(infos[4]);
            addMap(queryMap, score, infos, 0, "");
        }
        
        for (List<Integer> scores : queryMap.values()) {
            Collections.sort(scores);
        }

        for (int i = 0; i < query.length; i++) {
            String[] queryInfo = query[i].replace(" and ", " ").split(" ");
            String key = String.join("", Arrays.copyOf(queryInfo, queryInfo.length - 1));
            int targetScore = Integer.parseInt(queryInfo[4]);

            if (queryMap.containsKey(key)) {
                List<Integer> scores = queryMap.get(key);
                var index = lowerBound(scores, targetScore);
                if(index < scores.size()){
                    answer[i] = scores.size() - index;
                }
            }
        }
        return answer;
    }

    private void addMap(Map<String, List<Integer>> map, int score, String[] infos, int index, String current) {
        if (index == 4) {
            map.putIfAbsent(current, new ArrayList<>());
            map.get(current).add(score);
            return;
        }
        addMap(map, score, infos, index + 1, current + infos[index]);
        addMap(map, score, infos, index + 1, current + "-");
    }

    private int lowerBound(List<Integer> scores, int target) {
        var index = Collections.binarySearch(scores, target);
        if(index < 0){
            return Math.abs(index) -1;
        }
        while(index >= 0 && scores.get(index).equals(target)){
            index--;
        }
        return index + 1;
    }
}