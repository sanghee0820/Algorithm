import java.util.*;
// 도넛 -> n개의 간선 / 싸이클
// 막대 -> n-1개의 간선 / 모든 간선 순회 / 돌아오지않음
// 8자 -> 2n + 1개의 정점 /2n + 2개의 간선
// 추가한 정점은 나가는 정점밖에 없음.
// 그래프 만들고 끝까지 탐색
class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int[] input = new int[1000000];
        int[] output = new int[1000000];
        List<LinkedList<Integer>> linkedList = new ArrayList<>();
        for (int i = 0; i < output.length; i++) {
            linkedList.add(new LinkedList<>());
        }
        for(int[] edge : edges){
            input[edge[1] - 1]++;
            output[edge[0] - 1]++;
            linkedList.get(edge[0] - 1).add(edge[1] - 1);
        }
        int root = -1;
        for(int i = 0; i < input.length; i++){
            if(input[i] == 0 && output[i] >= 2){
                root = i + 1;
                break;
            }
        }
        answer[0] = root;
        for(Integer graphStart : linkedList.get(root - 1)){
            if(linkedList.get(graphStart).size() == 0){
                answer[2]++;
                continue;
            }
            int cur = graphStart;
            int edge = 0;
            int maxOutDegree = 0;
            Set<Integer> visited = new HashSet<>();
            visited.add(cur);
            while(linkedList.get(cur).size() != 0){
                maxOutDegree = Math.max(maxOutDegree, linkedList.get(cur).size());
                cur = linkedList.get(cur).poll();
                edge++;
                visited.add(cur);
            }
            if(maxOutDegree > 1){
                answer[3]++;
                continue;
            }
            if(edge == visited.size()){
                answer[1]++;
                continue;
            }
            answer[2]++;
        }
        return answer;
    }
}
