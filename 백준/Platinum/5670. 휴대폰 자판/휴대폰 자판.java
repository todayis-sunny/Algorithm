// 05670. [P4] 휴대폰 자판.

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static Tire trie;

    public static void main(String[] args) throws IOException {
        input();
    }

    static void input() throws IOException {
        String str;
        while ((str = br.readLine()) != null) {
            if (str.isEmpty()) {
                break;
            }
            N = Integer.parseInt(str);
            List<String> list = new ArrayList<String>();
            trie = new Tire();
            double ans = 0.0;
            for (int n = 0; n < N; n++) {
                String word = br.readLine();
                list.add(word);
                trie.insert(word);
            }
            for (String word : list) {
                ans += trie.search(word);
            }
            bw.write(String.format("%.2f\n", ans / N));
        }
        bw.flush();
    }

    static class Node {
        HashMap<Character, Node> child;
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }

    static class Tire {
        Node root;

        public Tire() {
            this.root = new Node();
        }

        public void insert(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);

                node.child.putIfAbsent(ch, new Node());

                node = node.child.get(ch);
            }
            node.endOfWord = true;
        }

        public int search(String str) {
            if (str.length() == 1) {
                return 1;
            }
            Node node = this.root;
            int count = 1;
            char ch = str.charAt(0);
            node = node.child.get(ch);
            for (int i = 1; i < str.length(); i++) {
                ch = str.charAt(i);
                if (node.endOfWord || node.child.size() > 1) {
                    count++;
                }
                node = node.child.get(ch);
            }
            return count;
        }
    }
}
