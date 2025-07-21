class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> answer = new ArrayList<>();

        Map<String, Integer> pInfo = new HashMap<>();
        for(String pChar : p.split("")) {
            pInfo.put(pChar, pInfo.getOrDefault(pChar, 0) + 1);
        }

        int left = 0, right = 0, maxLength = p.length();
        Map<String, Integer> contains = new HashMap<>();
        String[] sChars = s.split("");
        while(right < s.length()) {
            String curChar = sChars[right++];

            if(!pInfo.containsKey(curChar)) {
                contains.clear();
                left = right;
                continue;
            }

            contains.put(curChar, contains.getOrDefault(curChar, 0) + 1);
            if(right - left == maxLength) {
                if(contains.equals(pInfo)) {
                    answer.add(left);
                }
                String leftChar = sChars[left++];
                contains.put(leftChar, contains.get(leftChar) - 1);
            }
        }

        return answer;
    }
}