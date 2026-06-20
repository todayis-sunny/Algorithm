// [G2] 02641. 택배
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0 // 마을 수
        var C = 0 // 트력의 용량
        var M = 0 // 박스 정보의 개수
        lateinit var village: Array<IntArray> // 1-based | [f][t] = 박수개수 | f: 싣는 마을, t 받는 마을
        lateinit var delivery: IntArray // 싣을 박수 개수
        lateinit var receive: IntArray // 받을 박수 개수
        var ans = 0 // 정답

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, C 입력
            readln().split(" ").map { it.toInt() }.apply {
                N = this[0]
                C = this[1]
            }
            village = Array(N + 1) { IntArray(N + 1) }
            delivery = IntArray(N + 1)
            receive = IntArray(N + 1)
            // 2. M입력
            M = readln().toInt()
            repeat(M) {
                st = StringTokenizer(readln())
                val from = st.nextToken().toInt()
                val to = st.nextToken().toInt()
                val box = st.nextToken().toInt()
                village[from][to] = box
            }
        }

        fun solve() {
            // 1. 시뮬레이션
            for (t in 2..N) { // a. 받는 마을 순회
                var truck = 0
                for (f in 1 until t) { // b. 싣는 마을 순회
                    truck += delivery[f]
                    truck -= receive[f]
                    var box = village[f][t] // 박스 개수
                    // 싣을 박스가 최대 용량 이상인 경우
                    if (truck + box >= C) {
                        box = C - truck
                    }
                    delivery[f] += box
                    receive[t] += box
                    truck += box
                }
                ans += receive[t]
            }
            // 2. 정답처리
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
