import java.util.*;
import java.util.stream.*;
class Solution2 {
    public int solution(String[][] relation) {
        int answer = 0;

        int columnCount = relation[0].length;

        List<int[]> keyList = new ArrayList<>();

        for(int keyLength = 1; keyLength <= columnCount; keyLength++) {
            answer += countCandidateKey(new int[keyLength], new boolean[columnCount], 0, keyList, relation);
        }

        return answer;
    }

    public int countCandidateKey(int[] keyIndexes, boolean[] visited, int cur, List<int[]> keyList, String[][] relation) {
        if(cur == keyIndexes.length) {
            Set<String> checked = new HashSet<>();

            for(String[] row : relation) {
                String key = Arrays.stream(keyIndexes).mapToObj(keyIndex -> row[keyIndex]).collect(Collectors.joining("-"));
                if(checked.contains(key)) {
                    return 0;
                }
                checked.add(key);
            }

            for (int[] existingKey : keyList) {
                boolean isSubset = true;

                for (int kVal : existingKey) {
                    boolean found = false;
                    for (int newKeyIdxVal : keyIndexes) {
                        if (kVal == newKeyIdxVal) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        continue;
                    }

                    isSubset = false;
                    break;
                }

                if (isSubset) {
                    return 0;
                }
            }
            keyList.add(keyIndexes.clone());
            return 1;
        }

        int candidateKeyCount = 0;
        for(int index = cur == 0 ? 0 : keyIndexes[cur - 1] + 1; index < visited.length; index++) {
            if(visited[index]) {
                continue;
            }
            keyIndexes[cur] = index;
            visited[index] = true;
            candidateKeyCount += countCandidateKey(keyIndexes, visited, cur + 1, keyList, relation);
            visited[index] = false;
        }
        return candidateKeyCount;
    }
}