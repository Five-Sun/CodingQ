import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[5];
        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while(true) {
            boolean check = true;
            for (int i = 0; i < 5; i++) {
                if (arr[i] != i + 1) {
                    check = false;
                    break;
                }
            }

            if (check) {
                break;
            }

            if (arr[idx] > arr[idx + 1]) {
                int temp = arr[idx];
                arr[idx] = arr[idx + 1];
                arr[idx + 1] = temp;

                for (int num : arr) {
                    sb.append(num).append(" ");
                }
                sb.append("\n");
            }
            idx = (idx + 1) % 4;
        }
        System.out.println(sb);
    }
}