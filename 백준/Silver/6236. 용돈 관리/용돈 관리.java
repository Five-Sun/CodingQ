import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] uses = new int[N];
        int max = 0;
        int sum = 0;
        for (int i = 0; i < N; i++) {
            uses[i] = Integer.parseInt(br.readLine());
            sum += uses[i];
            max = Math.max(max, uses[i]);
        }

        int left = max;
        int right = sum;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (isPossible(uses, mid, M)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(answer);
    }

    static boolean isPossible(int[] uses, int mid, int M) {
        int count = 0;
        int money = 0;

        for (int i = 0; i < uses.length; i++) {
            if (money < uses[i]) {
                count++;
                money = mid - uses[i];
            } else {
                money = money - uses[i];
            }
        }

        return count <= M;
    }
}
//N일동안 사용할 금액 계산
//M번만 돈을 빼서 사용하고 싶음
//K원을 인출
//남은 돈이 충분하면 인출 안함
//부족하면 남은 금액을 넣고, 다시 K원 인출
//남은 금애긍로도 충분하지만 M번을 맞추기 위해서 또 뽑을 수도 있음
//최소 K 금액
//인출 횟수가 적으면 가능하다라고 판단하면 될듯?
//인출 최소 금액과 최대 금액을 어케 설정하면 좋지?
//최대 금액은 전체의 합? 그러면 1번만 뽑아도 다 가능
//최소 금액은 써야하는 돈 중에 가장 큰 금액일 듯? 근데 그냥 대충 0으로 놔도 큰 문제는 없을 듯