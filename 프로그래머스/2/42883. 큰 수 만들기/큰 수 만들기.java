import java.util.*;

class Solution {
    
    public String solution(String number, int k) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        int count = 0;
        boolean flag = false;
        for(int i=0; i<number.length(); i++) {
            if(flag) {
                stack.push(number.charAt(i));
            } else {
                if(stack.isEmpty()) {
                    stack.push(number.charAt(i));
                } else {
                        while(!stack.isEmpty() && stack.peek() < number.charAt(i)) {
                            stack.pop();
                            count++;
                            if(count == k) {
                                flag = true;
                                break;
                            }
                        }
                        stack.push(number.charAt(i));
                }
            }
        }
        for(Character ch : stack) {
            answer += ch;
        }
        
        return answer.substring(0, number.length() - k);
    }
    
//     //시간초과 발생, 0이 시작인 경우에 처리 필요
//     public static void combi(String number, boolean[]visited , int start, int depth, int r) {
//         if(depth == r) {
//             String str = "";
//             for(int i=0; i<visited.length; i++) {
//                 if(visited[i]) str += number.charAt(i);
//             }
//             list.add(str);
//             return;
//         }
        
//         for(int i=start; i<number.length(); i++) {
//             if(!visited[i]) {
//                 visited[i] = true;
//                 combi(number, visited, i + 1, depth + 1, r);
//                 visited[i] = false;
//             }
//         }
//     }
}
//1. 조합 -> 정렬 - 내림차순, 시간 복잡도 n!/(n -r)! r! * r, 시간 복잡도가 초과할 거 같은데? 우선 해봐, 시간 초과가 나네.
//
