// 05052. [G4] 전화번호 목록.

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int TC, N;
    static boolean flagAns;
    static ArrayList<String> addressBook;

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < TC; tc++) {
            N = Integer.parseInt(br.readLine());
            flagAns = true;
            Trie trie = new Trie();
            addressBook = new ArrayList<>();
            for (int n = 0; n < N; n++) {
                String phoneNumber = br.readLine();
                addressBook.add(phoneNumber);
                if (!flagAns) {
                    continue;
                }
                if (!trie.insert(phoneNumber)) {
                    flagAns = false;
                }
            }

            if (flagAns) {
                bw.write("YES");
            } else {
                bw.write("NO");
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    static class Node {
        // 각 노드의 자식노드 저장
        HashMap<Character, Node> child;
        // 현재 노드가 단어의 끝인지 저장
        boolean endOfWord;

        public Node() {
            this.child = new HashMap<>();
            this.endOfWord = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        /**
         * 삽입 메서드
         */
        public boolean insert(String str) {
            // 시작 노드를 루트노드로 설정
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (node.endOfWord) {
                    return false;
                }
                // 문자열의 각 단어를 가져와서 자식노드 중에 있는지 체크
                node.child.putIfAbsent(ch, new Node());

                // 있을 때 : node = node.child.get(str.charAt(i)); putIfAbsent는 값 존재시에 value를 반환
                // 없을 때 : 새로운 노드를 생성해서 넣음
                node = node.child.get(ch);
            }
            // 마지막 문자열의 문자를 넣을 때, 일관성이 없는 목록인지 체크.
            if (!node.child.isEmpty()) {
                // 현재 단어가 다른 단어의 접두어인 경우
                return false;
            }
            node.endOfWord = true;
            return true;
        }


    }

}
