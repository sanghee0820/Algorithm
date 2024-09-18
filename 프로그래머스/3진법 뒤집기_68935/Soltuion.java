import java.util.*;
class Solution {
    public int solution(int n) {
        String three = Integer.toString(n, 3);
        StringBuilder str = new StringBuilder(three);
        return Integer.parseInt(str.reverse().toString(), 3);
        
    }
}