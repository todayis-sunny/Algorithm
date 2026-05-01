class Solution {
    public int solution(int number, int limit, int power) {
        int result = 0;
        for (int num = 1; num <= number; num++) {
            // 약수의 개수
            int count = 0;
            for (int k = 1; k <= (int) Math.sqrt(num); k++) {
                // 약수가 아니면 스킵
                if (num % k != 0) continue;
                // 약수라면 개수 더하기
                // 제곱근이 아니라면 2개
                if (k * k != num) {
                    count += 2;
                } // 제곱근이라면 1개
                else {
                    count += 1;
                }
                if (count > limit) {
                    count = power;
                    break;
                }
            }
            result += count;
        }
        
        return result;
    }
}