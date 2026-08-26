class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        for (int i = 0; i < seoul.length; i++) {
            String s = seoul[i];
            if (s.equals("Kim")) {
                return String.format("김서방은 %d에 있다", i);
            }
        }

        return answer;
    }
}