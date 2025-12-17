import java.io.*;
import java.util.*;

public class Main {
    static int rLength;
    static int cLength;
    static int[][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        A = new int[101][101];
        for (int[] arr : A) {
            Arrays.fill(arr, -1);
        }

        rLength = 3;
        cLength = 3;
        for (int i = 1; i <= rLength; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= cLength; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int count = 0;
        while (count <= 100) {
            if (A[r][c] == k) {
                System.out.println(count);
                return;
            }
            if (rLength >= cLength) {
                rCal();
            } else {
                cCal();
            }
            sync();
            count++;
        }
        System.out.println(-1);
    }

    static void rCal() {
        HashMap<Integer, Integer> map;
        ArrayList<Number> list;
        int newLen = cLength;
        for (int r = 1; r <= rLength; r++) {
            map = new HashMap<>();
            list = new ArrayList<>();
            //map에 담으면서 count
            for (int c = 1; c <= cLength; c++) {
                if (A[r][c] == 0) continue;
                map.put(A[r][c], map.getOrDefault(A[r][c], 0) + 1);
            }
            //list에 담으면서 sort
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                list.add(new Number(entry.getKey(), entry.getValue()));
            }
            //정렬
            Collections.sort(list);
            //행 길이 최신화
            newLen = Math.max(newLen, list.size() * 2);

            //정렬 후 배열에 반영 전 리셋
            for (int c = 1; c <= cLength; c++) {
                A[r][c] = -1;
            }
            for (int i = 0; i < list.size(); i++) {
                Number number = list.get(i);
                A[r][i * 2 + 1] = number.n;
                A[r][i * 2 + 2] = number.c;
            }
        }
        cLength = newLen;

    }
    static void cCal() {
        HashMap<Integer, Integer> map;
        ArrayList<Number> list;
        int newLen = rLength;
        for (int c = 1; c <= cLength; c++) {
            map = new HashMap<>();
            list = new ArrayList<>();
            //map에 담으면서 count
            for (int r = 1; r <= rLength; r++) {
                if (A[r][c] == 0) continue;
                map.put(A[r][c], map.getOrDefault(A[r][c], 0) + 1);
            }
            //list에 담으면서 sort
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                list.add(new Number(entry.getKey(), entry.getValue()));
            }
            //정렬
            Collections.sort(list);
            //열 길이 최신화
            newLen = Math.max(newLen, list.size() * 2);
            //정렬 후 배열에 반영 전 리셋
            for (int r = 1; r <= rLength; r++) {
                A[r][c] = -1;
            }
            for (int i = 0; i < list.size(); i++) {
                Number number = list.get(i);
                A[i * 2 + 1][c] = number.n;
                A[i * 2 + 2][c] = number.c;
            }
        }
        rLength = newLen;
    }
    static void sync() {
        for (int r = 1; r <= rLength; r++) {
            for (int c = 1; c <= cLength; c++) {
                if (A[r][c] == -1) {
                    A[r][c] = 0;
                }
            }
        }
    }

    static class Number implements Comparable<Number>{
        int n, c;

        Number(int n, int c) {
            this.n = n;
            this.c = c;
        }

        @Override
        public int compareTo(Number o) {
            if (this.c == o.c) return this.n - o.n;
            return this.c - o.c;
        }
    }
}
//A[r][c] = k 일 때까지 몇초가 걸리는가
//행의 개수가 크거나 같으면 R연산, 반대면 C 연산
//계속 덮어쓰기 하는 형태로 풀어야할 거 같은데?
//클래스를 만들어서 등장횟수 오름차순, 수 오름차순 정렬 기준으로 리스트를 담아서 하나씩 움직이면서 새로 답아내는 방식으로 해야할 거 같은데,,
