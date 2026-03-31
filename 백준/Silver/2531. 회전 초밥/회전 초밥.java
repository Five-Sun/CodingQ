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
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + k - 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i < k - 1; i++) {
            arr[N + i] = arr[i];
        }

        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.clear();
            for (int j = 0; j < k; j++) {
                set.add(arr[i + j]);
            }
            set.add(c);

            answer = Math.max(answer, set.size());
        }

        System.out.println(answer);
    }
}
/**
 * 임의의 위치부터 k개의 접시를 연속해서 먹으면 할인된 정액 가격으로 제공
 * 초밥의 종류 하나가 쓰인 쿠폰을 발행, 1번 행사에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 추가로 무료로 제공
 * 만약 이 번호에 적혀진 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어 손님에게 제공
 *
 */