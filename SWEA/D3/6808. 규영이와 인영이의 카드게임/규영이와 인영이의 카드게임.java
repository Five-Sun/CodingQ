import java.io.*;
import java.util.*;

public class Solution {
	static int[] gy;
	static int[] in;
	static int win, lose;
	static int answer = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
        	st = new StringTokenizer(br.readLine());
        	gy = new int[9];
        	in = new int[9];
        	win = 0;
        	lose = 0;
        	boolean[] used = new boolean[19];
        	for(int i=0; i<9; i++) {
        		int num = Integer.parseInt(st.nextToken());
        		gy[i] = num;
        		used[num] = true;
        	}
        	
        	int idx = 0;
        	for(int i=1; i<19; i++) {
        		if(!used[i]) in[idx++] = i;
        	}
        	
        	permu(0, new int[9], new boolean[9]);
        	
        	sb.append("#" + tc + " " + win + " " + lose + "\n");
        }
        System.out.println(sb.toString());
    }
    
    static void permu(int depth, int[] out, boolean[] visited) {
    	if(depth == 9) {
    		game(out);
    		return;
    	}
    	for(int i=0; i<in.length; i++) {
    		if(visited[i]) continue;
    		out[depth] = in[i];
    		visited[i] = true;
    		permu(depth + 1, out, visited);
    		visited[i] = false;
    	}
    }
    
    static void game(int[] out) {
    	int gys = 0, ins = 0;
    	
    	for(int i=0; i<9; i++) {
    		int gyn = gy[i];
    		int inn = out[i];
    		
    		if(gyn > inn) {
    			gys += (gyn + inn);
    		} else if(gyn < inn) {
    			ins += (gyn + inn);
    		}
    	}
    	
    	if(gys > ins) {
    		win++;
    	} else if(gys < ins) {
    		lose++;
    	}
    }
}
