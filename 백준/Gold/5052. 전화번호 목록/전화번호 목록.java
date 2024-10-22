// 05052. [G4] 전화번호 목록.
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
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
                for (int n = 0; n < N; n++) {
                    String phoneNumber = addressBook.get(n);
                    if(!trie.search(phoneNumber)) {
                        flagAns = false;
                        break;
                    }
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
                if(node.child.putIfAbsent(ch, new Node()) != null && i == str.length()-1) {
                    return false;
                }
                // 있을 때 : node = node.child.get(str.charAt(i)); putIfAbsent는 값 존재시에 value를 반환
                // 없을 때 : 새로운 노드를 생성해서 넣음
                node = node.child.get(ch);
            }
            node.endOfWord = true;
            return true;
        }

        /**
         * 탐색 메서드
         */
        public boolean search(String str) {
            Node node = this.root;

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                // 자식노드에 ch가 있을 때 계속 탐색
                if (node.child.containsKey(ch)) {
                    node = node.child.get(ch);
                } else {
                    return false;
                }
            }
            // 마지막 노드까지 도달 시, 마지막 노드의 endOfWord를 반환
            return node.endOfWord;
        }

        /**
         * 삭제 메서드
         */
        public boolean delete(String str) {
            return delete(this.root, str, 0);
        }

        /**
         * 내부적으로 재귀를 통해 삭제하는 메서드
         */
        private boolean delete(Node node, String str, int idx) {
            char ch = str.charAt(idx);

            // 현재 노드의 자식노드에서 ch를 지워야하는데 없으면 false 반환
            if (!node.child.containsKey(ch)) {
                return false;
            }

            Node curr = node.child.get(ch);
            idx++;
            // 문자열 끝에 도달했을 때
            if (idx == str.length()) {
                if (!curr.endOfWord) {
                    return false;
                }
                // endOfWord를 flase로 바꿔주면 지우려는 문자열을 찾을 수 없게 됨
                curr.endOfWord = false;

                // 지우려는 문자열의 마지막에서 더 뻗어나가는 경우
                if (curr.child.isEmpty()) {
                    node.child.remove(ch);
                }
            } else { // 문자열 끝에 도달하지 않았을 때
                // 재귀적으로 현재 노드부터 다시 호출
                if (!this.delete(curr, str, idx)) { // 삭제에 실패했을 경우
                    return false;
                }
                // 삭제에 성공했고, 자식노드가 비어 있으면 현재 노드를 삭제
                // node는 curr의 부모노드임. 즉, curr노드를 node의 자식 Map에서 삭제하는 것
                if (!curr.endOfWord && curr.child.isEmpty()) {
                    node.child.remove(ch);
                }
            }
            return true;
        }


    }

}
