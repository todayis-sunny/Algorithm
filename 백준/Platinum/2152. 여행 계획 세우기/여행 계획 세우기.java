// 02152. [P2] 여행 계획 세우기

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // N: 도시 수, M: 비행로 수, S: 출발지, T: 도착지
    static int N, M, S, T;
    static ArrayList<ArrayList<Integer>> airway; // 기존 비행로
    static int nodeId = 0, sccCnt = 0;
    static int[] parentId, sccId;
    static boolean[] finished;
    static Stack<Integer> stack = new Stack<>();

    // SCC 그래프
    static ArrayList<ArrayList<Integer>> sccAirway; // SCC 간의 비행로
    static int[] sccSize; // 각 SCC에 속한 도시의 수
    static int[] inDegree; // SCC 그래프의 진입차수 (위상 정렬용)
    static int[] maxCities; // 각 SCC에 도달했을 때 방문 가능한 최대 도시 수

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        airway = new ArrayList<>();
        for (int n = 0; n <= N; n++) {
            airway.add(new ArrayList<>());
        }
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            airway.get(a).add(b);
        }
        parentId = new int[N + 1];
        sccId = new int[N + 1];
        finished = new boolean[N + 1];
    }

    static void solve() {
        // 1. Tarjan 알고리즘으로 모든 SCC를 찾음
        for (int i = 1; i <= N; i++) {
            if (parentId[i] != 0) continue;
            dfs(i);
        }

        // 2. SCC 그래프를 위한 자료구조 초기화
        sccAirway = new ArrayList<>();
        for (int i = 0; i < sccCnt; i++) {
            sccAirway.add(new ArrayList<>());
        }
        sccSize = new int[sccCnt];
        inDegree = new int[sccCnt];
        maxCities = new int[sccCnt];

        // 3. SCC 그래프 생성
        for (int i = 1; i <= N; i++) {
            sccSize[sccId[i]]++; // 각 SCC의 크기(도시 수) 계산
            for (int neighbor : airway.get(i)) {
                // i와 neighbor가 다른 SCC에 속한다면, SCC 그래프에 간선 추가
                if (sccId[i] != sccId[neighbor]) {
                    sccAirway.get(sccId[i]).add(sccId[neighbor]);
                    inDegree[sccId[neighbor]]++; // 도착 SCC의 진입차수 증가
                }
            }
        }

        // 4. 위상 정렬을 위한 큐와 정렬된 순서를 담을 리스트 생성
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> sortedOrder = new ArrayList<>();

        // 진입차수가 0인 모든 SCC를 큐에 추가
        for (int i = 0; i < sccCnt; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // 위상 정렬 실행
        while (!queue.isEmpty()) {
            int currScc = queue.poll();
            sortedOrder.add(currScc); // 정렬된 순서에 추가

            for (int nextScc : sccAirway.get(currScc)) {
                inDegree[nextScc]--;
                if (inDegree[nextScc] == 0) {
                    queue.add(nextScc);
                }
            }
        }

        // 5. 위상 정렬된 순서에 따라 DP 진행
        int startScc = sccId[S];

        // 시작점(S가 속한 SCC)의 DP 값을 자신의 크기로 설정
        maxCities[startScc] = sccSize[startScc];

        // 위상 정렬된 순서대로 DP 값을 전파
        for (int currScc : sortedOrder) {

            // 현재 SCC가 S로부터 도달 불가능한 곳이면(dp값이 0) 더 이상 전파할 수 없음
            if (maxCities[currScc] == 0) continue;

            // 현재 SCC에서 갈 수 있는 다음 SCC들의 DP 값을 갱신
            for (int nextScc : sccAirway.get(currScc)) {
                maxCities[nextScc] = Math.max(maxCities[nextScc], maxCities[currScc] + sccSize[nextScc]);
            }
        }
    }

    static void output() throws IOException {
        // 도착지(T)가 속한 SCC까지의 최대 도시 수를 출력
        bw.write(String.valueOf(maxCities[sccId[T]]));
        bw.flush();
        bw.close();
    }

    static int dfs(int x) {
        int parent = parentId[x] = ++nodeId;
        stack.push(x);

        for (Integer y : airway.get(x)) {
            if (parentId[y] == 0) { // 방문하지 않은 이웃
                parent = Math.min(parent, dfs(y));
            } else if (!finished[y]) { // 처리중인 이웃
                parent = Math.min(parent, parentId[y]);
            }
        }
        if (parent == parentId[x]) { // 부모 노드가 자기 자신인 경우
            while (true) {
                int p = stack.pop();
                finished[p] = true;
                sccId[p] = sccCnt;
                if (p == x) break;
            }
            sccCnt++;
        }
        return parent;
    }
}
