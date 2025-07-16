import java.util.*;
import java.util.regex.*;

class Solution2 {
    private static String REGEX = "(\\d+)|([+\\-*/])";
    private static  String[][] operatorAry = new String[][] {
        {"+", "-", "*"},
        {"+", "*", "-"},
        {"-", "+", "*"},
        {"-", "*", "+"},
        {"*", "+", "-"},
        {"*", "-", "+"}
    };
    public long solution(String expression) {
        long answer = 0;


        List<String> originalTokens = splitExpression(expression);
        for(String[] operators : operatorAry) {
            List<String> expressions = new ArrayList<>(originalTokens);
            for(String operator : operators) {
                expressions = calcOperator(operator, expressions);
            }
            answer = Math.max(answer, Math.abs(Long.valueOf(expressions.get(0))));
        }
        return answer;
    }

    public List<String> calcOperator(String operator, List<String> expressions) {
        List<String> newExpression = new ArrayList<>();

        for(int index = 0; index < expressions.size(); index++) {
            String cur = expressions.get(index);
            if(cur.equals(operator)) {
                String before = newExpression.remove(newExpression.size() - 1);
                String next = expressions.get(++index);

                newExpression.add(calcNumbers(before, next, cur));
                continue;
            }
            newExpression.add(cur);
        }

        return newExpression;
    }

    private String calcNumbers(String before, String next, String operator) {
        if(operator.equals("*")) {
            return String.valueOf(Long.valueOf(before) * Long.valueOf(next));
        }
        if(operator.equals("-")) {
            return String.valueOf(Long.valueOf(before) - Long.valueOf(next));
        }
        return String.valueOf(Long.valueOf(before) + Long.valueOf(next));
    }



    public List<String> splitExpression(String expression) {
        List<String> tokens = new ArrayList<>();

        Matcher matcher = Pattern.compile(REGEX).matcher(expression);

        while(matcher.find()) {
            if (matcher.group(1) != null) {
                tokens.add(matcher.group(1));
                continue;
            }

            if (matcher.group(2) != null) {
                tokens.add(matcher.group(2));
            }
        }
        return tokens;
    }
}