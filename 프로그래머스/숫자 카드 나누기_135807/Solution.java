import java.util.*;

class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int gcdA = arrayA[0];
        int gcdB = arrayB[0];
        for (int index = 1; index < arrayA.length; index++) {
            gcdA = gcd(gcdA, arrayA[index]);
            gcdB = gcd(gcdB, arrayB[index]);
        }
        
        int answerA = 0;
        if (gcdA > 1) {
            boolean valid = true;
            for (int b : arrayB) {
                if (b % gcdA == 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                answerA = gcdA;
            }
        }
        
        int answerB = 0;
        if (gcdB > 1) {
            boolean valid = true;
            for (int a : arrayA) {
                if (a % gcdB == 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) { 
                answerB = gcdB;
            }
        }
        
        return Math.max(answerA, answerB);
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}