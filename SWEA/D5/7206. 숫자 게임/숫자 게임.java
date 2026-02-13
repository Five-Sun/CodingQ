import java.io.*;
import java.util.*;

public class Solution {
	static int N, answer;
	static int[] nums;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
        	int n = Integer.parseInt(br.readLine());
        	nums = new int[n + 1];
        	answer = solve(n);
        	sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }

        System.out.print(sb.toString());
    }
    static int solve(int n) {
    	if(n < 10) {
    		return 0;
    	}
    	
    	if(nums[n] != 0) {
    		return nums[n];
    	}
    	
    	int best = 0;
    	String num = String.valueOf(n);
    	int N = num.length() - 1;
    	
    	for(int mask = 1; mask < (1 << N); mask++) {
    		int sum = 1;
    		int lastIdx = num.length();
    		int startIdx = num.length() - 1;
    		for(int i = 0; i < N; i++) {
    			//1인 비트에서 끊어서 저장
    			if((mask & (1 << i)) != 0) {
    				//끊어주기.
    				String subStr = num.substring(startIdx, lastIdx);
    				sum *= Integer.parseInt(subStr);
    				//lastIdx변경
    				lastIdx = startIdx;
    			}
    			startIdx--;
    		}
    		//남아있는 거 list 넣어주기
    		String subStr = num.substring(startIdx, lastIdx);
			sum *= Integer.parseInt(subStr);
			
    		//연산
    		best = Math.max(best, 1 + solve(sum));
    	}
		nums[n] = best;
    	return best;
    }
}
// 
//1. 2^(str.length()-1) 조합이 계속 반복되는 구조
//2. 하나의 조합을 계속 타고 들어가서 10보다 작으면 그때 return 해야 함
//3. 하나씩 보면서 숫자를 리스트에 담아서 연산하고 숫자를 반환하고 재귀타
//4. 01 -> 123 = 12 3