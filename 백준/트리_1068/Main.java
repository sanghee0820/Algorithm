import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Main {

    public static void solution1() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int treeSize = Integer.parseInt(bufferedReader.readLine());
        int[] tree = new int[treeSize];
        boolean[] deleted = new boolean[treeSize];

        // 트리 입력 받기
        String treeData = bufferedReader.readLine();
        String[] data = treeData.split(" ");
        for(int i = 0; i < treeSize; i++){
           tree[i] = Integer.parseInt(data[i]);
        }

        // 트리 지우기
        int firstDeleted = Integer.parseInt(bufferedReader.readLine());
        deleted[firstDeleted] = true;
        int deletedCnt = 1;
        for(int i = 0; i < treeSize; i++){
            if(tree[i] == -1){
                continue;
            }
            int cur = tree[i];
            //위의 노드중 삭제된것이 있는지 확인
            // 바로위노드만 확인했다가 시간잡아먹음.
            while(cur != -1){
                if(deleted[cur]){
                    deleted[i] = true;
                    deletedCnt++;
                    break;
                }
                cur = tree[cur];
            }

        }

        // 자식이 있는 노드 확인.
        Set<Integer> parents = new HashSet<>();
        for(int i = 0; i < treeSize; i++){
            if(deleted[i] || tree[i] == -1){
                continue;
            }
            parents.add(tree[i]);
        }

        System.out.println(treeSize - deletedCnt - parents.size());
    }

    public static void solution2() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int treeSize = Integer.parseInt(bufferedReader.readLine());
        List<List<Integer>> tree = new ArrayList<>();
        for(int i = 0; i < treeSize; i++){
            tree.add(i, new ArrayList<>());
        }
        String treeData = bufferedReader.readLine();
        String[] data = treeData.split(" ");
        for(int i = 0; i < treeSize; i++){
            if(Integer.parseInt(data[i]) == -1 ){
                continue;
            }
            tree.get(Integer.parseInt(data[i])).add(i);
        }

        int firstDeleted = Integer.parseInt(bufferedReader.readLine());
        deleteNode(tree, firstDeleted);

        int leaf = 0;
        for(int i = 0; i < treeSize; i++){
            List<Integer> cur = tree.get(i);
            if(cur == null){
                continue;
            }
            if(cur.isEmpty()){
                leaf++;
            }
            if(cur.size() == 1 && cur.get(0) == firstDeleted){
                leaf++;
            }
        }

        System.out.println(leaf);
    }

    public static void deleteNode(List<List<Integer>> tree, int deleteNode){
        Queue<Integer> queue = new LinkedList<>(tree.get(deleteNode));
        tree.set(deleteNode, null);
        while(!queue.isEmpty()){
            List<Integer> next = tree.get(queue.peek());
            tree.set(queue.remove(), null);
            queue.addAll(next);
        }
    }
    public static void main(String[] args) throws IOException {
        solution2();
    }

}
