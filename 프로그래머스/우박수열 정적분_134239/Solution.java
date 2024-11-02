import java.util.*;

class Solution {
    private static final Integer TARGET = 1;
    public double[] solution(int k, int[][] ranges) {
        List<Double> prefixSum = new ArrayList<>(List.of(0.0));
        while(k != TARGET){
            int next = k % 2 == 0 ? k / 2 : k * 3 + 1;
            double area = (next + k) / 2.0;
            prefixSum.add(prefixSum.get(prefixSum.size() - 1) + area);
            k = next;
        }
        
        
        List<Double> answerList = new ArrayList<>();
        int prefixSumLength = prefixSum.size() - 1;

        for (var range : ranges) {
            int left = range[0];
            int right = prefixSumLength + range[1];

            if (left > right) {
                answerList.add(-1.0);
                continue;
            }

            double area = prefixSum.get(right) - prefixSum.get(left);
            answerList.add(area);
        }

        double[] answer = answerList.stream().mapToDouble(Double::doubleValue).toArray();
        return answer;
    }
}