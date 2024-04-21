import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        String[][] parsedFiles = new String[files.length][3];
        String[] answer = new String[files.length];
        
        Arrays.sort(files, (A,B) ->{
            String[] a = A.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            String[] b = B.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            
            if(a[0].toLowerCase().equals(b[0].toLowerCase())){
                return Integer.valueOf(a[1].trim()) - Integer.valueOf(b[1].trim());
            }
            return a[0].toLowerCase().compareTo(b[0].toLowerCase());
        });
        return files;
    }
}