import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] sky = new int[H][W];
        for (int[] temp : sky) {
            Arrays.fill(temp, -1);
        }

        ArrayList<int[]> clouds = new ArrayList<>();
        for (int r = 0; r < H; r++) {
            String str = br.readLine();
            for (int c = 0; c < W; c++) {
                if (str.charAt(c) == 'c') {
                    clouds.add(new int[]{r, c, 0});
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        for (int[] temp : clouds) {
            q.offer(temp);
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int t = cur[2];

            if (sky[r][c] == -1) {
                sky[r][c] = t;
            } else {
                sky[r][c] = Math.min(sky[r][c], t);
            }

            int nextC = c + 1;
            int nextT = t + 1;
            if (nextC >= W) continue;
            if (sky[r][nextC] != - 1 && sky[r][nextC] <= nextT) continue;

            q.offer(new int[]{r, nextC, nextT});
        }

        StringBuilder sb = new StringBuilder();
        for (int[] temp : sky) {
            for (int status : temp) {
                sb.append(status).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}