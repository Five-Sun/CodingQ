import java.io.*;
import java.util.*;

public class Main {
	static int[] counts;
	static int[] rules;
	static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        String str = br.readLine();
        st = new StringTokenizer(br.readLine());
        
        rules = new int[4]; //A, C, G, T
        for(int i=0; i<4; i++) {
        	rules[i] = Integer.parseInt(st.nextToken());
        }
        
       int left = 0;
       int right = M -1;
       counts = new int[4];
       for(int i=left; i<=right; i++) {
    	   char ch = str.charAt(i);
    	   up(ch);
       }
       check();
       while(right + 1 < N) {
    	   down(str.charAt(left++));
    	   up(str.charAt(++right));
    	   check();
       }
       System.out.println(answer);
    }
    
    static void up(char ch) {
    	switch(ch) {
    	case 'A':
    		counts[0]++;
    		break;
    	case 'C':
    		counts[1]++;
    		break;
    	case 'G':
    		counts[2]++;
    		break;
    	case 'T':
    		counts[3]++;
    		break;
    	}
    }
    
    static void down(char ch) {
    	switch(ch) {
    	case 'A':
    		counts[0]--;
    		break;
    	case 'C':
    		counts[1]--;
    		break;
    	case 'G':
    		counts[2]--;
    		break;
    	case 'T':
    		counts[3]--;
    		break;
    	}
    }
    
    static void check() {
    	for(int i=0; i<4; i++) {
    		if(rules[i] > counts[i]) return;
    	}
    	answer++;
    }
}
