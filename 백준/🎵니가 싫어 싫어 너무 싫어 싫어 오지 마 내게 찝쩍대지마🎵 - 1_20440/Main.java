import java.util.*;
import java.io.*;
public class Main {
    public static class Mosquito {
        int start;
        int end;

        @Override
        public String toString() {
            return "Mosquito{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }

        public Mosquito(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<Mosquito> mosquitoes = new ArrayList<>();
        Set<Integer> treeSet = new TreeSet<>();
        for(int i = 0; i < n; i++){
            int[] data = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            mosquitoes.add(new Mosquito(data[0], data[1]));
            treeSet.add(data[0]);
            treeSet.add(data[1]);
        }

        List<Integer> sortedList = new ArrayList<>(treeSet);
        int[] cnt = new int[sortedList.size()];
        for(Mosquito mosquito : mosquitoes){
            int start = Collections.binarySearch(sortedList, mosquito.start);
            int end = Collections.binarySearch(sortedList, mosquito.end);
            for(int i = start ; i < end; i++){
                cnt[i]++;
            }
        }
        int maxCnt = 0;
        int[] range = new int[]{sortedList.get(1), sortedList.get(1)};
        for(int i = 0; i < cnt.length; i++){
            if(maxCnt < cnt[i]){
                range[0] = sortedList.get(i);
                range[1] = sortedList.get(i + 1);
                maxCnt = cnt[i];
            }
            if(maxCnt == cnt[i] && sortedList.get(i) == range[1]){
                range[1] = sortedList.get(i + 1);
            }
        }
        System.out.println(maxCnt);
        System.out.println(range[0] + " " + range[1]);
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}
