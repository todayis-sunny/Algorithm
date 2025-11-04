import java.util.*;

class Solution {
    // 판매원 id(인덱스)
    static Map<String, Integer> enrollId = new HashMap<>();
    // 추천인 (-1 : 없음)
    static int[] parent;
    // 결과
    static int[] result;
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int size = enroll.length;
        // 결과 초기화
        result = new int[size];
        // 추천인 초기화
        parent = new int[size];
        Arrays.fill(parent, -1);
        // 판매원 id 넣기
        for (int id = 0; id < size; id++) {
            enrollId.put(enroll[id], id);    
        }
        // 추천인 넣기
        for (int id = 0; id < size; id++) {
            // 추천인이 없으면 스킵
            if (referral[id].equals("-")) continue;
            // 추천인 있으면 삽입
            parent[id] = enrollId.get(referral[id]);
        }
        // 판매원 순회
        for (int i = 0; i < seller.length; i++) {
            dfs(enrollId.get(seller[i]), amount[i] * 100);
        }
        
        return result;
    }
    
    public void dfs(int id, int value) {
        // 센터까지 온 경우 종료
        if (id == -1) {
            return;
        }
        int parentValue = value / 10;
        result[id] += value - parentValue;
        dfs(parent[id], parentValue);
    }
}