import java.util.*;
class Solution {
    static class Node{
        int number;
        boolean sheep;
        
        public Node(int number, int sheep) {
            this.number = number;
            this.sheep = sheep == 0;
        }
    }
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        
        List<Node> nodes = new ArrayList<>();
        for(int index = 0; index < info.length; index++) {
            nodes.add(new Node(index, info[index]));
        }
        
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);
        }
        
        return dfs(graph, nodes, new ArrayList<>(List.of(0)), 0, 0);
    }
    
    public int dfs(Map<Integer, List<Integer>> graph, List<Node> nodes, List<Integer> next, int curSheep, int curWolf) {
        int max = curSheep;

        for (int index = 0; index < next.size(); index++) {
            int current = next.get(index);
            Node node = nodes.get(current);

            int nextSheep = curSheep + (node.sheep ? 1 : 0);
            int nextWolf = curWolf + (node.sheep ? 0 : 1);

            if (nextSheep <= nextWolf) {
                continue;
            }

            List<Integer> nextAvailable = new ArrayList<>(next);
            nextAvailable.remove(index);
            nextAvailable.addAll(graph.getOrDefault(current, new ArrayList()));

            max = Math.max(max, dfs(graph, nodes, nextAvailable, nextSheep, nextWolf));
        }

        return max;
    }
}