// 17143. [G1] 낚시왕.
import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int R, C, M;
    static int time, ans;
    static HashMap<Integer, Shark> sea;
    static int fishingNear; // 잡으려는 상어의 위치 Y
    static int cycleRow, cycleCol;

    /*
    1. 낚시왕이 오른쪽으로 한 칸 이동한다.
        -> 반복문으로 해결
    2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
        -> moreNear함수
    3. 상어가 이동한다.
        -> eat함수와 move함수 사용, sea : hashMap 활용

    1 -> 2 -> 3 순이지만, 마지막 3은 발생 하나마나다.
    입력부분을 3이라 생각하고 3 -> 1 -> 2로 로직을 마무리한다. 3의 과정을 통해 낚시왕이 잡을 상어를 미리 찾는다.
    */


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = 1;
        initCycle();
        repeatedInit();
        sea = new HashMap<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sea.put(conversionXY(r, c), new Shark(s, d, z)); // 3번 실행 : 이동했다고 생각하고
            repeatedFishing(conversionXY(r, c)); // 1s에서 잡을 상어를 찾음
        }
        for (int c = 1; c <= C; c++) {
            repeatedHarvest();
            repeatedInit();
            time++;
            simulation();
        }
        bw.write(String.valueOf(ans));
        bw.flush();

    }

    /**
     * 상어 시뮬레이션 이동 및 먹기, 어떤 상어를 낚시할지 탐지
     */
    static void simulation() {
        HashMap<Integer, Shark> newSea = new HashMap<>();
        for (Integer key : sea.keySet()) {
            Shark currShark = sea.get(key);
            int nextKey = move(key, currShark); // 다음 위치 받아옴
            Shark prevShark = newSea.putIfAbsent(nextKey, currShark);
            if (prevShark != null) {
                newSea.put(nextKey, eat(prevShark, currShark)); // 이긴 상어가 자리를 차지함.
            }
            repeatedFishing(nextKey); // 계속해서 어떤 상어를 잡을 수 있는지 탐지.
        }
        sea = newSea; // 바다 지칭을 다시 옮겨줌.
    }

    /**
     * row, col 별 주기 구하기
     */
    static void initCycle() {
        cycleRow = 2 * (R - 1);
        cycleCol = 2 * (C - 1);
    }

    static int conversionXY(int x, int y) {
        return 1001 * x + y;
    }

    static int conversionX(int xy) {
        return xy / 1001;
    }

    static int conversionY(int xy) {
        return xy % 1001;
    }

    /**
     * 반복문 한번씩 돌때 마다 초기화
     */
    static void repeatedInit() {
        fishingNear = R + 1; // 제일 멀리 있는것으로 측정
    }

    static void repeatedFishing(int xy) {

        int x = conversionX(xy);
        int y = conversionY(xy);
        if (y != time) {
            return;
        } else {
            fishingNear = Math.min(fishingNear, x);
        }
    }

    /**
     * 유효성을 검증하고 수확하기
     */
    static void repeatedHarvest() {
        if (fishingNear == R + 1) { // 못 찾았으므로 종료
            return;
        }
        int xy = conversionXY(fishingNear, time);
        Shark shark = sea.get(xy);
        ans += shark.size;
        sea.remove(xy);
    }

    /**
     * 상어 이동위치 반환
     */
    static int move(int xy, Shark shark) {
        int x = conversionX(xy);
        int y = conversionY(xy);
        int nx = 0, ny = 0, mv; // nx, ny : 다음 위치 | mv : 주기 0으로 치환한 위치
        switch (shark.direction) {
            case 1: // 상
                nx = x;
                ny = y;
                mv = shark.speed % cycleRow;
                if (mv > 0) {
                    // 첫번째 부딪히기
                    if (mv > nx - 1) { // 더 가야함
                        mv -= (nx - 1);
                        nx = 1;
                    } else {
                        nx -= mv;
                        mv = 0;
                    }
                    if (mv == 0) {
                        break;
                    }
                    // 두번째 부딪히기
                    if (mv > R - 1) { // 더 가야함
                        mv -= (R - 1);
                        nx = R;
                    } else {
                        nx += mv;
                        mv = 0;
                        shark.direction = 2;
                    }
                    // 마지막 처리하기
                    nx -= mv;
                }

                break;
            case 2: // 하
                nx = x;
                ny = y;
                mv = shark.speed % cycleRow;
                if (mv > 0) {
                    // 첫번째 부딪히기
                    if (mv > R - nx) { // 더 가야함
                        mv -= (R - nx);
                        nx = R;
                    } else {
                        nx += mv;
                        mv = 0;
                    }
                    if (mv == 0) {
                        break;
                    }
                    // 두번째 부딪히기
                    if (mv > nx - 1) { // 더 가야함
                        mv -= (nx - 1);
                        nx = 1;

                    } else {
                        nx -= mv;
                        mv = 0;
                        shark.direction = 1;
                    }
                    // 마지막 처리하기
                    nx += mv;
                }
                break;

            case 3: // 우
                nx = x;
                ny = y;
                mv = shark.speed % cycleCol;
                if (mv > 0) {
                    // 첫번째 부딪히기
                    if (mv > C - ny) {
                        mv -= (C - ny);
                        ny = C;
                    } else {
                        ny += mv;
                        mv = 0;
                    }
                    if (mv == 0) {
                        break;
                    }
                    // 두번째 부딪히기
                    if (mv > ny - 1) { // 더 가야함
                        mv -= (ny - 1);
                        ny = 1;
                    } else {
                        ny -= mv;
                        mv = 0;
                        shark.direction = 4;
                    }
                    // 마지막 처리하기
                    ny += mv;
                }
                break;


            case 4: // 우
                nx = x;
                ny = y;
                mv = shark.speed % cycleCol;
                if (mv > 0) {
                    // 첫번째 부딪히기
                    if (mv > ny - 1) {
                        mv -= (ny - 1);
                        ny = 1;
                    } else {
                        ny -= mv;
                        mv = 0;
                    }
                    if (mv == 0) {
                        break;
                    }
                    // 두번째 부딪히기
                    if (mv > C - 1) { // 더 가야함
                        mv -= (C - 1);
                        ny = C;
                    } else {
                        ny += mv;
                        mv = 0;
                        shark.direction = 3;
                    }
                    // 마지막 처리하기
                    ny -= mv;
                }

                break;

        }
        return conversionXY(nx, ny);
    }


    /**
     * 누가 먹는 상어인가
     */
    static Shark eat(Shark shark1, Shark shark2) {
        if (shark1.size > shark2.size) {
            return shark1;
        } else {
            return shark2;
        }
    }

    static class Shark {
        int speed;
        int direction;
        int size;

        public Shark(int speed, int direction, int size) {
            this.speed = speed;
            this.direction = direction;
            this.size = size;
        }
    }


}

