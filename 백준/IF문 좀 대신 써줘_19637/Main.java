import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Main {
    public static List<String> solution(String[][] titleInfo, int[] combatPowers) {
        Map<Integer, String> titleMap = new HashMap<>();
        List<Integer> combatList = new ArrayList<>();
        for (String[] title : titleInfo) {
            var combat = Integer.parseInt(title[1]);
            if (titleMap.containsKey(combat)) {
                continue;
            }
            titleMap.put(combat, title[0]);
            combatList.add(combat);
        }
        Collections.sort(combatList);
        List<String> answer = new ArrayList<>();
        for (int combatPower : combatPowers) {
            var index = Collections.binarySearch(combatList, combatPower);
            if (index < 0) {
                index = Math.abs(index) - 1;
            }
            
            var title = titleMap.get(combatList.get(index));
            answer.add(title);
        }

        return answer;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] data = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[][] titleInfo = new String[data[0]][2];
        for (int index = 0; index < data[0]; index++) {
            titleInfo[index] = br.readLine().split(" ");
        }
        int[] combatPower = new int[data[1]];
        for (int index = 0; index < data[1]; index++) {
            combatPower[index] = Integer.parseInt(br.readLine());
        }

        var answer = solution(titleInfo, combatPower);
        for (String title : answer) {
            bw.write(title + "\n");
        }
        bw.close();
        br.close();
    }
}