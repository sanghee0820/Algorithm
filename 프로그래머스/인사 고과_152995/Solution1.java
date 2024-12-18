import java.util.*;

class Score {
    final Boolean wonHo;
    final Integer score1;
    final Integer score2;
    
    public Score(int index, int[] scores) {
        this.wonHo = index == 0;
        this.score1 = scores[0];
        this.score2 = scores[1];
    }
    
    public Integer totalScore() {
        return score1 + score2;
    }
}

class Solution1 {
    public int solution(int[][] scores) {
        List<Score> employees = createEmployeeList(scores);
        List<Score> eligibleEmployees = findEligibleEmployees(employees);
        
        if (eligibleEmployees == null) {
            return -1;
        }
        
        return calculateWonHoRank(eligibleEmployees);
    }
    
    private List<Score> createEmployeeList(int[][] scores) {
        List<Score> employees = new ArrayList<>();
        for (int index = 0; index < scores.length; index++) {
            employees.add(new Score(index, scores[index]));
        }
        
        employees.sort((a, b) -> {
            if (a.score1.equals(b.score1)) {
                return a.score2 - b.score2;
            }
            return b.score1 - a.score1;
        });
        
        return employees;
    }
    
    private List<Score> findEligibleEmployees(List<Score> employees) {
        List<Score> eligibleEmployees = new ArrayList<>();
        int maxScore2 = 0;
        
        for (Score employee : employees) {
            if (employee.score2 >= maxScore2) {
                eligibleEmployees.add(employee);
                maxScore2 = employee.score2;
                continue;
            }
            
            if (employee.wonHo) {
                return null;
            }
        }
        
        eligibleEmployees.sort((a, b) -> b.totalScore() - a.totalScore());
        return eligibleEmployees;
    }
    
    private int calculateWonHoRank(List<Score> eligibleEmployees) {
        int rank = 0;
        int currentScore = -1;
        int sameScoreCount = 1;
        
        for (Score employee : eligibleEmployees) {
            int totalScore = employee.totalScore();
            
            if (totalScore == currentScore) {
                sameScoreCount++;
            } else {
                currentScore = totalScore;
                rank += sameScoreCount;
                sameScoreCount = 1;
            }
            
            if (employee.wonHo) {
                break;
            }
        }
        
        return rank;
    }
}