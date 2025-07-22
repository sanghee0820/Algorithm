class Solution {
    public String decodeString(String s) {
        Deque<String> deque = new ArrayDeque<>();

        char[] sChars = s.toCharArray();
        for(int index = 0; index < sChars.length; index++) {
            char sChar = sChars[index];
            if(sChar == ']') {
                StringBuilder cur = new StringBuilder();
                while(!deque.peekLast().equals("[") ){
                    cur.append(new StringBuilder(deque.removeLast()).reverse().toString());
                }
                deque.removeLast();

                StringBuilder countBuilder = new StringBuilder();
                while(!deque.isEmpty() && Character.isDigit(deque.peekLast().charAt(0))) {
                    countBuilder.append(deque.removeLast());
                }
                Integer count = Integer.parseInt(countBuilder.reverse().toString());
                deque.add(cur.reverse().toString().repeat(count));
                continue;
            }
            deque.add(String.valueOf(sChar));
        }

        StringBuilder answer = new StringBuilder();
        while(!deque.isEmpty()) {
            answer.append(deque.removeFirst());
        }

        return answer.toString();
    }
}