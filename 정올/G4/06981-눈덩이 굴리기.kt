package jungol.solved_3_g4
// [G4] 06981. 눈덩이 굴리기

fun main() {
    Solution().run()
}

private class Solution {
    val sb = StringBuilder()
    var N = 0
    var M = 0
    lateinit var snow: IntArray
    lateinit var dp: Array<IntArray>
    var ans = 0

    fun run() {
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
        // 2. snow, dp 초기화
        snow = IntArray(N + 1) // 1-based
        dp = Array(M + 1) { IntArray(N + 1) }// 0-based
        readln().split(" ").map { it.toInt() }.forEachIndexed { i, value ->
            snow[i + 1] = value;
        }
    }

    fun solve() {
        // 1. dp 초기값 설정
        dp[0][0] = 1
        // 2. 메모제이션
        for (t in 0 until M) {
            for (i in 0..N) {
                if (dp[t][i] == 0) continue  // 방문 안 한 칸
                // a. 굴리기
                if (i + 1 <= N) {
                    dp[t + 1][i + 1] = maxOf(dp[t + 1][i + 1], dp[t][i] + snow[i + 1])
                }
                // b. 던지기
                if (i + 2 <= N) {
                    dp[t + 1][i + 2] = maxOf(dp[t + 1][i + 2], dp[t][i] / 2 + snow[i + 2])
                }
            }
        }
        // 3. 최대값 찾기
        ans = maxOf(
            (0..M).maxOf { dp[it][N] }, // N에 도달한 경우
            (0..N).maxOf { dp[M][it] } // 시간이 종료된 경우
        )
        sb.append(ans)
    }

    fun output() {
        println(sb.toString())
    }
}

/*
 풀이날짜 2026. 05. 05
 소요시간 00h 50m
 태그: #DP
 */

