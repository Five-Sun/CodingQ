import java.util.*;

class Solution {
    static String[] alpha = {"A", "E", "I", "O", "U"};
    static int count = 0;
    static boolean flag = false;
    public int solution(String word) {
        combi(word, new ArrayList<>());
        return count;
    }
    
    //1. 중복순열 메소드를 변형해서 단어를 만들고 카운팅, 같으면 flag를 변경해서 그만 동작하도록
    public static void combi(String word, ArrayList<String> list) {
        for(int i=0; i<alpha.length; i++) {
            if (list.size() == 5 || flag) return;
            list.add(alpha[i]);
            count++;
            if (check(word, list)) {
                flag = true;
                return;
            } else {
                combi(word, list);
                list.remove(list.size() - 1);
            }
        }
    }
    //2. 같은 단어인지 확인하는 메소드
    public static boolean check(String word, ArrayList<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
        return word.equals(sb.toString());
    }
}

//사전 규칙을 생각해봐라.
//1. A -> E -> I -> O -> U 순 사전순 오름차순임
//2. AA가 E 가 사전순 정렬을 하면 원래 더 빠름
//3. 순서를 구하려면 음,,조합을 만들어보면서 몇번째인지 구해야 하나?