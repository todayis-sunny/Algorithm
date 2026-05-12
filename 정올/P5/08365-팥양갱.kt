// [P5] 08365. 팥양갱

fun main() {
    class Solution {
        val INF = Int.MAX_VALUE
        var N = 0
        lateinit var length: IntArray // 1-based
        var ans = INF

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            length = IntArray(N + 1)
            // 2. 길이 입력
            repeat(N) {
                length[it + 1] = readln().toInt()
            }
        }

        fun solve() {
            // 1. 모든구간 [left, right]를 탐색
            for (left in 1..N) {
                var low = 0
                for (right in left..N) {
                    low += length[right]
                    ans = minOf(ans, find(low))
                }
            }
        }


        fun output() {
            println(ans)
        }

        fun find(low: Int): Int {
            // dp[i] = v : i번째 조각까지 봤을때, 지금까지 조각들 중 가장 긴 조각의 최솟값
            val dp = IntArray(N + 1) { INF }
            dp[0] = low
            // 조각의 마지막 인덱스
            for (last in 1..N) {
                var pieceLength = 0
                for (first in last downTo 1) {
                    pieceLength += length[first]
                    // 전체 구간을 본 경우 (절취를 하지 않은 경우) 스킵
                    if (last == N && first == 1) continue
                    // 최소 길이 이상을 만족하지 못한 경우 스킵
                    if (pieceLength < low) continue
                    // 최소 길이 이상을 만족한 경우
                    val high = maxOf(pieceLength, dp[first - 1])
                    dp[last] = minOf(dp[last], high)
                }
            }

            return dp[N] - low
        }
    }
    Solution().execute()
}
