import java.util.*;

class Solution {
    public int solution(int n, int[] weak, int[] dist) {
        int[] extended = new int[weak.length * 2];

        for (int index = 0; index < weak.length; index++) {
            extended[index] = weak[index];
            extended[index + weak.length] = weak[index] + n;
        }

        List<List<Integer>> permutations = new ArrayList<>();
        getPermutations(dist, new ArrayList<>(), new boolean[dist.length], permutations);

        int min = Integer.MAX_VALUE;
        for (List<Integer> perm : permutations) {
            for (int start = 0; start < weak.length; start++) {
                int count = 1;
                int position = extended[start] + perm.get(0);

                for (int index = start + 1; index < start + weak.length; index++) {
                    if (extended[index] > position) {
                        count++;
                        if (count > perm.size()) {
                            break;
                        }
                        position = extended[index] + perm.get(count - 1);
                    }
                }

                if (count <= dist.length) {
                    min = Math.min(min, count);
                }
            }
        }
        
        if(min == Integer.MAX_VALUE) {
            min = -1;
        }
        return min;
    }

    private void getPermutations(int[] dist, List<Integer> current, boolean[] visited, List<List<Integer>> result) {
        if (current.size() == dist.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int index = 0; index < dist.length; index++) {
            if (visited[index]) {
                continue;
            }
            visited[index] = true;
            current.add(dist[index]);
            getPermutations(dist, current, visited, result);
            current.remove(current.size() - 1);
            visited[index] = false;
        }
    }
}