// 02179. [G4] 비슷한 단어.

import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int limit;
    static Set<String> set = new HashSet<>();
    static List<Word> words = new ArrayList<>();
    static WordPair wordPair = new WordPair(-1, new Word(""), new Word(""));

    public static void main(String[] args) throws IOException {
        input();
        solve();
        output();
    }

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        for (int n = 0; n < N; n++) {
            String word = br.readLine();
            if (set.contains(word)) continue;
            set.add(word);
            words.add(new Word(word));
        }
        limit = set.size();
    }

    static void solve() {
        for (int i = 0; i < limit - 1; i++) {
            Word word1 = words.get(i);
            if (word1.data.length() < wordPair.matched) continue;
            for (int j = i + 1; j < limit; j++) {
                Word word2 = words.get(j);
                if (word2.data.length() < wordPair.matched) continue;
                int matchPair = word1.getMatchPair(word2);
                if (matchPair <= wordPair.matched) continue;
                wordPair = new WordPair(matchPair, word1, word2);
            }
        }
    }

    static void output() throws IOException {
        bw.write(wordPair.word1.data + "\n");
        bw.write(wordPair.word2.data + "\n");
        bw.flush();
    }

    static class WordPair {
        int matched;
        Word word1, word2;

        public WordPair(int matched, Word word1, Word word2) {
            this.matched = matched;
            this.word1 = word1;
            this.word2 = word2;
        }
    }

    static class Word {
        String data;


        public Word(String data) {
            this.data = data;
        }

        int getMatchPair(Word word) {
            int limit = Math.min(this.data.length(), word.data.length());
            for (int i = 0; i < limit; i++) {
                if (this.data.charAt(i) != word.data.charAt(i)) return i;
            }
            return limit;
        }
    }
}
