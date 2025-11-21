import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        int right = 0;
        int left = 0;
        int mid = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
            right += num;
            left = Math.max(left, num);
        }

        while(left < right) {
            mid = (left + right) / 2;

            int sum = 0;
            int cnt = 1;

            for (int i = 0; i < N; i++) {
                int num = arr[i];
                if (sum + num > mid) {
                    sum = num;
                    cnt++;
                } else {
                    sum += num;
                }
            }

            if (cnt <= M) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);

        int sum = 0;
        int cnt = 0;
        ArrayList<Integer> group = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int num = arr[i];
            if (sum + num > left) {
                group.add(cnt);
                sum = num;
                cnt = 1;
            } else {
                sum += num;
                cnt++;
            }

            if (N - i - 1 == M - group.size() -1) {
                group.add(cnt);
                for (int j = i + 1; j < N; j++) group.add(1);
                break;
            }
        }

        if (group.size() < M) group.add(cnt);

        for (int num : group) {
            System.out.print(num + " ");
        }
    }
}