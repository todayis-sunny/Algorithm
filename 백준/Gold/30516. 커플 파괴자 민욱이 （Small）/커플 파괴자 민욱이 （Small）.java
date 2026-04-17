// 30516. [G4] 커플 파괴자 민욱이 (Small)

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int N;
    static int minBlock;
    static int[] people;
    static boolean[] mainBlock;  // 크기 N-1 추천. i~i+1 사이
    static boolean[] tempBlock;

    // 재귀 탐색용
    static boolean foundAnswer;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        people = new int[N];

        // i~i+1 사이 관리 (N-1)
        mainBlock = new boolean[N - 1];
        for (int i = 0; i < N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        solve();
        bw.flush();
    }

    static void solve() throws IOException {
        initBlock();

        // d = 추가로 막을 개수
        for (int d = 0; d <= (N - 1) - minBlock; d++) {
            tempBlock = mainBlock.clone();
            foundAnswer = false;
            selectBlock(d, 0, 0);
            if (foundAnswer) return; // 성공하면 종료
        }
        bw.write("-1\n");
    }

    static void initBlock() {
        minBlock = 0;
        for (int i = 0; i < N - 1; i++) {
            if (people[i] == people[i + 1] && people[i] != 0) {
                mainBlock[i] = true;
                minBlock++;
            }
        }
    }

    // d개를 추가 차단
    static void selectBlock(int limit, int depth, int start) throws IOException {
        if (foundAnswer) return; // 이미 성공

        if (depth == limit) {
            // 여기서 isSuccess 검사
            if (isSuccess()) {
                foundAnswer = true;
            }
            return;
        }
        for (int i = start; i < (N - 1); i++) {
            if (mainBlock[i]) continue;
            tempBlock[i] = true;
            selectBlock(limit, depth + 1, i + 1);
            tempBlock[i] = false;
            if (foundAnswer) return;
        }
    }

    static boolean isSuccess() throws IOException {
        // 1) 그룹 생성
        List<Group> groups = new ArrayList<>();
        int front = 0;
        for (int i = 0; i < N - 1; i++) {
            if (tempBlock[i]) {
                groups.add(new Group(i - front + 1, people[front], people[i]));
                front = i + 1;
            }
        }
        // 마지막 그룹
        groups.add(new Group((N - 1) - front + 1, people[front], people[N - 1]));

        int gCount = groups.size();
        boolean[] used = new boolean[gCount];
        int[] path = new int[gCount];

        // 2) 순열을 전부 저장하지 않고, "DFS로 하나씩 만들어 확인"
        return checkPermutationDFS(0, gCount, groups, used, path);
    }

    // depth: 몇 번째 그룹을 배치할지
    // gCount: 그룹 개수
    // groups: 그룹 목록
    // used[i]: i번째 그룹을 이미 사용?
    // path[depth]: 그룹 인덱스
    static boolean checkPermutationDFS(int depth, int gCount, List<Group> groups, boolean[] used, int[] path) throws IOException {
        if (depth == gCount) {
            // 순열 완성 -> 인접 그룹 tail==head &&!=0 없으면 success
            // 근데 여기서는 이미 만들면서 검사해도 됨
            // 여기서는 완성 후 한번에 검사
            return outputIfSuccess(path, groups);
        }

        for (int i = 0; i < gCount; i++) {
            if (!used[i]) {
                // 현재 path에 i그룹 배치
                path[depth] = i;
                // 인접 조건 체크(직전에 배치한 그룹과 이어붙였을 때 커플인가?)
                if (depth > 0) {
                    Group prevG = groups.get(path[depth - 1]);
                    Group currG = groups.get(path[depth]);
                    if (prevG.tail == currG.head && prevG.tail != 0) {
                        // 이어붙이면 커플 -> 실패, 가지치기
                        continue;
                    }
                }
                used[i] = true;
                // 다음 단계
                if (checkPermutationDFS(depth + 1, gCount, groups, used, path)) {
                    return true; // 성공을 찾았다면 즉시 true
                }
                used[i] = false;
            }
        }
        return false; // 이 경로로는 성공 못 찾음
    }

    // 완성된 순열 path[]가 인접 커플 연결이 전혀 없으므로 성공. 출력 후 true
    static boolean outputIfSuccess(int[] path, List<Group> groups) throws IOException {
        bw.write(groups.size() + "\n");
        for (Group g : groups) {
            bw.write(g.size + " ");
        }
        bw.newLine();
        // path는 그룹 인덱스(0-based)
        // 문제 요구에 맞게 1-based나 그대로 출력
        for (int idx : path) {
            bw.write((idx + 1) + " ");
        }
        bw.newLine();
        return true;
    }

    static class Group {
        int size;
        int head, tail;
        Group(int sz, int h, int t) {
            size = sz; head = h; tail = t;
        }
    }
}