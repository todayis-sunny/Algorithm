// [G2] 01946. 음악프로그램
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0 // 가수들의 수
        var M = 0 // 보조 PD들의 수
        lateinit var singerArr: Array<IntArray> // idx 0: 진입차수, idx n: 이후 가수
        val queue = ArrayDeque<Int>()
        var sing = 0 // 노래 부른 횟수


        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, M 입력
            readln().split(" ").map { it.toInt() }.apply {
                N = this[0]
                M = this[1]
            }
            singerArr = Array(N + 1) { IntArray(N + 1) }
            // 2. 가수들 순서 입력
            repeat(M) {
                st = StringTokenizer(readln())
                val cnt = st.nextToken().toInt()
                val backStage = IntArray(cnt) {
                    st.nextToken().toInt()
                }
                // 2-1. 진입차수 설정
                for (i in 0 until cnt - 1) {
                    val before = backStage[i]
                    val after = backStage[i + 1]
                    singerArr[after][0]++ // 진입차수 증가
                    singerArr[before][after]++ // 이후 가수
                }
            }
        }

        fun solve() {
            // 1. 진입차수 0인 가수들 넣기
            for (before in 1..N) {
                if (singerArr[before][0] == 0) {
                    singerArr[before][0] = -1
                    queue.addLast(before)
                }
            }
            // 2. 가수들 노래하기
            while (queue.isNotEmpty()) {
                val before = queue.removeFirst()
                sb.append(before).append("\n")
                sing++
                for (after in 1..N) {
                    singerArr[after][0] -= singerArr[before][after]
                    if (singerArr[after][0] == 0) {
                        singerArr[after][0] = -1
                        queue.addLast(after)
                    }
                }
            }
        }

        fun output() {
            if (sing == N) {
                println(sb)
            } else{
                println(0)
            }
        }
    }
    Solution().execute()
}

