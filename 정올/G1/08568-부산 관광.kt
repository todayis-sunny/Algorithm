// [G1] 08568. 부산 관광

fun main() {
    Solution().execute()
}

private class Solution {
    val sb = StringBuilder()
    var N = 0
    var p1 = 0;
    var p3 = 0;
    var p5 = 0;
    var pp = 0
    lateinit var va: BooleanArray
    lateinit var vb: BooleanArray
    lateinit var dp: Array<IntArray>
    val INF = 1_000_000_000

    fun execute() {
        input()
        solve()
        output()
    }

    fun input() {
        N = readln().toInt()
        val sa = readln()
        val sb = readln()
        readln().split(" ").map { it.toInt() }.also {
            p1 = it[0]; p3 = it[1]; p5 = it[2]; pp = it[3]
        }
        va = BooleanArray(N + 2)
        vb = BooleanArray(N + 2)
        for (i in 1..N) {
            if (sa[i - 1] == '1') va[i] = true
            if (sb[i - 1] == '1') vb[i] = true
        }
    }

    fun solve() {
        dp = Array(N + 6) { IntArray(N + 6) { INF } }
        dp[0][0] = 0

        for (a in 1..N + 1) {
            val aa = va[a]
            for (b in 1..N + 1) {
                val bb = vb[b]
                if (dp[a - 1][b - 1] == INF) continue

                // 관광 없는 날
                if (!aa) dp[a][b - 1] = minOf(dp[a][b - 1], dp[a - 1][b - 1])
                if (!bb) dp[a - 1][b] = minOf(dp[a - 1][b], dp[a - 1][b - 1])

                // 개별 티켓
                if (aa) updateA(a - 1, b - 1)
                if (bb) updateB(a - 1, b - 1)

                // 묶음권 (같은 날)
                if (a == b) updatePP(a - 1, b - 1)
            }
        }

        sb.append(dp[N][N])
    }

    fun updateA(i: Int, j: Int) {
        dp[i + 1][j] = minOf(dp[i + 1][j], dp[i][j] + p1)
        for (k in 1..3) dp[i + k][j] = minOf(dp[i + k][j], dp[i][j] + p3)
        for (k in 1..5) dp[i + k][j] = minOf(dp[i + k][j], dp[i][j] + p5)
    }

    fun updateB(i: Int, j: Int) {
        dp[i][j + 1] = minOf(dp[i][j + 1], dp[i][j] + p1)
        for (k in 1..3) dp[i][j + k] = minOf(dp[i][j + k], dp[i][j] + p3)
        for (k in 1..5) dp[i][j + k] = minOf(dp[i][j + k], dp[i][j] + p5)
    }

    fun updatePP(i: Int, j: Int) {
        for (a in 0..4) {
            for (b in 0..4) {
                dp[i + a][j + b] = minOf(dp[i + a][j + b], dp[i][j] + pp)
            }
        }
    }

    fun output() {
        println(sb)
    }
}
