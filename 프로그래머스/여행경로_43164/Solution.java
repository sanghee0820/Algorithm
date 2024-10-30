import java.util.*;

class Ticket{
    final Integer id;
    final String from;
    final String to;
    
    public Ticket(Integer id, String from, String to){
        this.id = id;
        this.from = from;
        this.to = to;
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        
        return ticket.id == this.id;
    }
    
    public int hashCode() {
        return Objects.hash(this.id);
    }
}

class Solution {
    public String[] solution(String[][] tickets) {
        Map<String, List<Ticket>> ticketsTree = new HashMap<>();
        var totalTicket = tickets.length;
        for(int id = 0; id < tickets.length; id++){
            var ticket = tickets[id];
            ticketsTree.putIfAbsent(ticket[0], new ArrayList<>());
            ticketsTree.get(ticket[0]).add(new Ticket(id, ticket[0], ticket[1]));
        }
        for(String key : ticketsTree.keySet()){
            var ticketInfo = ticketsTree.get(key);
            Collections.sort(ticketInfo, (a,b) -> {
                return a.to.compareTo(b.to);
            });
        }
        
        var startTicket = new Ticket(-1, null, "ICN");
        var path = dfs(new HashSet<>(), totalTicket, new ArrayList<>(List.of(startTicket)), startTicket, ticketsTree);
        
        var answer = path.stream().map(a -> a.to).toArray(String[]::new);
        
        return answer;
    }
    
    public List<Ticket> dfs(Set<Ticket> visited, int totalTicket, List<Ticket> path, Ticket current, Map<String, List<Ticket>> tickets){
        if(visited.size() == totalTicket){
            return path;
        }
        
        var next = tickets.getOrDefault(current.to, new ArrayList<>());
        for(Ticket ticket : next){
            if(visited.contains(ticket)){
                continue;
            }
            
            visited.add(ticket);
            path.add(ticket);
            var findPath = dfs(visited, totalTicket, path, ticket, tickets);
            if(findPath != null){
                return findPath;
            }
            visited.remove(ticket);
            path.remove(ticket);
        }
        
        return null;
    }
}