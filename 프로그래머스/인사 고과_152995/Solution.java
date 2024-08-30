import java.util.*;

class Solution {
    class Employee{
        int score1;
        int score2;
        int totalScore;
        
        public Employee(int score1, int score2){
            this.score1 = score1;
            this.score2 = score2;
            this.totalScore = score1 + score2;
        }
    }
    public int solution(int[][] scores) {
        int answer = 1;
        
        if(checkWanhooIncentive(scores)){
            return -1;
        }
        var wanHoo = new Employee(scores[0][0], scores[0][1]);
        
        List<Employee> list = new ArrayList<>();
        Arrays.sort(scores, (a,b) -> {
            if(a[0] == b[0]){
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        filterIncentive(scores, list);
        Collections.sort(list, (a,b) -> Integer.compare(b.totalScore, a.totalScore));
        for(Employee current : list){
            if(current.totalScore <= wanHoo.totalScore){
                break;
            }
            answer++;
        }
        
        return answer;
    }
    
    private boolean checkWanhooIncentive(int[][] scores){
        int[] wanHooScore = scores[0];
        
        boolean noIncentive = false;
        for(int i = 1; i < scores.length; i++){
            int[] current = scores[i];
            if(noIncentive(wanHooScore, current)){
                noIncentive = true;
                break;
            }
        }
        
        return noIncentive;
    }
    
    private void filterIncentive(int[][] scores, List<Employee> list){
        int maxScore = -1;
        for(int i = 0; i < scores.length; i++){
            int[] target = scores[i];
            if(target[1] < maxScore){
                continue;
            }
            list.add(new Employee(target[0], target[1]));
            maxScore = target[1];
        }
    }
    private boolean noIncentive(int[] employee1, int[] employee2){
        return employee1[0] < employee2[0] && employee1[1] < employee2[1];
    }
}