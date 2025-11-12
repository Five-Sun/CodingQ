import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] strArr = new String[numbers.length];
        
        for(int i=0; i<numbers.length; i++) {
            strArr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(strArr, (a, b) -> {
            return (b + a).compareTo(a + b);
        });
        
        if(strArr[0].equals("0")) return "0";
        for(String s : strArr) {
            answer += s;
        }
        return answer;
    }
}