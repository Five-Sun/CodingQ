import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashSet<String> set1 = new HashSet<>();

        for(int i=0; i<n; i++) {
            set1.add(br.readLine());
        }
        int count = 0;
        for(int i=0; i<m; i++) {
            if(set1.contains(br.readLine())) count++;
        }
        System.out.println(count);
    }
}