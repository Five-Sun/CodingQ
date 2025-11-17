import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<String, Integer> map = new HashMap<>();
        String str = "";
        double totalCnt = 0;
        while ((str = br.readLine()) != null && !str.isEmpty()) {
            map.put(str, map.getOrDefault(str, 0) + 1);
            totalCnt++;
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());


        entries.sort(Map.Entry.comparingByKey());

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            sb.append(entry.getKey()).append(" ").append(String.format("%.4f", (entry.getValue() / totalCnt) * 100)).append("\n");
        }
        System.out.println(sb);
    }
}