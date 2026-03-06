class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int len = balls.length;
        int[] answer = new int[len];
        for(int r=0; r < len; r++) {
            int x = balls[r][0];
            int y = balls[r][1];
            
            //왼쪽 벽 대칭
            int dist1 = calDist(startX, startY, -x, y);
            //오른쪽 벽 대칭
            int dist2 = calDist(startX, startY, 2 * m -x, y);
            //위쪽 벽 대칭
            int dist3 = calDist(startX, startY, x, 2 * n -y);
            //아래쪽 벽 대칭
            int dist4 = calDist(startX, startY, x, -y);
            
            if(startX == x && y < startY) {
                answer[r] = Math.min(Math.min(dist1, dist2), dist3);
            } else if (startX == x && y > startY) {
                answer[r] = Math.min(Math.min(dist1, dist2), dist4);
            } else if (startY == y && x < startX) {
                answer[r] = Math.min(Math.min(dist2, dist3), dist4);
            } else if (startY == y && x > startX){
                answer[r] = Math.min(Math.min(dist1, dist3), dist4);
            } else {
                answer[r] = Math.min(Math.min(Math.min(dist1, dist2), dist3), dist4);
            }
        }
        return answer;
    }
    
    private int calDist(int x1, int y1, int x2, int y2) {
        return (int) (Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}