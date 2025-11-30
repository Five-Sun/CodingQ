import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        TreeSet<Integer> A = new TreeSet<>();
        TreeSet<Integer> B = new TreeSet<>();

        st = new StringTokenizer(br.readLine());
        while (N-- > 0) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        A.removeAll(B);

        if (A.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(A.size());
            for (int num : A) {
                System.out.print(num + " ");
            }
        }
    }
}