import java.util.*;

class Position{
    int row;
    int column;
    
    public Position(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if(Objects.isNull(o) || this.getClass() != o.getClass()) return false;
        Position position = (Position) o;
        
        return this.row == position.row && this.column == position.column;
    }
    
    public int hashCode(){
        return Objects.hash(row, column);
    }
}

class Solution {
    private int answer = 0;
    public int solution(int n) {
        findQueenPosition(new ArrayList<>(), 0, n);
        return answer;
    }
    
    public void findQueenPosition(List<Position> containsQueen, int row, int n){
        if(containsQueen.size() == n){
            answer++;
            return;
        }
        
        for(int column = 0; column < n; column ++){
            var nextQueen = new Position(row, column);
            
            if(isInvalidPosition(containsQueen, nextQueen)){
                continue;
            }
            
            containsQueen.add(nextQueen);
            findQueenPosition(containsQueen, row + 1, n);
            containsQueen.remove(row);
        }
    }
    
    public boolean isInvalidPosition(List<Position> containsQueen, Position currentQueen){
        for(Position contains : containsQueen){
            if(contains.column == currentQueen.column){
                return true;
            }
            if(Math.abs(contains.column - currentQueen.column) == Math.abs(contains.row - currentQueen.row)){
                return true;
            }
        }
        return false;
    }
}