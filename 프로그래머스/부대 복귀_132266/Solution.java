import java.util.*;

class Position{
    int num;
    int cnt;
    public Position(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }
}

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        int[] distance = new int[n];
        Arrays.fill(distance, Integer.MAX_VALUE);
        Map<Integer, List<Integer>> tree = new HashMap<>();
        for(int[] road : roads){
            tree.putIfAbsent(road[0], new ArrayList<>());
            tree.putIfAbsent(road[1], new ArrayList<>());
            tree.get(road[0]).add(road[1]);
            tree.get(road[1]).add(road[0]);
        }
        
        bfs(distance, tree, destination);
        
        for(int i = 0; i < sources.length; i++){
            if(distance[sources[i] - 1] == Integer.MAX_VALUE){
                answer[i] = -1;
                continue;
            }
            answer[i] = distance[sources[i] - 1];
        }
        return answer;
    }
    
    public void bfs(int[] distance, Map<Integer, List<Integer>> tree, int startPosition){
        distance[startPosition - 1] = 0;
        
        Queue<Position> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        tree.get(startPosition).stream().forEach( i -> {
           queue.add(new Position(i, 1)); 
        });
        visited.add(startPosition);
        while(!queue.isEmpty()){
            var current = queue.poll();
            if(visited.contains(current.num)){
                continue;
            }
            visited.add(current.num);
            distance[current.num - 1] = current.cnt;
            
            tree.get(current.num).stream().forEach( i -> {
                queue.add(new Position(i, current.cnt + 1)); 
            });
        }
    }
}