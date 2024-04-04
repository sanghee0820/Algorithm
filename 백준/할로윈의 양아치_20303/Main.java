//75208KB 1888ms
import java.io.*;
import java.util.*;

public class Main {

    public static class GroupInfo{
        int cnt;
        int candy;

        public GroupInfo(int cnt, int candy) {
            this.cnt = cnt;
            this.candy = candy;
        }

    }

    private static int[] friends;
    private static int[] candies;
    private static GroupInfo[] groupInfos;
    static Set<Integer> groups = new HashSet<>();

    public static int find(int n) {
        ArrayList<Integer> path = new ArrayList<>();
        while(friends[n] != n){
            path.add(n);
            n = friends[n];
        }
        for(int node : path){
            friends[node] = n;
        }

        return n;
    }


    public static void union(int a, int b){
        int aParent = find(a);
        int bParent = find(b);
        friends[aParent] = bParent;
    }
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] data = br.readLine().split(" ");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int k = Integer.parseInt(data[2]);

        friends = new int[n + 1];
        candies = new int[n + 1];
        
        data = br.readLine().split(" ");
        for(int i = 1; i < candies.length; i++){
            friends[i] = i;
            candies[i] = Integer.parseInt(data[i - 1]);
        }

        for(int i = 0; i < m; i++){
            data = br.readLine().split(" ");
            union(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
        }
        for(int i = 0; i < friends.length; i++){
            groups.add(find(i));
        }

        groups.remove(0);
        groupInfos = new GroupInfo[groups.size()];
        int groupIndex = 0;
        for(int group: groups){
            groupInfos[groupIndex] = new GroupInfo(0, 0);
            for(int friend = 1; friend < friends.length; friend++){
                if(friends[friend] == group){
                    groupInfos[groupIndex].candy += candies[friend];
                    groupInfos[groupIndex].cnt++;
                }
            }
            groupIndex++;
        }

//        int[][] dp = new int[groupInfos.length + 1][n];
//        int maxCandies = 0;
//        for(int i = 1; i < dp.length; i++){
//            for(int j = 0; j < n; j++){
//                if(groupInfos[i - 1].cnt <= j){
//                    dp[i][j] = Math.max(dp[i - 1][j - groupInfos[i - 1].cnt] + groupInfos[i - 1].candy, dp[i-1][j]);
//                }else{
//                    dp[i][j] = dp[i-1][j];
//                }
//                if(j < k){
//                    maxCandies = Math.max(maxCandies, dp[i][j]);
//                }
//            }
//
//        }

        int[] dp = new int[k];

        for(GroupInfo groupInfo : groupInfos){
            for(int i = k - 1; i >= groupInfo.cnt; i--){
                dp[i] = Math.max(dp[i], dp[i - groupInfo.cnt] + groupInfo.candy);
            }
        }

        System.out.println(dp[k - 1]);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
