import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        String music = "";
        int maxLength = 0;
        for(int i = 0; i < m.length(); i++){
            if(i + 1 < m.length() && m.charAt(i + 1) == '#'){
                music += String.valueOf(m.charAt(i)).toLowerCase();
                i++;
                continue;
            }
            music += String.valueOf(m.charAt(i));
        }
        for(String musicInfo : musicinfos){
            String[] splitedMusicInfo = musicInfo.split(",");
            int startTime = Integer.valueOf(splitedMusicInfo[0].split(":")[0]) * 60 
                + Integer.valueOf(splitedMusicInfo[0].split(":")[1]);
            int endTime = Integer.valueOf(splitedMusicInfo[1].split(":")[0]) * 60 
                + Integer.valueOf(splitedMusicInfo[1].split(":")[1]);
            
            String musicPattern = "";
            for(int i = 0, index = 0; i < endTime - startTime; i++){
                if(splitedMusicInfo[3].charAt((index + 1) % splitedMusicInfo[3].length()) == '#'){
                    musicPattern += String.valueOf(
                        splitedMusicInfo[3].charAt((index++) % splitedMusicInfo[3].length())).toLowerCase();
                    index++;
                    continue;
                }
                musicPattern += String.valueOf(splitedMusicInfo[3].charAt(index++ % splitedMusicInfo[3].length()));
            }
            if(musicPattern.contains(music)){
                if(maxLength < endTime - startTime){
                    answer = splitedMusicInfo[2];
                    maxLength = endTime - startTime;
                }
            }
        }
        if(answer.length() == 0){
            answer += "(None)";
        }
        return answer;
    }
}