class Solution {
    public int DFS(int[] numbers, int aryLength, int position, int target, int calculatedValue, int count ){
        
        if(aryLength  == position){
            if(target == calculatedValue){
                return count + 1;
            }
            return count;
        }
        count = DFS(numbers, aryLength, position + 1, target, calculatedValue + numbers[position], count);
        count = DFS(numbers, aryLength, position + 1, target, calculatedValue - numbers[position], count);
        
        return count;
    } 
    public int solution(int[] numbers, int target) {
        int answer = DFS(numbers, numbers.length, 0, target, 0, 0);
        return answer;
    }
}