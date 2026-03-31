import java.io.*;
import java.util.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class Main {
    static int N, d, k, c;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] count  = new int[100001];
        int left  = 0;
        int answer = 0;

        for (int right = 0; right < N; right++) {
            count[arr[right]]++;

            while(count[arr[right]] > K) {
                count[arr[left]]--;
                left++;
            }
            
            answer = Math.max(answer, right - left + 1);
        }

        System.out.println(answer);
    }
}
/**
 * 시작 위치에 1,2,번 포인터를 설정
 * 사이의 구간을 조사하고 조건을 확인
 *
 */