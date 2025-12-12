import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int cnt1 = 0;
        int cnt2 = 0;

        char prev = s.charAt(0);
        cnt1++;
        boolean flag = true;//1이면서 true면 pass, true인데 0이면 count++,

        for (char ch : s.toCharArray()) {
            if (ch != prev && flag) {
                flag = false;
                cnt2++;
                prev = ch;
            } else if (ch != prev && !flag) {
                flag = true;
                cnt1++;
                prev = ch;
            }
        }
        System.out.println(Math.min(cnt1, cnt2));
    }
}