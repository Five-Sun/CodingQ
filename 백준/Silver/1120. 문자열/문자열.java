import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();
        int ALength = A.length();
        int BLength = B.length();
        int answer = 50;
        for (int i = 0; i <= BLength - ALength; i++) {
            int count = 0;
            for (int j = 0; j < ALength; j++) {
                if (A.charAt(j) != B.charAt(j + i)) {
                    count++;
                }
            }
            answer = Math.min(answer, count);
        }
        System.out.println(answer);
    }
}