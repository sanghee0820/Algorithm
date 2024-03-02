import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void solution() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int caseCnt = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < caseCnt;i++){

            int studentCnt = Integer.parseInt(bufferedReader.readLine());
            List<Integer> students = new ArrayList<>();
            Set<Integer> checked = new HashSet<>();
            Set<Integer> error = new HashSet<>();

            for(String data : bufferedReader.readLine().split(" ")){
                students.add(Integer.parseInt(data));
            }

            for(int j = 1; j < studentCnt + 1; j++){
                if(checked.contains(j)){
                    continue;
                }
                checkTeam(students, j, checked, error);
            }
            System.out.println(error.size());
        }

    }

    public static void checkTeam( List<Integer> students , int startIndex, Set<Integer> globalChecked, Set<Integer> globalError){
        Set<Integer> checked = new HashSet<>();
        checked.add(startIndex);
        int cur = students.get(startIndex - 1);;
        while(cur != startIndex){
            if(globalError.contains(cur) || globalChecked.contains(cur) || checked.contains(cur)){
                globalError.add(startIndex);
                return;
            }
            if(students.get(cur - 1) == cur){
                globalError.addAll(checked);
                return;
            }
            checked.add(cur);
            cur = students.get(cur - 1);
        }
        globalChecked.addAll(checked);
    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
