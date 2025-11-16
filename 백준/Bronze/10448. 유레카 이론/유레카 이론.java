import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        List<Integer> tri = new ArrayList<>();
        for (int i = 1; i < 45; i++) {
            tri.add(Tn(i));
        }

        Set<Integer> eureka = new HashSet<>();
        for (int i = 0; i < tri.size(); i++) {
            for (int j = 0; j < tri.size(); j++) {
                for (int k = 0; k < tri.size(); k++) {
                    eureka.add(tri.get(i) + tri.get(j) + tri.get(k));
                }
            }
        }

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            if (eureka.contains(N)) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }

    static int Tn(int num) {
        return (num * (num + 1)) / 2;
    }
}