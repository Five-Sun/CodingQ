import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        long num = 1;
        while (true) {
            if (N < (num * (num + 1)) / 2) {
                System.out.println(num - 1);
                return;
            }
            num++;
        }
    }
}