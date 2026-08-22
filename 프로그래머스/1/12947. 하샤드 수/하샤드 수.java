class Solution {
    public boolean solution(int x) {
        return isPossible(x);
    }

    static boolean isPossible(int num) {
        int sum = 0;
        int original = num;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return (original % sum) == 0;
    }
}