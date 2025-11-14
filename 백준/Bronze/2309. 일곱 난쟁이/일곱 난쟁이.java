import java.io.*;
import java.util.*;

class Main {
    static ArrayList<Integer> result = new ArrayList<>();
    static boolean flag = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] heights = new int[9];
        for (int i = 0; i < 9; i++) {
            heights[i] = Integer.parseInt(br.readLine());
        }

        dfs(heights, new boolean[9], 0, 0, 7);
        Collections.sort(result);
        
        for (int num : result) {
            System.out.println(num);
        }
    }

    static void dfs(int[] arr, boolean[] visited, int start, int depth, int r) {
        if (flag) return;
        if (depth == r) {
            int sum = 0;
            for (int i = 0; i < visited.length; i++) {
                if (visited[i]) sum += arr[i];
            }
            if (sum == 100) {
                for (int i = 0; i < visited.length; i++) {
                    if (visited[i]) result.add(arr[i]);
                }
                flag = true;
                return;
            }
        }

        for (int i = start; i < visited.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(arr, visited, i + 1, depth + 1, r);
                visited[i] = false;
            }
        }

    }
}