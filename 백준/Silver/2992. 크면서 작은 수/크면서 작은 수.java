import java.io.*;
import java.util.*;

public class Main {
    static int INF = 100_000_000;
    static int answer = INF;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int[] arr = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arr[i] = str.charAt(i) - '0';
        }
        dfs(Integer.parseInt(str), arr, new int[str.length()], new boolean[str.length()], 0);
        System.out.println(answer == INF ? 0 : answer);
    }

    static void dfs(int num, int[] arr, int[] out, boolean[] visited, int depth) {
        if (depth == arr.length) {
            int result = out[0];
            for (int i = 1; i < out.length; i++) {
                result *= 10;
                result += out[i];
            }
            if (num >= result) {
                return;
            } else {
                answer = Math.min(result, answer);
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                dfs(num, arr, out, visited, depth + 1);
                visited[i] = false;
            }
        }


    }
}