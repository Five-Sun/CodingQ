import java.io.*;
import java.util.*;

class Main {
    static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> q = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                q.add(num);
            } else if (command.equals("pop")) {
                if (q.size() == 0) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.get(0) + "\n");
                    q.remove(0);
                }
            } else if (command.equals("size")) {
                sb.append(q.size() + "\n");
            } else if (command.equals("empty")) {
                if (q.size() == 0) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            } else if (command.equals("front")) {
                if (q.size() == 0) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.get(0) + "\n");
                }
            } else if (command.equals("back")) {
                if (q.size() == 0) {
                    sb.append("-1\n");
                } else {
                    sb.append(q.get(q.size() - 1) + "\n");
                }
            }
        }
        System.out.println(sb);
    }
}