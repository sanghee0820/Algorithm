import java.util.*;
class Solution {
    public int solution2(String name) {
        int answer = 0;
        String[] init = new String[name.length()];
        Arrays.fill(init, "A");
        String[] names = name.split("");
        answer = search(init, names, 0, 1, names.length - 1, 0);
        return answer;
    }
    
    private int search(String[] names, String[] target, int current, int left, int right, int count){
        String[] nextNickName = names.clone();
        int alphabetMove = searchAlphabetMove(target[current]);
        nextNickName[current] = target[current];
        
        if(String.join("", nextNickName).equals(String.join("", target))){
            return count + alphabetMove;
        }
    
        int rightMove = searchRightMoveCnt(names, current, left);
        int leftMove = searchLeftMoveCnt(names, current, right);
        int leftCnt = search(nextNickName, target, right, left, right - 1, count + leftMove + alphabetMove);
        int rightCnt = search(nextNickName, target, left, left + 1, right, count + rightMove + alphabetMove);
        
        return Math.min(leftCnt, rightCnt);
    }
    
    private int searchLeftMoveCnt(String[] names, int current, int target){
        if(current > target){ 
            return current - target;
        }
        return names.length - target + current;
    }
    
    private int searchRightMoveCnt(String[] names, int current, int target){
        if(current < target){ 
            return target - current;
        }
        return names.length - current + target;
    }
    
    private int searchAlphabetMove(String target){

        int move1 = Math.abs('A' - target.charAt(0));
        int move2 = Math.abs('Z' - target.charAt(0) + 1);

        return Math.min(move1, move2);
    }
}