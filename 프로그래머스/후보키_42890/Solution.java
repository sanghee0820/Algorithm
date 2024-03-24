import java.util.*;


// 1개 부터 n개까지 column 집합을 확인
// 최소성을 만족해야하므로, 1에서 쓰인 column은 확인 X
class Solution {
    // private HashSet<Integer> checkedColumn = new HashSet<>();
    private HashSet<String> checkedColumn = new HashSet<>();
    // private HashSet<Integer> temp = new HashSet<>();
    
    private int answer = 0;
    
    public int solution(String[][] relation) {
        for(int i = 1; i < relation[0].length + 1; i++){
            DFS(relation, new ArrayList<>(), i, 0);
        }
        
        return checkedColumn.size();
    }
    
    public void DFS(String[][] relation, List<Integer> combination, int cnt, int start){
        if(combination.size() == cnt){
            Set<String> checkKey = new HashSet<>();
            for(int i = 0 ; i < relation.length; i++){
                String str = "";
                for(int index : combination){
                    str += relation[i][index] + ",";
                }
                checkKey.add(str);
            }
            if(checkKey.size() == relation.length){
                String key = "";
                for(int i = 0; i < combination.size(); i++){
                    key += String.valueOf(combination.get(i));  
                }
                for(String existKey : checkedColumn){
                    boolean flag = true;
                    for(int i = 0; i < existKey.length(); i++){
                        if(!key.contains(String.valueOf(existKey.charAt(i)))){
                            flag = false;
                        }
                    }
                    if(flag){
                        return;
                    }
                }
                checkedColumn.add(key);
            }
            return;
        }
        
        for(int i = start; i < relation[0].length; i++){
            combination.add(i);
            DFS(relation, combination, cnt, i + 1);
            combination.remove(combination.size() - 1);
        }
    }
}