import java.util.*;
import java.util.stream.*;

class Pair{
    int destination;
    int time;
    
    public Pair(int destination, int time){
        this.destination = destination;
        this.time = time;
    }
    
    public String toString(){
        return "Destination : " + destination + " time : " + time;
    }
}

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        boolean[] visited = new boolean[N + 1];
        int[] times = new int[N + 1];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[1] = 0;
        
        var tree = makeTree(N, road);
        dijkstra(N, tree, times, visited);
        
        answer = (int) Arrays.stream(times)
            .filter(i -> i <= K)
            .count();
        
        return answer;
    }
    
    public Map<Integer, List<Pair>> makeTree(int n, int[][] road){
        Map<Integer, List<Pair>> tree = new HashMap<>();
        
        for(int i = 1; i <= n; i++){
            tree.put(i, new ArrayList<>());
        }
        
        for(int[] path : road){
            tree.get(path[0]).add(new Pair(path[1], path[2]));
            tree.get(path[1]).add(new Pair(path[0], path[2]));
        }
        return tree;
    }
    
    public void dijkstra(int N, Map<Integer, List<Pair>> tree, int[] times, boolean[] visited){
        int current = 1;
        int totalVisited = 0;
        
        while(totalVisited < N){
            totalVisited++;
            visited[current] = true;
            var nexts = tree.get(current);
            for(Pair next : nexts){
                if(visited[next.destination]){
                    continue;
                }
                times[next.destination] = Math.min(times[next.destination], times[current] + next.time);
            }
            
            Integer minValue = Integer.MAX_VALUE;
            for(int i = 1; i <= N; i++){
                if(visited[i]){
                    continue;
                }
                if(times[i] < minValue){
                    minValue = times[i];
                    current = i;
                }
            }
        }
    }
}