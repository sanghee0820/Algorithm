import java.io.*;
import java.util.Stack;

public class Main {

    private enum Direction {
        NORTH(new int[]{-1, 0}, new int[]{1, 0}, 0),
        EAST(new int[]{0, 1}, new int[]{0, -1}, 1),
        SOUTH(new int[]{1, 0}, new int[]{-1, 0}, 2),
        WEST(new int[]{0, -1}, new int[]{0, 1}, 3);

        final int[] go;
        final int[] back;
        final int index;

        Direction(int[] go, int[] back, int index) {
            this.go = go;
            this.back = back;
            this.index = index;
        }

        public static Direction getValue(int index){
            return Direction.values()[index];
        }

        public Direction rotate(int cnt){
            return Direction.values()[(3 + this.index - cnt) % 4];
        }
    }

    public static class Position{
        int x, y;
        Direction direction;

        public Position(int y, int x, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    '}';
        }
    }
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input;
        input = br.readLine().split(" ");
        int n = Integer.parseInt(input[0]), m = Integer.parseInt(input[1]);

        int[][] map = new int[n][m];

        Stack<Position> positions = new Stack<>();
        input = br.readLine().split(" ");
        positions.add(new Position(Integer.parseInt(input[0]), Integer.parseInt(input[1]),
                Direction.getValue(Integer.parseInt(input[2]))));

        for(int i = 0; i < n; i++){
            input = br.readLine().split(" ");
            for(int j = 0; j < m; j++){
                map[i][j] = Integer.parseInt(input[j]);
            }
        }

        int answer = 0;
        while(true){
            Position cur = positions.peek();
            if(map[cur.y][cur.x] == 0){
                map[cur.y][cur.x] = 2;
                answer++;
                continue;
            }
            boolean dirty = false;
            for(int i = 0; i < 4; i++){
                Direction next = cur.direction.rotate(i);
                int nextX = cur.x + next.go[1];
                int nextY = cur.y + next.go[0];
                if(nextX < 0 || nextX >= m){
                    continue;
                }if(nextY < 0 || nextY >= n){
                    continue;
                }
                if(map[nextY][nextX] == 0){
                    positions.add(new Position(nextY, nextX, next));
                    dirty = true;
                    break;
                }
            }
            if(dirty){
                continue;
            }
            int nextX = cur.x + cur.direction.back[1];
            int nextY = cur.y + cur.direction.back[0];
            if(nextX < 0 || nextX >= m){
                break;
            }if(nextY < 0 || nextY >= n){
                break;
            }
            if(map[nextY][nextX] != 1){
                positions.add(new Position(nextY, nextX, cur.direction));
                continue;
            }
            break;
        }
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        solution();
    }
}