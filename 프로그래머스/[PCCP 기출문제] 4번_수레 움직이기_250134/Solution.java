import java.util.*;

class Position{
    int row;
    int column;
    public Position(int i, int j){
        this.row = i;
        this.column = j;
    }

    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && column == position.column;
    }
    
    public int hashCode() {
        return Objects.hash(row, column);
    }
}

class Info{
    Position red;
    Position blue;
    HashSet<Position> redVisited;
    HashSet<Position> blueVisited;
    int move;

    private Info(Position red, Position blue, HashSet<Position> redVisited, HashSet<Position> blueVisited, int move){
        this.red = red;
        this.blue = blue;
        this.redVisited = redVisited;
        this.blueVisited = blueVisited;
        this.move = move;
    }

    public static Info move(Position red, Position blue, HashSet<Position> redVisited, HashSet<Position> blueVisited, int move, boolean redFinal, boolean blueFinal,
                           Position redBefore, Position blueBefore){
        if(redBefore.equals(blue) && blueBefore.equals(red)){
            return null;
        }
        if(red.equals(blue)){
            return null;
        }
        if(redVisited.contains(red) && !redFinal){
            return null;
        }
        if(blueVisited.contains(blue) && !blueFinal){
            return null;
        }

        var nextRedVisited = (HashSet<Position>)redVisited.clone();
        var nextBlueVisited = (HashSet<Position>)blueVisited.clone();
        nextRedVisited.add(red);
        nextBlueVisited.add(blue);

        return new Info(red, blue, nextRedVisited, nextBlueVisited, move + 1);
    }
    
    public static Info init(Position red, Position blue){
        HashSet<Position> redVisited = new HashSet<>();
        HashSet<Position> blueVisited = new HashSet<>();
        redVisited.add(red);
        blueVisited.add(blue);
        return new Info(red, blue, redVisited, blueVisited, 0);
    }
}
class Solution {
    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private static final int RED_START_POINT = 1;
    private static final int BLUE_START_POINT = 2;
    private static final int RED_END_POINT = 3;
    private static final int BLUE_END_POINT = 4;
    private static final int WALL = 5;
    
    public int solution(int[][] maze) {
        int answer = 0;
        Position redStart = null;
        Position blueStart = null;
        for(int i = 0; i < maze.length; i++){
            for(int j = 0; j < maze[0].length; j++){
                if(maze[i][j] == RED_START_POINT){
                    redStart = new Position(i, j);
                    continue;
                }
                
                if(maze[i][j] == BLUE_START_POINT){
                    blueStart = new Position(i, j);
                    continue;
                }
            }
        }
        
        return bfs(redStart, blueStart, maze);
    }
    
    public int bfs(Position redStart, Position blueStart, int[][] maze){
        Queue<Info> queue = new LinkedList<>();
        var init = Info.init(redStart, blueStart);
        queue.add(init);
        
        while(!queue.isEmpty()){
            var current = queue.poll();
            if(maze[current.red.row][current.red.column] == RED_END_POINT &&
              maze[current.blue.row][current.blue.column] == BLUE_END_POINT){
                return current.move;
            }
            for(int[] redNextDirection : DIRECTIONS){
                
                Position redNext = null;
                if(maze[current.red.row][current.red.column] == RED_END_POINT){
                    redNext = current.red;
                }else{
                    var redNextRow = current.red.row + redNextDirection[0];    
                    var redNextColumn = current.red.column + redNextDirection[1];

                    if(redNextRow < 0 || maze.length <= redNextRow ||
                      redNextColumn < 0 || maze[0].length <= redNextColumn){
                        continue;
                    }

                    if (maze[redNextRow][redNextColumn] == WALL) {
                        continue;
                    }
                    redNext = new Position(redNextRow, redNextColumn);
                }
                
                
                for(int[] blueNextDirection : DIRECTIONS){
                    Position blueNext = null;
                    if(maze[current.blue.row][current.blue.column] == BLUE_END_POINT){
                        blueNext = current.blue;
                    }else{
                        var blueNextRow = current.blue.row + blueNextDirection[0];    
                        var blueNextColumn = current.blue.column + blueNextDirection[1];

                        if(blueNextRow < 0 || maze.length <= blueNextRow ||
                          blueNextColumn < 0 || maze[0].length <= blueNextColumn){
                            continue;
                        }

                        if (maze[blueNextRow][blueNextColumn] == WALL) {
                            continue;
                        }
                        blueNext = new Position(blueNextRow, blueNextColumn);
                    }
                    
                    var nextInfo = Info.move(redNext, blueNext, current.redVisited, current.blueVisited, current.move, redNext.equals(current.red), blueNext.equals(current.blue),
                                            current.red, current.blue);
                    if(nextInfo != null){
                        queue.add(nextInfo);
                        continue;
                    }
                    
                }
            }
        }
        return 0;
    }
}