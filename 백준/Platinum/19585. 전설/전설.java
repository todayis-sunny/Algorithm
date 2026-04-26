// 19585. [P3] 전설.

import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int C, N, Q;
    static Trie colorTrie;
    static HashSet<String> nicknameSet;
    static String[] query;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        colorTrie = new Trie();
        nicknameSet = new HashSet<>();
        for (int c = 0; c < C; c++) {
            String word = br.readLine();
            colorTrie.insert(word);
        }
        for (int n = 0; n < N; n++) {
            String word = br.readLine();
            nicknameSet.add(word);
        }
        Q = Integer.parseInt(br.readLine());
        query = new String[Q];
        for (int q = 0; q < Q; q++) {
            String team = br.readLine();
            query[q] = team;
        }
    }

    static void solve() {
        for (int q = 0; q < Q; q++) {
            String team = query[q];
            if (colorTrie.search(team)) {
                sb.append("Yes\n");
            } else {
                sb.append("No\n");
            }
        }
    }

    static void output() throws IOException {
        bw.write(sb.toString());
        bw.flush();
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        void insert(String word) {
            Node node = this.root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                if (node.child[ch - 'a'] == null) {
                    node.child[ch - 'a'] = new Node();
                }
                node = node.child[ch - 'a'];
            }
            node.isEnd = true;
        }

        boolean search(String team) {
            Node curr = colorTrie.root;
            for (int i = 0; i < team.length(); i++) {
                char ch = team.charAt(i);
                int idx = ch - 'a';

                // Trie 탐색이 막히면 더 이상 색상 이름이 될 수 없음
                if (curr.child[idx] == null) break;

                curr = curr.child[idx];

                // 1. 여기까지가 유효한 색상 이름(isEnd)인지 확인
                // 2. 그렇다면 나머지 뒷부분이 닉네임셋에 있는지 확인
                if (curr.isEnd) {
                    // substring 하나만 사용하여 닉네임 존재 여부 체크
                    if (nicknameSet.contains(team.substring(i + 1))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    static class Node {
        Node[] child;
        boolean isEnd;

        public Node() {
            this.child = new Node[26];
            this.isEnd = false;
        }
    }
}
