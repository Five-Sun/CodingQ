import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //트럭의 수
        int W = Integer.parseInt(st.nextToken()); //다리의 길이, Queue 제한 사이즈
        int L = Integer.parseInt(st.nextToken()); //제한 무게

        Queue<Integer> trucks = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            trucks.offer(Integer.parseInt(st.nextToken()));
        }

        int time = 0;
        int total = 0;
        Queue<int[]> bridge = new ArrayDeque<>();

        while (!trucks.isEmpty() || !bridge.isEmpty()) {
            time++;
            //하차 처리
            if (!bridge.isEmpty()) {
                int cur[] = bridge.peek(); //가장 앞에 있는 트럭
                if (cur[1] == time) { //완전히 내릴 시간인지 확인
                    total -= cur[0]; //현재 하중에서 제외
                    bridge.poll(); //다리에서 내림 처리
                }
            }
            //승차 처리
            //1. 탈 공간이 있는지
            if (!trucks.isEmpty()) {
                if(bridge.size() >= W) continue;
                //2. 무게가 되는지
                if (total + trucks.peek() > L) continue;
                //3. 그러면 탑승
                int next = trucks.poll();
                bridge.offer(new int[]{next, time + W});
                total += next;
            }
        }
        System.out.println(time);
    }
    //트럭도 순서대로 Queue 에서 나와야함
    //다리를 Queue라고 생각하고, 길이 이상만큼, Queue에 적재될 수 없음
    //Queue에 들어가 있는 전체 하중을 변수로 관리해줘야 함.
    //최대 하중이 초과이면 트럭 q에서 다리 q로 갈 수가 없고,
    //시간은 어케 관리할 것인가? while문을 돌면서 증가시키는데, 다리를 건너는 것도 시간으로 관리할 수 있다.
    //다리에 현재의 무게와 나가는 시간을 담아서 넣어두자.
    //다리에 타기 전에는 trunk
}