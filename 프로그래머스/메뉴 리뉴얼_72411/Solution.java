import java.util.*;
import java.util.stream.*;

class Solution {
    Map<String, Integer> result = new HashMap<>();
    public String[] solution(String[] orders, int[] course) {
        for(String order: orders){
            var menus = order.split("");
            Arrays.sort(menus);
            for(int courseCnt : course){
                makeCourse(new String[courseCnt], courseCnt, 0, 0, menus);
            }
        }
        
        List<String> answers = new ArrayList<>();
        
        for(int i = 0; i < course.length; i++){
            List<String> current = new ArrayList<>();
            int maxCnt = 2;
            var cnt = course[i];
            for(String key : result.keySet()){
                if(key.length() != cnt){
                    continue;
                }
                var value = result.get(key);
                if(value > maxCnt){
                    current.clear();
                    current.add(key);
                    maxCnt = value;
                    continue;
                }
                if(value == maxCnt){
                    current.add(key);
                }   
            }
            answers.addAll(current);
        }
        Collections.sort(answers);
        var answer = answers.stream().toArray(String[]::new);
        return answer;
    }
    
    public void makeCourse(String[] selected, int max, int cnt, int currentIndex, String[] menus){
        if(max == cnt){
            var courseInfo = String.join("", selected);
            result.putIfAbsent(courseInfo, 0);
            result.put(courseInfo, result.get(courseInfo) + 1);
            return;
        }
        
        for(int i = currentIndex; i < menus.length; i++){
            selected[cnt] = menus[i];
            makeCourse(selected, max, cnt + 1, i + 1, menus);
        }
    }
}