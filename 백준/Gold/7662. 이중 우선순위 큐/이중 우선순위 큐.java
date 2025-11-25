import java.io.*;
import java.util.*;

import java.io.BufferedReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());

            while (k-- > 0) {
                st = new StringTokenizer(br.readLine());
                String command = st.nextToken();
                int num = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    map.put(num, map.getOrDefault(num, 0) + 1);
                } else {
                    if (!map.isEmpty()) {
                        if (num == 1) {
                            if (!map.isEmpty()) {
                                int first = map.firstKey();
                                map.put(first, map.get(first) - 1);
                                if (map.get(first) == 0) {
                                    map.remove(first);
                                }
                            }
                        } else {
                            int last = map.lastKey();
                            map.put(last, map.get(last) - 1);
                            if (map.get(last) == 0) {
                                map.remove(last);
                            }
                        }
                    }
                }
            }
            if (map.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(map.firstEntry().getKey() + " " + map.lastEntry().getKey());
            }
        }
    }
}