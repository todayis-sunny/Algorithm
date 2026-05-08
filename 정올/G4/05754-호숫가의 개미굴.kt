// [G4] 05754. 호숫가의 개미굴

fun main() {
    class Solution {
        val sb = StringBuilder()
        var N = 0
        var ans = 0L
        lateinit var anthill: LongArray

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. 배열 초기화
            anthill = LongArray(N)
            // 3. 입력
            readln().split(" ").map { it.toLong() }.forEachIndexed { i, cnt ->
                anthill[i] = cnt
            }
        }

        fun solve() {
            // 1. 양끝의 0개수 파악
            // 1-1. 좌측 먼저
            val left = anthill.takeWhile { it == 0L }.count()
            // 1-2. 우측
            var right = 0
            if (left < N) {
                right = anthill.takeLastWhile { it == 0L }.count()
                ans += (left + right + 1) / 2
            } else {
                ans += left / 2
            }

            // 2. 가운데 파악
            var count = 1 // 중복되는 0의 개수, 기본 1
            for (i in left until N - right) {
                if (anthill[i] != 0L) {
                    ans += anthill[i]
                    ans += count / 2
                    count = 1
                } else {
                    count++
                }
            }
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }

    }
    Solution().execute()
}
