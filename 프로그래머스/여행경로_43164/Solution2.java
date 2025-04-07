import java.util.*;

class Solution2 {
    static class Ticket{
        String from;
        String to;
        int index;
        
        public Ticket(String from, String to, int index) {
            this.from = from;
            this.to = to;
            this.index = index;
        }
    }
    
    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> ticketMap = new HashMap<>();
        for(int index = 0; index < tickets.length; index++) {
            var ticket = tickets[index];
            ticketMap.putIfAbsent(ticket[0], new ArrayList<>());
            ticketMap.get(ticket[0]).add(new Ticket(ticket[0], ticket[1], index));
        }
        
        List<String> answer = new ArrayList<>();
        dfs(ticketMap, new boolean[tickets.length], 0, "ICN", "ICN", answer);
        Collections.sort(answer);
        
        return answer.get(0).split(" ");
    }
    
    public void dfs(Map<String, List<Ticket>> tickets, boolean[] visited, int cnt, String cur, String before, List<String> answer) {
        if(cnt == visited.length) {
            answer.add(cur);
            return;
        }
        
        for(Ticket ticket : tickets.getOrDefault(before, new ArrayList<>())) {
            if(visited[ticket.index]) {
                continue;
            }
            
            var next = cur + " " + ticket.to;
            visited[ticket.index] = true;
            dfs(tickets, visited, cnt + 1, next, ticket.to, answer);
            visited[ticket.index] = false;
        }

    }
}