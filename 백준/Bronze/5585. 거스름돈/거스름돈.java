import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int rest = 1000 - N;
        int answer = 0;
        if (rest / 500 > 0) {
            answer += rest / 500;
            rest = rest % 500;
        }
        if (rest / 100 > 0) {
            answer += rest / 100;
            rest = rest % 100;
        }
        if (rest / 50 > 0) {
            answer += rest / 50;
            rest = rest % 50;
        }
        if (rest / 10 > 0) {
            answer += rest / 10;
            rest = rest % 10;
        }
        if (rest / 5 > 0) {
            answer += rest / 5;
            rest = rest % 5;
        }
        answer += rest;
        System.out.println(answer);
    }
}