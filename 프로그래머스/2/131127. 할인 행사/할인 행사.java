import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        int len = want.length;
        int totalQuantity = 0;
        for (int i = 0; i < len; i++) {
            String product = want[i];
            int quantity = number[i];
            totalQuantity += quantity;
            map.put(product, quantity);
        }

        for (int i = 0; i < 10; i++) {
            if (map.containsKey(discount[i])) {
                if (map.get(discount[i]) > 0) totalQuantity--;
                map.put(discount[i], map.get(discount[i]) - 1);
            }
        }

        if (totalQuantity == 0) answer++;

        for (int i = 0; i < discount.length - 10; i++) {
            if(map.containsKey(discount[i])){
                if(map.get(discount[i])>=0){
                    totalQuantity++;
                }
                map.put(discount[i], map.get(discount[i])+1);
            }
            if(map.containsKey(discount[i+10])){
                if(map.get(discount[i+10])>0){
                    totalQuantity--;
                }
                map.put(discount[i+10], map.get(discount[i+10])-1);
            }
            if(totalQuantity==0) answer++;
        }

        return answer;
    }
}
/**
 * 물품 자체가 안맞는 경우는 지금까지 본 idx 이후 부터 재 탐색
 * 수량이 안맞는 경우는 슬라이딩 윈도우 처럼, 처음 요소만 삭제하고 다시 이어서 탐색
 */