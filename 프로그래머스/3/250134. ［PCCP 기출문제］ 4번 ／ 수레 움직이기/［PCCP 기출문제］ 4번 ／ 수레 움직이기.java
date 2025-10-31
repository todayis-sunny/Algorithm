class Solution {
    // 설정값
    static final int EMPTY = 0, RED_UNIT = 1, BLUE_UNIT = 2, RED_GOAL = 3, BLUE_GOAL = 4, WALL = 5;
    // 4방향 탐색
    static final int[] dx = {-1, 1, 0, 0};
    static final int[] dy = {0, 0, -1, 1};
    // 변수
    static int ans = Integer.MAX_VALUE;
    static int N, M;
    // 방문배열
    static boolean[][] visitedR;
    static boolean[][] visitedB;
    // maze static선언
    static int[][] maze;
    public int solution(int[][] maze) {
        // 열 행 구하기
        N = maze.length;
        M = maze[0].length;
        // 방문 배열 초기화
        visitedR = new boolean[N][M];
        visitedB = new boolean[N][M];
        // maze 초기화
        this.maze = maze;
        // 현재위치
        Node currR = null, currB = null;
        // 현재위치 찾기
        for(int n = 0; n < N; n++) {
            for(int m = 0; m < M; m++) {
                if(maze[n][m] == RED_UNIT) {
                    currR = new Node(n, m);
                } else if(maze[n][m] == BLUE_UNIT) {
                    currB = new Node(n, m);
                }
            }
        }
        // 방문처리
        visitedR[currR.x][currR.y] = true;
        visitedB[currB.x][currB.y] = true;
        dfs(0, currR, currB);
        if (ans == Integer.MAX_VALUE) return 0;
        return ans;
    }
    
    static void dfs(int depth, Node prevR, Node prevB) {
        // 이미 최적의 답을 넘은 경우 종료
        if (depth >= ans) return;
        // 둘다 목표지점에 도달한 경우
        if (maze[prevR.x][prevR.y] == RED_GOAL && maze[prevB.x][prevB.y] == BLUE_GOAL) {
            ans = depth;
            return;
        }
        // 빨간 수레만 도착한 경우 (파란 수레 움직임)
        else if (maze[prevR.x][prevR.y] == RED_GOAL) {
            for(int i = 0; i < 4; i++) {
                int bx = prevB.x + dx[i];
                int by = prevB.y + dy[i];
                // 범위 밖이면 스킵
                if (outOfMaze(bx, by)) continue;
                // 같은 자리면 스킵
                if (prevR.x == bx && prevR.y == by) continue;
                // 벽이면 스킵
                if (maze[bx][by] == WALL) continue;
                // 이미 방문했으면 스킵
                if (visitedB[bx][by]) continue;
                // 위 케이스를 통과하면 진행
                visitedB[bx][by] = true;
                dfs(depth + 1, prevR, new Node(bx, by));
                visitedB[bx][by] = false;
            }
        }
        // 파란 수레만 도착한 경우
        else if (maze[prevB.x][prevB.y] == BLUE_GOAL) {
            for(int i = 0; i < 4; i++) {
                int rx = prevR.x + dx[i];
                int ry = prevR.y + dy[i];
                // 범위 밖이면 스킵
                if (outOfMaze(rx, ry)) continue;
                // 같은 자리면 스킵
                if (prevB.x == rx && prevB.y == ry) continue;
                // 벽이면 스킵
                if (maze[rx][ry] == WALL) continue;
                // 이미 방문했으면 스킵
                if (visitedR[rx][ry]) continue;
                // 위 케이스를 통과하면 진행
                visitedR[rx][ry] = true;
                dfs(depth + 1, new Node(rx, ry), prevB);
                visitedR[rx][ry] = false;
            }
        }
        // 두 수레 모두 도착하지 못한 경우 (4 x 4 탐색) : 어떤 수레가 먼저 움직이는지 고려할 필요가 없음
        else {
            for(int i = 0; i < 16; i++) {
                int rx = prevR.x + dx[i / 4];
                int ry = prevR.y + dy[i / 4];
                int bx = prevB.x + dx[i % 4];
                int by = prevB.y + dy[i % 4];
                // 범위 밖이면 스킵
                if (outOfMaze(rx, ry) || outOfMaze(bx, by)) continue;
                // 같은 자리면 스킵
                if (rx == bx && ry == by) continue;
                // 둘이 자리를 변경하는 거면 스킵 
                if (prevR.x == bx && prevR.y == by && prevB.x == rx && prevB.y == ry) continue;
                // 벽이면 스킵
                if (maze[rx][ry] == WALL || maze[bx][by] == WALL) continue;
                // 이미 방문했으면 스킵
                if (visitedR[rx][ry] || visitedB[bx][by]) continue;
                // 위 케이스를 통과하면 진행
                visitedR[rx][ry] = true;
                visitedB[bx][by] = true;
                dfs(depth + 1, new Node(rx, ry), new Node(bx, by));
                visitedR[rx][ry] = false;
                visitedB[bx][by] = false;
            }
        }
        return;
    }
    
    static boolean outOfMaze(int x, int y) {
        return (x < 0 || x >= N || y < 0 || y >= M);
    }
    
    static class Node {
        int x, y;
        
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}