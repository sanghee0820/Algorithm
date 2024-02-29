import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Main {

    public static class Position{
        int position;
        int count;

        public Position(int position, int count){
            this.position = position;
            this.count = count;
        }

        public void setPosition(int position){
            this.position = position;
        }
    }
    public static void solution() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, Integer> snake = new HashMap<>();
        Map<Integer, Integer> ladder = new HashMap<>();

        int snakeAndLadder = 0;
        for(String data : bufferedReader.readLine().split(" ")){
            snakeAndLadder += Integer.parseInt(data);
        }
        for(int i = 0; i < snakeAndLadder; i++){
            String data = bufferedReader.readLine();
            String[] parsedData = data.split(" ");
            if(Integer.parseInt(parsedData[0]) < Integer.parseInt(parsedData[1])){
                ladder.put(Integer.parseInt(parsedData[0]),  Integer.parseInt(parsedData[1]));
                continue;
            }
            snake.put(Integer.parseInt(parsedData[0]),  Integer.parseInt(parsedData[1]));
        }

        BFS(ladder, snake);
    }

    public static void BFS(Map<Integer,Integer> ladder, Map<Integer,Integer> snake){
        Position[] visited = new Position[101];
        Queue<Position> queue = new LinkedList<>();
        queue.add(new Position(1, 0));

        while(!queue.isEmpty()){
            Position cur = queue.remove();
            if(cur.position == 100){
                System.out.println(cur.count);
                break;
            }
            for(int i = 1; i < 7; i++){
                Position next = new Position(cur.position + i, cur.count + 1);
                if(next.position > 100){
                    continue;
                }
                if(ladder.containsKey(next.position)){
                    next.setPosition(ladder.get(next.position));
                }
                if(snake.containsKey(next.position)){
                    next.setPosition(snake.get(next.position));
                }
                if(visited[next.position] == null){
                    queue.add(next);
                    visited[next.position] = next;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        solution();
    }

}
