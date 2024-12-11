import java.util.*;

class Diff{
    private static final String UNDER = "<";
    private static final String OVER = ">";
    private static final String EQUAL = "=";
    String from;
    String to;
    Integer distance;
    String equation;
    
    public Diff(String from, String to, String distance, String equation){
        this.from = from;
        this.to = to;
        this.distance = Integer.parseInt(distance);
        this.equation = equation;
        
    }
    
    public boolean isMatch(int fromIndex, int toIndex){
        int distance = Math.abs(fromIndex - toIndex) - 1;
        
        if(UNDER.equals(equation)){
            return distance < this.distance;
        }
        if(EQUAL.equals(equation)){
            return distance == this.distance;
        }
        
        return distance > this.distance;
    }
}

class Solution {
    private static final String[] FRIENDS = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
    private List<Diff> diffs = new ArrayList<>();
    private int answer = 0;
    public int solution(int n, String[] data) {
        for(String condition : data){
            String[] conditionData = condition.split("");
            diffs.add(new Diff(conditionData[0], conditionData[2], conditionData[4], conditionData[3]));
        }
        
        dfs(new HashSet<>(), "", 0);
        return answer;
    }
    
    public void dfs(Set<String> contains, String position, int n){
        if(n == FRIENDS.length){
            if(matchAllConditions(position)){
                answer++;
            }
            return;
        }
        
        for(String friend : FRIENDS){
            if(contains.contains(friend)){
                continue;
            }
            contains.add(friend);
            dfs(contains, position + friend, n + 1);
            contains.remove(friend);
        }
    }
    
    public boolean matchAllConditions(String position){
        HashMap<String, Integer> indexMap = new HashMap<>();
        for(int index = 0; index < position.length(); index++){
            indexMap.put(String.valueOf(position.charAt(index)), index);
        }
        
        for(Diff diff : diffs){
            int from = indexMap.get(diff.from);
            int to = indexMap.get(diff.to);
            if(diff.isMatch(from, to)){
                continue;
            }
            return false;
        }
        return true;
    }
    
}