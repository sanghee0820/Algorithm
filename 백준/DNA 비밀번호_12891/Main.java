import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static int solution(int s, int p, String dna, int a, int c, int g, int t){
        int answer = 0;
        Map<Character, Integer> includingMap = new HashMap<>();

        int left = 0;
        for(int right = 0; right < s; right++){
            if(right - left + 1 > p){
                includingMap.put(dna.charAt(left),
                        includingMap.get(dna.charAt(left++)) - 1);
            }
            includingMap.put(dna.charAt(right),
                    includingMap.getOrDefault(dna.charAt(right), 0) + 1);
            if(right - left + 1 == p){
                if(includingMap.getOrDefault('A', 0) < a){
                    continue;
                }if(includingMap.getOrDefault('C', 0) < c){
                    continue;
                }if(includingMap.getOrDefault('G', 0) < g){
                    continue;
                }if(includingMap.getOrDefault('T', 0) < t){
                    continue;
                }
                answer++;
            }
        }

        return answer;
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int s = Integer.parseInt(input[0]);
        int p = Integer.parseInt(input[1]);
        String dna = br.readLine();
        input = br.readLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        int g = Integer.parseInt(input[2]);
        int t = Integer.parseInt(input[3]);
        System.out.println(solution(s, p, dna, a, c, g, t));
    }
}
