import java.util.*;

class Solution {

    static class Seller {
        String name;
        int cost;

        public Seller(String name) {
            this.name = name;
            this.cost = 0;
        }
    }

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, String> parentMap = new HashMap<>();
        Map<String, Seller> sellerMap = new HashMap<>();

        sellerMap.put("-", new Seller("-"));

        for (String name : enroll) {
            sellerMap.put(name, new Seller(name));
        }

        for (int index = 0; index < enroll.length; index++) {
            parentMap.put(enroll[index], referral[index]);
        }

        for (int index = 0; index < seller.length; index++) {
            String cur = seller[index];
            int cost = amount[index] * 100;
            dfs(cur, cost, parentMap, sellerMap);
        }

        return Arrays.stream(enroll)
                .mapToInt(name -> sellerMap.get(name).cost)
                .toArray();
    }

    private void dfs(String seller, int cost, Map<String, String> parentMap, Map<String, Seller> sellerMap) {
        if (seller.equals("-") || cost == 0) {
            return;
        }

        int toParent = cost / 10;
        int remain = cost - toParent;

        sellerMap.get(seller).cost += remain;

        dfs(parentMap.get(seller), toParent, parentMap, sellerMap);
    }
}