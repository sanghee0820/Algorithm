import java.util.*;

class Solution {
    public List<String> subString(String str){
        str = str.toLowerCase();
        
        List<String> subStr = new ArrayList<>();
        for(int strIdx = 0; strIdx < str.length() - 1; strIdx++){
            if( ('a' > str.charAt(strIdx)) || ('z' < str.charAt(strIdx)) 
                 || ( ('a' > str.charAt(strIdx + 1)) || ('z' < str.charAt(strIdx + 1)) ) ) {
                continue;
            }
            subStr.add(str.substring(strIdx, strIdx + 2));
        }
        
        return subStr;
    }
    
    public float jarCard(List<String> subStrs1, List<String> subStrs2){
        
        if(subStrs2.size() == 0 && subStrs1.size() == 0) return 1;
        if(subStrs1.size() == 0 ) return 0;
        if(subStrs2.size() == 0 ) return 0;
        
        int intersection = 0;
        int union = subStrs1.size() + subStrs2.size();
        
        HashMap<String, Integer> str1Map= this.toHash(subStrs1);
        HashMap<String, Integer> str2Map= this.toHash(subStrs2);
        
        
        for(String key : str1Map.keySet()){
            if(str2Map.containsKey(key)){
                intersection += Math.min(str1Map.get(key), str2Map.get(key));
            }
        }
        
        union -= intersection;
        return intersection / (float) union;
            
    }
    
    public HashMap<String, Integer> toHash(List<String> subStrs){
        HashMap<String, Integer> strMap= new HashMap<>();
        
        for(String subStr: subStrs) {
            
            if(strMap.containsKey(subStr)){
                strMap.put(subStr, strMap.get(subStr) + 1);
                continue;
            }
            strMap.put(subStr, 1);
        }
        
        return strMap;
    }
    
    public int solution(String str1, String str2) {
        List<String> subStr1 = subString(str1);
        List<String> subStr2 = subString(str2);

        float jarCardResult = jarCard(subStr1, subStr2);
        float result =  jarCardResult * 65536;
        return (int)result;
    }
}