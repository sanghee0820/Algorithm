import java.util.*;

class Subject{
    String name;
    int startTime;
    int leftTime;
    
    public Subject(String name, String startTime, String time) {
        this.name = name;
        this.startTime = toTime(startTime);
        this.leftTime = Integer.parseInt(time);
    }
    
    private int toTime(String startTime) {
        var timeInfo = startTime.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }
    
    public String toString() {
        return String.format("%s : [%d]", name, leftTime);
    }
}
class Solution2 {
        public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Arrays.sort(plans, (a,b) -> {
            return toTime(a[1]) - toTime(b[1]);
        });
        Stack<Subject> subjects = new Stack<>();
        
        Subject curSubject = new Subject(plans[0][0], plans[0][1], plans[0][2]);
        int planIndex = 1;
        int time = curSubject.startTime;
        
        while (true) {
            if (planIndex == plans.length && subjects.isEmpty() && curSubject == null) {
                break;
            }
            
            if (curSubject != null && curSubject.leftTime == 0) {
                answer.add(curSubject.name);
                
                if (!subjects.isEmpty()) {
                    curSubject = subjects.pop();
                } else {
                    curSubject = null;
                }
            }
            
            if (planIndex < plans.length && time == toTime(plans[planIndex][1])) {
                if (curSubject != null) {
                    subjects.push(curSubject);
                }
                
                curSubject = new Subject(plans[planIndex][0], plans[planIndex][1], plans[planIndex][2]);
                planIndex++;
            }
            
            if (curSubject != null) {
                curSubject.leftTime--;
            }
            
            time++;
        }
        
        return answer.toArray(new String[0]);
    }
    
    public int toTime(String startTime) {
        var timeInfo = startTime.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }
}