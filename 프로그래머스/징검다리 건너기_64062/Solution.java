class Solution {
    public int solution(int[] stones, int k) {
        int left = 1;
        int right = 200_000_000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canCross(stones, k, mid)) {
                answer = mid;
                left = mid + 1;
                continue;
            }
            right = mid - 1;
        }

        return answer;
    }

    private boolean canCross(int[] stones, int k, int friends) {
        int count = 0;

        for (int stone : stones) {
            if (stone - friends < 0) {
                count++;
                if (count >= k) {
                    return false;
                }
                continue;
            }
            count = 0;
        }

        return true;
    }
}