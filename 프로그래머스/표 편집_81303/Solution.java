import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        int[] prev = new int[n];
        int[] next = new int[n];
        boolean[] isDeleted = new boolean[n];
        Stack<Integer> deleted = new Stack<>();

        for (int i = 0; i < n; i++) {
            prev[i] = i - 1;
            next[i] = i + 1;
        }
        next[n - 1] = -1;

        int index = k;

        for (String line : cmd) {
            String[] parts = line.split(" ");
            String command = parts[0];

            if (command.equals("U")) {
                int count = Integer.parseInt(parts[1]);
                index = moveUp(index, count, prev);
                continue;
            }

            if (command.equals("D")) {
                int count = Integer.parseInt(parts[1]);
                index = moveDown(index, count, next);
                continue;
            }

            if (command.equals("C")) {
                index = doC(index, prev, next, isDeleted, deleted);
                continue;
            }

            if (command.equals("Z")) {
                index = doZ(index, prev, next, isDeleted, deleted);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < isDeleted.length; i++) {
            if (isDeleted[i]) {
                sb.append('X');
                continue;
            }
            sb.append('O');
        }
        return sb.toString();
    }

    private int moveUp(int index, int count, int[] prev) {
        for (int i = 0; i < count; i++) {
            index = prev[index];
        }
        return index;
    }

    private int moveDown(int index, int count, int[] next) {
        for (int i = 0; i < count; i++) {
            index = next[index];
        }
        return index;
    }

    private int doC(int index, int[] prev, int[] next, boolean[] isDeleted, Stack<Integer> deleted) {
        deleted.push(index);
        isDeleted[index] = true;

        if (prev[index] != -1) {
            next[prev[index]] = next[index];
        }
        if (next[index] != -1) {
            prev[next[index]] = prev[index];
        }

        if (next[index] != -1) {
            return next[index];
        }
        return prev[index];
    }

    private int doZ(int index, int[] prev, int[] next, boolean[] isDeleted, Stack<Integer> deleted) {
        int restore = deleted.pop();
        isDeleted[restore] = false;

        if (prev[restore] != -1) {
            next[prev[restore]] = restore;
        }
        if (next[restore] != -1) {
            prev[next[restore]] = restore;
        }

        return index;
    }
}