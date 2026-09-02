class Solution {
    public int solution(int[][] sizes) {
        int width = 0;
        int height = 0;
        for (int[] size : sizes) {
            int[] arr = rotation(size);
            width = Math.max(width, arr[0]);
            height = Math.max(height, arr[1]);
        }
        
        return width * height;
    }

    private int[] rotation(int[] arr) {
        if (arr[0] >= arr[1]) {
            int temp = arr[0];
            arr[0] = arr[1];
            arr[1] = temp;
        }
        return arr;
    }
}