import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] box = new int[N];
        for (int i = 0; i < N; i++) {
            box[i] = i + 1;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int mid = Integer.parseInt(st.nextToken()) - 1;

            int[] temp = new int[end - start + 1];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = box[start + j] ;
            }
            for (int j = 0; j < mid - start; j++) {
                temp = swap(temp);
            }

            for (int j = 0; j < temp.length; j++) {
                box[start + j] = temp[j];
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(box[i] + " ");
        }
    }

    static int[] swap(int[] temp) {
        int len = temp.length;
        int tempN = 0;
        for (int i = 0; i < len - 1; i++) {
            tempN = temp[i];
            temp[i] = temp[i + 1];
            temp[i + 1] = tempN;
        }
        return temp;
    }
}