import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int len = str.length();
        int count = 0;
        for (int i = 0; i < len; i++) {
            int left = i;
            int right = len - 1;
            boolean flag = true;
            while(left <= right) {
                if (left == right) {
                    count++;
                    break;
                }

                if (str.charAt(left) == str.charAt(right)) {
                    left++;
                    right--;
                    count += 2;
                } else {
                    flag = false;
                    count = 0;
                    break;
                }
            }
            if (flag) {
                System.out.println((len - count) * 2 + count );
                return;
            }
        }
    }
}