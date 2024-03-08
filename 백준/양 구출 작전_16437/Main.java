import java.util.*;
import java.io.*;
public class Main {
    public static class Land{
        List<Integer> child = new ArrayList<>();
        TYPE type;
        long cnt;

        public void setType(String type, String cnt){
            if(type.equals("W")){
                this.type = TYPE.WOLF;
            }else{
                this.type = TYPE.SHEEP;
            }
            this.cnt = Long.parseLong(cnt);
        }

    }
    public enum TYPE{
        ROOT, SHEEP, WOLF
    }
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int landCnt = Integer.parseInt(br.readLine());

        List<Land> landInfo = new ArrayList<>();
        for(int i = 0; i < landCnt; i++){
            landInfo.add(new Land());
        }
        landInfo.get(0).type = TYPE.ROOT;

        for(int i = 1; i < landCnt; i++){
            String[] landData = br.readLine().split(" ");
            landInfo.get(i).setType(landData[0], landData[1]);
            landInfo.get(Integer.parseInt(landData[2]) - 1).child.add(i);
        }

        System.out.println(DFS(landInfo,0));
    }

    public static long DFS(List<Land> landInfo, int current){
        long total = 0;
        for(int next : landInfo.get(current).child){
            total += DFS(landInfo, next);
        }
        if(landInfo.get(current).type == TYPE.WOLF){
            total -= landInfo.get(current).cnt;
            if(total < 0){
                return 0;
            }
        }else{
            total += landInfo.get(current).cnt;
        }
        return Math.abs(total);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}
