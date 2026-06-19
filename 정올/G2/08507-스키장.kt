// [G2] 08507. 스키장

fun main() {
    Solution().execute()
}

private class Solution {
    val sb = StringBuilder()
    var N = 0 // 중간 지점의 개수
    var M = 0 // 코스의 개수
    var K = 0 // 스키 리프트 최대 탑승횟수
    var S = 0 // 출발 지점
    var T = 0 // 목적 지점
    var ans = -1L // 정답

    // dp[k][n] = v : 리프트를 k번 탑승하고 n지점에 있을때 최대 시간 v
    lateinit var dp: Array<LongArray> // 1-based

    // 스키장 코스
    lateinit var slope: Array<ArrayList<Course>> // 1-based


    fun execute() {
        input()
        solve()
        output()
    }

    fun input() {
        // 1. N, M, K, S, T 입력
        readln().split(" ").map { it.toInt() }.apply {
            N = this[0]
            M = this[1]
            K = this[2]
            S = this[3]
            T = this[4]
        }
        // 2. 각종 배열 초기화
        dp = Array(K + 1) { LongArray(N + 1) { -1L } }
        slope = Array(N + 1) { ArrayList() }

        // 3. 코스 및 리프트 입력
        repeat(M) {
            val (from, to, time) = readln().split(" ").map { it.toInt() }
            slope[from].add(Course(to, time.toLong()))
        }
    }

    fun solve() {
        // 1. 시작점 초기화
        dp[0][S] = 0
        // 2. (스키 -> 리프트) 반복
        for (k in 0 until K) {
            rideSki(k)
            rideLift(k)
        }
        // 3. 마지막 스키 가능
        rideSki(K)
        // 4. 정답 확인
        for (k in 0..K) {
            ans = maxOf(ans, dp[k][T])
        }
        sb.append(ans)
    }

    fun output() {
        println(sb)
    }

    fun rideSki(k: Int) {
        for (from in 1..N) {
            // 스키를 탈 수 없는 경우 스킵
            if (dp[k][from] == -1L) continue
            for (sl in slope[from]) {
                dp[k][sl.to] = maxOf(dp[k][sl.to], dp[k][from] + sl.time)
            }
        }
    }

    fun rideLift(k: Int) {
        // 슬로프 (높은곳:to -> 낮은곳:sl.to)
        // 리프트 (낮은곳:sl.to -> 높은곳:to)
        for (to in 1..N) {
            for (sl in slope[to]) {

                if (dp[k][sl.to] == -1L) continue
                dp[k + 1][to] = maxOf(dp[k + 1][to], dp[k][sl.to])
            }
        }
    }

    data class Course(val to: Int, val time: Long)
}
