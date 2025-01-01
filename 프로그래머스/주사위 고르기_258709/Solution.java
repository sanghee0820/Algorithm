import java.util.*;

class Solution {
    private int[] answer = {};
    private int maxWin = -1;
    
    public int[] solution(int[][] dice) {
        answer = new int[dice.length / 2];
        generateCombination(dice, 1);
        
        return answer;
    }
    
    public void generateCombination(int[][] dice, int picked){
        if(Integer.bitCount(picked) == dice.length / 2){
            List<int[]> a = new ArrayList<>();
            List<int[]> b = new ArrayList<>();
            for(int i = 0; i < dice.length; i++) {
                if((picked & (1 << i)) == 0) {
                    b.add(dice[i]);
                    continue;
                }
                a.add(dice[i]);
            }
            List<Integer> aScores = new ArrayList<>();
            calcScores(a, 0, 0, aScores);
            List<Integer> bScores = new ArrayList<>();
            calcScores(b, 0, 0, bScores);
            var wins = getAWinCount(aScores, bScores);
            if(maxWin < wins) {
                int idx = 0;
                for(int i = 0; i < dice.length; i++) {
                    if((picked & (1 << i)) != 0) {
                        answer[idx++] = i + 1;
                    }
                }
                maxWin = wins;    
            }
        }
        
        if(picked >= (1 << dice.length)) {
            return;
        }
        
        generateCombination(dice, picked + 1);
        return;
    }
    
    public void calcScores(List<int[]> dices, int current, int score, List<Integer> results) {
        if(dices.size() == current) {
            results.add(score);
            return;
        }
        
        for(int index = 0; index < 6; index++) {
            calcScores(dices, current + 1, score + dices.get(current)[index], results);
        }
    }
    
    public int getAWinCount(List<Integer> aScores, List<Integer> bScores) {
        Collections.sort(aScores);
        Collections.sort(bScores);
        int winCount = 0;

        for(int aScore : aScores) {
            winCount += binarySearch(bScores, aScore);
        }

        return winCount;
    }

    public int binarySearch(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();

        while(left < right) {
            int mid = left + (right - left) / 2;
            if(list.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}