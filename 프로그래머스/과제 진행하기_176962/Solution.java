import java.util.*;

class Solution {
    public class Homework{
        String name;
        int start;
        int playTime;
        
        public Homework(String name, int start, int playTime){
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }
        
        public String toString(){
            return "[name : " + name + ", start : " + start + ", playTime : " + playTime + "]";
        }
    }
    public String[] solution(String[][] plans) {
        
        List<Homework> homeworks = new ArrayList<>();
        for(String[] plan : plans){
            int[] timeInfo = Arrays.stream(plan[1].split(":")).mapToInt(Integer::parseInt).toArray();
            homeworks.add(new Homework(plan[0], timeInfo[0] * 60 + timeInfo[1], Integer.parseInt(plan[2])));
        }
        
        homeworks.sort((a,b) -> {
            return a.start - b.start;
        });
        
        int index = 0;
        
        String[] answer = new String[plans.length];
        Stack<Homework> waitHomeworks = new Stack<>();
        for(Homework current : homeworks){
            if(waitHomeworks.isEmpty()){
                waitHomeworks.add(current);
                continue;
            }
            if(waitHomeworks.peek().start == current.start + current.playTime){
                answer[index++] = current.name;
                continue;
            }
            
            Homework before = waitHomeworks.pop();
            int timeDiff = current.start - before.start;
            while(true){
                timeDiff = timeDiff - before.playTime;
                
                if(timeDiff >= 0){
                    answer[index++] = before.name;
                    if(waitHomeworks.isEmpty()){
                        break;
                    }
                    before = waitHomeworks.pop();
                    continue;
                }
                before.playTime = -timeDiff;
                waitHomeworks.add(before);
                break;
            }
            waitHomeworks.add(current);
        }
        while(!waitHomeworks.isEmpty()){
            answer[index++] = waitHomeworks.pop().name;
        }
        return answer;
    }
}