import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        TreeSet<String> set = new TreeSet<>(Collections.reverseOrder());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String sign = st.nextToken();

            if (sign.equals("enter")) {
                set.add(name);
            } else {
                set.remove(name);
            }
        }
        for (String name : set) {
            System.out.println(name);
        }
    }
}
