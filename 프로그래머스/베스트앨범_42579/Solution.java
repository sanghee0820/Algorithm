import java.util.*;

class Solution {
    
    public class Music{
        int index;
        int plays;
        public Music(int index, int plays){
            this.index = index;
            this.plays = plays;
        }
    }
    
    public class Genre{
        String genre;
        int plays;
        
        public Genre(String genre, int plays){
            this.genre = genre;
            this.plays = plays;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = new int[plays.length];
        
        Map<String, PriorityQueue<Music>> genreMap = new HashMap<>();
        Map<String, Integer> genreCount = new HashMap<>();
        
        for(int i = 0; i < plays.length; i++){
            if(!genreMap.containsKey(genres[i])){
                genreMap.put(genres[i], new PriorityQueue<>((a,b) -> {
                    if(a.plays == b.plays){
                        return a.index - b.index;
                    }
                    return b.plays - a.plays;
                }));
                genreCount.put(genres[i], 0);
            }
            genreMap.get(genres[i]).add(new Music(i, plays[i]));
            genreCount.put(genres[i], genreCount.get(genres[i]) + plays[i]);
        }
        
        List<Genre> genreList = new ArrayList<>();
        
        for(String key : genreCount.keySet()){
            genreList.add(new Genre(key, genreCount.get(key)));
        }
        genreList.sort((a,b) -> {
            return b.plays - a.plays;
        });
        
        List<Integer> result = new ArrayList<>();
        for(Genre genre : genreList){
            PriorityQueue<Music> current = genreMap.get(genre.genre);
            for(int i = 0; i < 2; i++){
                Music music = current.poll();
                result.add(music.index);
                if(current.size() == 0){
                    break;
                }
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
}