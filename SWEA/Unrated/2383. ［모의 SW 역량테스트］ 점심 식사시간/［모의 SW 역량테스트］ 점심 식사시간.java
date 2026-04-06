import java.io.*;
import java.util.*;

//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
public class  Solution {
    static int T, N;
    static int answer;
    static int[] choice;
    static List<People> peoples;
    static List<Stair> stairs;
    static class People {
        int r, c;

        People(int r ,int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class Stair {
        int r, c, k;

        Stair(int r ,int c, int k) {
            this.r = r;
            this.c = c;
            this.k = k;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            answer = Integer.MAX_VALUE;
            peoples = new ArrayList<>();
            stairs = new ArrayList<>();

            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num == 1) {
                        peoples.add(new People(r, c));
                    } else if (num != 0) {
                        stairs.add(new Stair(r, c, num));
                    }
                }
            }

            choice = new int[peoples.size()];

            dfs(0);

            sb.append("#" + tc + " " + answer + "\n");
        }
        System.out.println(sb.toString());
    }

    static void dfs(int depth) {
        if(depth == peoples.size()) {
            answer = Math.min(answer, simulate());
            return;
        }

        choice[depth] = 0;
        dfs(depth + 1);

        choice[depth] = 1;
        dfs(depth + 1);
    }

    static int simulate() {
        List<Integer> s0 = new ArrayList<>();
        List<Integer> s1 = new ArrayList<>();

        for (int i = 0; i < peoples.size(); i++) {
            People p = peoples.get(i);
            Stair s = stairs.get(choice[i]);

            int dist = Math.abs(p.r - s.r) + Math.abs(p.c - s.c);

            if(choice[i] ==0) s0.add(dist);
            else s1.add(dist);
        }

        int t0 = cal(s0, stairs.get(0).k);
        int t1 = cal(s1, stairs.get(1).k);

        return Math.max(t0, t1);
    }

    static int cal(List<Integer> arrivals, int k) {
        if (arrivals.isEmpty()) return 0;

        Collections.sort(arrivals);

        int[] end = new int[arrivals.size()];

        for (int i = 0; i < arrivals.size(); i++) {
            int start = arrivals.get(i) + 1;

            if (i >= 3) {
                start = Math.max(start, end[i - 3]);
            }

            end[i] = start + k;
        }

        return end[arrivals.size() - 1];
    }
}
/**
 * 사람 필요한 정보, 인덱스와 좌표 정보
 * 계단 필요한 정보, 인덱스와 좌표 정보, K
 * 스케줄 필요한 정보, 인덱스와 남은 시간
 * 인덱스별 들어갔는지 여부를 판단할 배열
 */