import java.io.*;
import java.util.*;

class Main {
    static boolean[] keys;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        keys = new boolean[10];
        Arrays.fill(keys, true);
        
        if (M != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                keys[Integer.parseInt(st.nextToken())] = false;
            }
        }

        int min = Math.abs(N - 100);

        for (int i = 0; i <= 999999; i++) {
            String num = String.valueOf(i);
            boolean flag = true;
            for (char ch : num.toCharArray()) {
                if (!keys[ch - '0']) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                min = Math.min(min, Math.abs(Integer.parseInt(num) - N) + num.length());
            }
        }
        System.out.println(min);
    }
}