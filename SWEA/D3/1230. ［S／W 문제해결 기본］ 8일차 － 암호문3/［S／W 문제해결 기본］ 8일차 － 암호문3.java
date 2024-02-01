// 01230. [Diff_03]암호문3
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution {
    static int NODE_MAX = 5000;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;
        Node tail;
        Node[] nodeArr;
        int nodeCnt;

        public LinkedList() {
            head = null;
            nodeArr = new Node[NODE_MAX];
            nodeCnt = 0;
        }

        Node getNewNode(int data) {
            nodeArr[nodeCnt] = new Node(data);
            return nodeArr[nodeCnt++];
        }

        void insert(int idx, int[] nums) {
            int st = 0;
            if (idx == 0) {
                if (head != null) {
                    Node newNode = getNewNode(nums[0]);
                    newNode.next = head;
                    head = newNode;
                } else {
                    head = getNewNode(nums[0]);
                }
                idx = 1;
                st = 1;
            }

            Node cur = head;
            for (int i = 1; i < idx; i++) {
                cur = cur.next;
            }
            for (int i = st; i < nums.length; i++) {
                Node newNode = getNewNode(nums[i]);
                newNode.next = cur.next;
                cur.next = newNode;
                cur = newNode;
            }
            if (cur.next == null) {
                tail = cur;
            }
        }

        void delete(int idx, int cnt) {
            Node cur = head;
            if (idx == 0) {
                for (int i = 0; i < cnt; i++) {
                    cur = cur.next;
                }
                head = cur;
                return;
            }

            for (int i = 1; i < idx; i++) {
                cur = cur.next;
            }

            Node anchor = cur;
            for (int i = 0; i < cnt; i++) {
                cur = cur.next;
            }

            anchor.next = cur.next;

            if (anchor.next == null) {
                tail = anchor;
            }
        }

        void add(int data) {
            Node cur = tail;
            Node newNode = getNewNode(data);
            cur.next = newNode;
            tail = newNode;
        }

        void print() throws Exception {
            int cnt = 10;
            Node cur = head;
            while (--cnt >= 0) {
                bw.write(cur.data + " ");
                cur = cur.next;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        int TC = 10;
        StringTokenizer st;
        for (int tc = 1; tc <= TC; tc++) {
            LinkedList list = new LinkedList();
            bw.write("#");
            bw.write(tc + " ");
            int N = Integer.parseInt(br.readLine());
            int[] initArr = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                initArr[i] = Integer.parseInt(st.nextToken());
            }
            list.insert(0, initArr);

            int M = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                char cmd = st.nextToken().charAt(0);
                int x, y;
                switch (cmd) {
                    case 'I':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        int[] temp = new int[y];
                        for (int j = 0; j < y; j++) {
                            temp[j] = Integer.parseInt(st.nextToken());
                        }
                        list.insert(x, temp);
                        break;
                    case 'D':
                        x = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());
                        list.delete(x, y);
                        break;
                    case 'A':
                        y = Integer.parseInt(st.nextToken());
                        for (int j = 0; j < y; j++) {
                            list.add(Integer.parseInt(st.nextToken()));
                        }
                        break;
                }
            }
            list.print();
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }

}
