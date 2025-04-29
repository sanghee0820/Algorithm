import java.util.*;
class Solution {
    // [x, y, a, b]
    // a 구조물의 종류 0 = pillar, 1 = beam
    // b 설치 삭제 0 = 삭제, 1 = 설치
    private static final int FRAME_TYPE_PILLAR = 0;
    private static final int FRAME_TYPE_BEAM = 1;
    private static final int BUILD_TYPE_DELETE = 0;
    private static final int BUILD_TYPE_ADD = 1;
    
    public int[][] solution(int n, int[][] build_frame) {
        int[][] answer = {};
        
        boolean[][] pillarMap = new boolean[n + 3][n + 3];
        boolean[][] beamMap = new boolean[n + 3][n + 3];
        
        for (int[] build : build_frame) {
            int x = build[0];
            int y = build[1];
            int frameType = build[2];
            int buildType = build[3];
            
            if (buildType == BUILD_TYPE_ADD) {
                if (canAdd(pillarMap, beamMap, frameType, x, y)) {
                    if (frameType == FRAME_TYPE_PILLAR) {
                        pillarMap[x][y] = true;
                        continue;
                    }
                    beamMap[x][y] = true;
                }
                continue;
            }
            
            if (frameType == FRAME_TYPE_PILLAR) {
                pillarMap[x][y] = false;
                if (isValid(pillarMap, beamMap, n)) {
                    continue;
                }
                pillarMap[x][y] = true;
                continue;
            }
            
            beamMap[x][y] = false;
            if (isValid(pillarMap, beamMap, n)) {
                continue;
            }
            beamMap[x][y] = true;
        }
        
        List<int[]> result = new ArrayList<>();
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillarMap[x][y]){
                    result.add(new int[]{x, y, FRAME_TYPE_PILLAR});
                }
                if (beamMap[x][y]) {
                    result.add(new int[]{x, y, FRAME_TYPE_BEAM});
                }
            }
        }

        result.sort((a, b) -> {
            if (a[0] == b[0]) {
                if(a[1] == b[1]) {
                    return a[2] - b[2];
                }
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        
        return result.toArray(new int[result.size()][]);
    }
        private boolean canAdd(boolean[][] pillar, boolean[][] beam, int type, int x, int y) {
        if (type == FRAME_TYPE_PILLAR) {
            if (y == 0) {
                return true;
            }
            if (pillar[x][y - 1]) {
                return true;
            }
            if (beam[x][y]) {
                return true;
            }
            if (x > 0 && beam[x - 1][y]) {
                return true;
            }
            return false;
        }
        
        if (pillar[x][y - 1]) {
            return true;
        }
        if (pillar[x + 1][y - 1]) {
            return true;
        }
        if (x > 0 && beam[x - 1][y] && beam[x + 1][y]) {
            return true;
        }
        
        return false;
    }
    
    private boolean isValid(boolean[][] pillar, boolean[][] beam, int n) {
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                if (pillar[x][y] && !canAdd(pillar, beam, FRAME_TYPE_PILLAR, x, y)) {
                    return false;
                }
                if (beam[x][y] && !canAdd(pillar, beam, FRAME_TYPE_BEAM, x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}