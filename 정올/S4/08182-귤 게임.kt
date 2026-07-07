// [S4] 08182. 귤 게임
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var tangerineBox: IntArray
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. 귤 입력
            st = StringTokenizer(readln())
            tangerineBox = IntArray(N) { st.nextToken().toInt() }
        }

        fun solve() {
            // 1. 맨 앞에서부터 연속된 1의 개수를 파악
            // 1은 선택지가 없어서 턴만 넘기는 역할
            var cnt = 0
            while (cnt < N && tangerineBox[cnt] == 1) {
                cnt++
            }
            // 2. 정올이가 이기는지 여부
            val jungolWin = if (cnt == N) {
                // a. 전부 1이면 홀수일 때 정올이 이김
                N % 2 == 1
            } else {
                // b. 처음 만나는 2이상 박스를 잡는 사람이 정올이라면 이김
                cnt % 2 == 0
            }
            sb.append(if (jungolWin) "J" else "H")
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
