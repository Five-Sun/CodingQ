import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<String>[] qList = new Queue[N];
        StringTokenizer st;
        //1. 앵무새 세팅
        for (int i = 0; i < N; i++) {
            qList[i] = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                qList[i].offer(st.nextToken());
            }
        }

        //2. 받아 적은 문장 세팅
        Queue<String> sentence = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            sentence.offer(st.nextToken());
        }
        String answer = "Possible";
        while (!sentence.isEmpty()) {
            String nextWord = sentence.poll();

            boolean isPossible = false;
            for (int i = 0; i < N; i++) {
                if (!qList[i].isEmpty()) {
                    if (nextWord.equals(qList[i].peek())) { //일치하는 단어가 있으면
                        isPossible = true;
                        qList[i].poll();//해당 단어 사용했으므로 제거
                        break; //for문 종료
                    }
                }
            }
            if (!isPossible) {
                answer = "Impossible";
                break; //불가능하므로 while문 종료
            }
        }

        //3. 모든 말을 옮겨 적지 않았으므로 실패
        for (Queue<String> q : qList) {
            if (!q.isEmpty()) {
                answer = "Impossible";
                break;
            }
        }

        System.out.println(answer);
    }
}