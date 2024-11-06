import java.util.*;
import java.util.stream.*;

class Solution {
    private final List<Integer> bases = List.of(2, 3, 4, 5, 6, 7, 8, 9);
    public String[] solution(String[] expressions) {
        List<String> complete = new ArrayList<>();
        List<String> secret = new ArrayList<>();
        for(String expression : expressions){
            if(expression.contains("X")){
                secret.add(expression);
            }
            complete.add(expression);
        }
        
        List<Integer> targetBase = new ArrayList<>();
        for(int base : bases){
            boolean correct = true;
            for(String expression : complete){
                var equation = expression.split(" = ");

                String[] variables;
                String answer = equation[1];
                String operator = "";

                if(equation[0].contains(" - ")){
                    operator = "-";
                    variables = equation[0].split(" - ");
                }else {
                    operator = "+";
                    variables = equation[0].split(" \\+ ");
                }
                
                boolean result;
                try{
                    result = isCorrect(variables, answer, operator, base);
                }catch(Exception e){
                    result = false;
                }
                if(!result){
                    correct = false;
                    break;
                }
            }
            if(correct){
                targetBase.add(base);
            }
        }
        
        List<String> answers = new ArrayList<>();
        for(String expression : secret){
            var equation = expression.split(" = ");

            String[] variables;
            String answer = equation[1];
            String operator = "";

            if(equation[0].contains(" - ")){
                operator = "-";
                variables = equation[0].split(" - ");
            }else {
                operator = "+";
                variables = equation[0].split(" \\+ ");
            }
            
            String expectAnswer = null;
            boolean sameAnswer = true;
            for(int base : targetBase){
                String calcAnswer;
                try{
                    calcAnswer = calc(variables, operator, base);
                }catch(Exception e){
                    continue;
                }
                
                if(expectAnswer == null){
                    expectAnswer = calcAnswer;
                    continue;
                }
                if(!expectAnswer.equals(calcAnswer)){
                    sameAnswer = false;
                    break;
                }
            }
            
            if(sameAnswer){
                expression = expression.replace("X", expectAnswer);
            }else{
                expression = expression.replace("X", "?");
            }
            answers.add(expression);
        }
        
        return answers.stream().toArray(String[]::new);
    }
    
    public boolean isCorrect(String[] variables, String answer, String operator, int base) throws Exception{
        var oneOrigins = Arrays.stream(variables[0].split("")).map(Integer::parseInt).collect(Collectors.toList());
        var twoOrigins = Arrays.stream(variables[1].split("")).map(Integer::parseInt).collect(Collectors.toList());
        var one = Integer.parseInt(variables[0], base);
        var two = Integer.parseInt(variables[1], base);
        
        for(int oneOrigin : oneOrigins){
            if(oneOrigin >= base){
                return false;
            }
        }
        for(int twoOrigin : twoOrigins){
            if(twoOrigin >= base){
                return false;
            }
        }
        if(answer.equals("X")){
            return true;
        }
        
        var answerNumber = Integer.parseInt(answer, base);
        if(operator.equals("+")){
            return (one + two) == answerNumber;
        }
        return (one - two) == answerNumber;
    }
    
    public String calc(String[] variables, String operator, int base) throws Exception{
        var one = Integer.parseInt(variables[0], base);
        var two = Integer.parseInt(variables[1], base);
        if(operator.equals("+")){
            return Integer.toString(one + two, base);
        }
        return Integer.toString(one - two, base);
    }
}