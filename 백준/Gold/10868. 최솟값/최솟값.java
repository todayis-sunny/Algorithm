// 10868. [G1] 최솟값.
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int[] element;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        SegmentTree segmentTree = new SegmentTree(N);
        element = new int[N+1];
        for (int n = 1; n <= N; n++) {
            element[n] = Integer.parseInt(br.readLine());
        }
        segmentTree.init(1, N, 1);

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            bw.write(segmentTree.getMin(1, N, 1, from, to) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }

    public static class SegmentTree{
        int[] tree;
        int treeSize;

        public SegmentTree(int arrSize){
            int h = (int)Math.ceil(Math.log(arrSize)/Math.log(2));
            this.treeSize = (int)Math.pow(2, h+1);
            tree = new int[treeSize];
        }
        public int init(int start, int end, int node){
            if(start == end) {
                return tree[node] = element[start];
            }
            int mid = (start + end) / 2;

            return tree[node] = Math.min(
                    init(start, mid, node * 2),
                    init(mid+1, end, node * 2 + 1)
            );
        }
        public int getMin(int start, int end, int node, int from, int to) {
            if(from > end || to < start) { // 범위 밖인 경우,
                return Integer.MAX_VALUE;
            }
            if(from <= start && to >= end) { // 범위 안인 경우,
                return tree[node];
            }
            // 두 경우 다 해당하지 않는다면, 두 부분으로 나눠서 최솟값 구하기.
            int mid = (start + end) / 2;
            return Math.min(getMin(start, mid, node*2, from, to), getMin(mid+1, end, node*2 + 1, from, to));
        }
    }
}