class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        
        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            
            // 케이스 1: 짝수인 경우 (가장 오른쪽 비트가 0)
            if (num % 2 == 0) {
                answer[i] = num + 1;
            } 
            // 케이스 2: 홀수인 경우 (가장 오른쪽의 '01' 패턴을 '10'으로 변경)
            else {
                long lastZero = ~num & (num + 1); // 가장 오른쪽의 0인 비트 위치 찾기
                answer[i] = num + lastZero - (lastZero >> 1);
            }
        }
        return answer;
    }
}