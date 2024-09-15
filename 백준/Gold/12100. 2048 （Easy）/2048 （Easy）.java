// 12100. [G2] 2048 (Easy).
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int n;
    public static int ans;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static int[][] board;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        board= new int[n][n];
        StringTokenizer st = null;
        for(int i=0;i<n;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        bfs();
        bw.write(String.valueOf(ans));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void move(int[][] map, int dir){
        boolean[][] visited = new boolean[n][n];

        int start = 0, end = 0, c = 0;
        if(dir == 0 || dir == 3) {
            start = 0;
            end = n;

            for(int j=start;j<end;j++) {
                for(int i=start;i<end;i++) {

                    if(map[i][j] == 0) continue;
                    int x = i;
                    int y = j;
                    int temp = map[x][y];
                    map[x][y] = 0;
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    while(true) {

                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                            break;
                        }

                        if(map[nx][ny] == 0) {
                            x = nx;
                            y = ny;
                            nx = x + dx[dir];
                            ny = y + dy[dir];
                        } else if(!visited[nx][ny] && map[nx][ny] == temp) {
                            x = nx;
                            y = ny;
                            visited[nx][ny] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                    map[x][y] += temp;
                }
            }
        } else {
            start = n-1;
            end = -1;

            for(int i=start;i>end;i--) {
                for(int j=start;j>end;j--) {

                    if(map[i][j] == 0) continue;
                    int x = i;
                    int y = j;
                    int temp = map[x][y];
                    map[x][y] = 0;
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];

                    while(true) {

                        if(nx < 0 || ny < 0 || nx >= n || ny >= n) {
                            break;
                        }


                        if(map[nx][ny] == 0) {
                            x = nx;
                            y = ny;
                            nx = x + dx[dir];
                            ny = y + dy[dir];

                        } else if(!visited[nx][ny] && map[nx][ny] == temp) {
                            x = nx;
                            y = ny;
                            visited[nx][ny] = true;
                            break;
                        } else {
                            break;
                        }
                    }
                    map[x][y] += temp;
                }
            }
        }



    }

    public static void bfs() {
        Queue<int[][]> q = new LinkedList<>();
        q.offer(board);
        int cnt = 0;
        while(!q.isEmpty()) {
            int q_len = q.size();
            for(int l=0;l<q_len;l++) {
                int[][] map = q.poll();

                for(int d=0;d<4;d++) {
                    int[][] map_copy = new int[n][n];
                    for(int i=0;i<n;i++) {
                        for(int j=0;j<n;j++) {
                            map_copy[i][j] = map[i][j];
                        }
                    }

                    move(map_copy, d);


                    q.offer(map_copy);
                    for(int i=0;i<n;i++) {
                        for(int j=0;j<n;j++) {
                            ans = Math.max(ans, map_copy[i][j]);
                        }
                    }

                }

            }

            cnt++;
            if(cnt >= 5) {
                return;
            }
        }


    }

}