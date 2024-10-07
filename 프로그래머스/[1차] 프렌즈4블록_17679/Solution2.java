import java.util.*;

class Block{
    int index;
    String type;
    
    public Block(int index, String type){
        this.index = index;
        this.type = type;
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(!Objects.equals(this.getClass(), o.getClass())) return false;
        
        Block b = (Block) o;
        return b.index == this.index && this.type.equals(b.type);
    }

    public int hashCode(){
        return Objects.hash(index, type);
    }
    
    public String toString(){
        return type;
    }
}
class Solution2 {
    public int solution(int m, int n, String[] board) {
        List<List<Block>> map = new ArrayList<>();
        String[][] splitBoard = new String[m][n];
        for(int row = 0; row < m; row ++){
            String[] rowData = board[row].split("");
            for(int column = 0; column < n; column++){
                splitBoard[row][column] = rowData[column];
            }
        }
        
        for(int column = 0; column < n; column++){
            List<Block> blocks = new ArrayList<>();
            for(int row = m - 1; row >= 0; row --){
                blocks.add(new Block(row * 100 + column, splitBoard[row][column]));
            }
            map.add(blocks);
        }
        
        int answer = 0;
        while(true){
            Set<Block> deletedBlock = new HashSet<>();
            
            for(int column = 0; column < n; column++){
                for(int row = 0; row < map.get(column).size(); row ++){
                    deletedBlock.addAll(findShape(map, row, column));
                }
            }
            
            if(deletedBlock.isEmpty()){
                break;
            }
            
            answer += deletedBlock.size();
            for(int column = 0; column < n; column++){
                map.get(column).removeAll(deletedBlock);
            }
        }
        
        return answer;
    }
    
    public Set<Block> findShape(List<List<Block>> map, int row, int column){
        Set<Block> deletedBlocks = new HashSet<>();
        
        if(checkIndex(map, row, column)){
            return deletedBlocks;
        }
        
        var current = map.get(column).get(row);
        var right = map.get(column + 1).get(row);
        var down = map.get(column).get(row + 1);
        var rightDown = map.get(column + 1).get(row + 1);

        if(equalTypes(current, right, down, rightDown)){
            deletedBlocks.add(current);
            deletedBlocks.add(right);
            deletedBlocks.add(down);
            deletedBlocks.add(rightDown);
        }
        
        return deletedBlocks;
    }
    
    public boolean checkIndex(List<List<Block>> map, int row, int column) {
        boolean hasCurrent = row < map.get(column).size();
        boolean hasRight = column + 1 < map.size() && row < map.get(column + 1).size();
        boolean hasDown = row + 1 < map.get(column).size();
        boolean hasRightDown = column + 1 < map.size() && row + 1 < map.get(column + 1).size();

        return !(hasCurrent && hasRight && hasDown && hasRightDown);
    }
    
    public boolean equalTypes(Block a, Block b, Block c, Block d){
        return a.type.equals(b.type) && a.type.equals(c.type) && a.type.equals(d.type);
    }
}