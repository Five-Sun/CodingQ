import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        HashMap<String, Integer> map = new HashMap<>();

        while (N-- > 0) {
            String book = br.readLine();
            map.put(book, map.getOrDefault(book, 0) + 1);
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
        Collections.sort(entries, (a, b) -> {
            if (Objects.equals(a.getValue(), b.getValue())) return a.getKey().compareTo(b.getKey());
            return b.getValue().compareTo(a.getValue());
        });

        System.out.println(entries.get(0).getKey());
    }
}
