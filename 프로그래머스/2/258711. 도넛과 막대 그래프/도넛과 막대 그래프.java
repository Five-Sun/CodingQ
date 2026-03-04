import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        //가능한 최대 크기로 설정
        int maxSize = 1_000_000;
        int[] in = new int[maxSize + 1];
        int[] out = new int[maxSize + 1];
        boolean[] used = new boolean[maxSize + 1];
        int maxNum = 0;
        //간선 정보에 따라 in/out 증가
        for(int [] row : edges) {
            int a = row[0];
            int b = row[1];
            if(!used[a]) used[a] = true;
            if(!used[b]) used[b] = true;
            maxNum = Math.max(maxNum, Math.max(a, b));
            out[a]++;
            in[b]++;
        }
        
        for(int i=1; i<=maxSize; i++) {
            if(!used[i]) {
                out[i] = -1;
                in[i] = -1;
            }
        }
        
        //정답에 포함할 변수 초기화
        int gen = 0;
        int donut = 0;
        int stick = 0;
        int eight = 0;
        
        for(int i=1; i<=maxNum; i++) {
            if(in[i] == -1 && out[i] == -1) continue;
            
            if(in[i] == 0 && out[i] >= 2) {
                gen = i;
            } else if (in[i] >= 2 && out[i] == 2) {
                eight++;
            } else if(out[i] == 0) {
                stick++;
            }
        }
        
        donut = out[gen] - eight - stick;
        
        int[] answer = {gen, donut, stick, eight};
        
        return answer;
    }
}
//도넛 모양 그래프, 막대 모양 그래프, 8자 모양 그래프가 존재.
//1개 이상의 정점과 정점들을 연결하는 '단방향' 간선으로 구성
//막대 그래프는 n개의 정점, n-1개의 간선
//8자 모양은 2n + 1개의 정점과 2n + 2개의 간선
//도넛은 n개의 정점과 n개의 간선 사이클이 있는 형태