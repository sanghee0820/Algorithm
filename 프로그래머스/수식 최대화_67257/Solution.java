import java.util.*;
class Solution {
    static String splitter = "(?=[+\\-*])|(?<=[+\\-*])";
    static String[][] combinsations = {{"+","-","*"},{"+","*","-"},{"-","+","*"},{"-","*","+"},{"*","+","-"},{"*","-","+"}};
    public long solution(String expression) {
        long answer = 0;
        for(String[] combination : combinsations){
            List<String> expressionList = new ArrayList<>(List.of(expression.split(splitter)));
            for(String comb : combination){
                for(int i = 1; i < expressionList.size(); i++){
                    if(expressionList.get(i).equals(comb)){
                        long calculated = calculate(comb, Long.valueOf(expressionList.get(i-1)), Long.valueOf(expressionList.get(i+1)));
                        expressionList.add(i - 1, String.valueOf(calculated));
                        expressionList.remove(i);
                        expressionList.remove(i);
                        expressionList.remove(i);
                        i--;
                        continue;
                    }
                    i++;
                }
            }
            
            answer = Math.max(Math.abs(Long.valueOf(expressionList.get(0))), answer);
            
        }
        return answer;
    }
        
    public long calculate(String comb, long a, long b){
        if(comb.equals("*")){
            return a * b;
        }
        if(comb.equals("-")){
            return a - b;
        }
        return a + b;
    }
}