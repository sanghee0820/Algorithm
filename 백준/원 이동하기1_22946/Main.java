import java.util.*;
import java.io.*;

public class Main {
    public static class Circle{
        final int x;
        final int y;
        final int r;
        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }


    private static final List<Integer> depths = new ArrayList<>();
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<Integer, List<Integer>> tree = new HashMap<>();
        List<Circle> circles = new ArrayList<>();
        tree.put(0,new ArrayList<>());
        for(int i = 1; i <= n; i++){
            String[] data = br.readLine().split(" ");
            circles.add(new Circle(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
            tree.put(i,new ArrayList<>());
        }
        //트리 만들기
        circles.sort(Comparator.comparingInt(a -> -a.r));
        for(int i = 0; i < circles.size(); i++){
            int cur = 0;
            while(true){
                List<Integer> child = tree.get(cur);
                if(child.isEmpty()){
                    child.add(i + 1);
                    break;
                }

                boolean checked = false;
                for(Integer node : child){
                    if(compareCircle(circles.get(node - 1), circles.get(i))){
                        cur = node;
                        checked = true;
                        break;
                    }
                }
                if(!checked){
                    child.add(i + 1);
                    break;
                }
            }
        }

        for(int i = 0; i < tree.get(0).size(); i++){
            depths.add(0);
            DFS(tree, i, tree.get(0).get(i), 1);
        }
        depths.sort(Comparator.reverseOrder());
        if(depths.size() == 1){
            System.out.println(depths.get(0));
        }else{
            System.out.println(depths.get(0) + depths.get(1));
        }
    }

    public static void DFS(Map<Integer, List<Integer>> tree, int parent, int cur, int depth){
        if(tree.get(cur).isEmpty()){
            depths.set(parent, Math.max(depths.get(parent), depth));
            return;
        }

        for(Integer child : tree.get(cur)){
            DFS(tree, parent, child, depth + 1);
        }
    }
    // return 1 -> 안에 있음. return 0 -> 밖에 있음
    public static boolean compareCircle(Circle a, Circle b){
        if(a.r == b.r){
            return false;
        }
        double distance = Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
        return !(a.r + b.r < distance);
    }
    public static void main(String[] args) throws IOException {
        solution();
    }
}