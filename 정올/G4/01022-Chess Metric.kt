// [G4] 01022-Chess Metric
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val dx = intArrayOf(-2, -2, -1, -1 ,-1 ,-1, -1, 0, 0, 1, 1, 1, 1, 1, 2, 2)
        val dy = intArrayOf(-1, 1, -2, -1, 0, 1, 2, -1, 1, -2, -1, 0, 1, 2, -1, 1)
        val sb = StringBuilder()
        var N = 0
        var initial = Node(0, 0)
        var goal = Node(0, 0)
        var numMoves = 0
        lateinit var dp : Array<Array<IntArray>>
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. 정보 입력
            st = StringTokenizer(readln())
            N = st.nextToken().toInt()
            initial = Node(st.nextToken().toInt(), st.nextToken().toInt())
            goal = Node(st.nextToken().toInt(), st.nextToken().toInt())
            numMoves = st.nextToken().toInt()
        }

        fun solve() {
            // 1. dp 초기화
            dp = Array(N) { Array(N) { IntArray(numMoves + 1) } }
            dp[initial.x][initial.y][0] = 1
            // 2. 메모제이션
            for (n in 1..numMoves) {
                for (x in 0 until N) {
                    for (y in 0 until N) {
                        for (i in 0 until 16) {
                            val nx = x + dx[i]
                            val ny = y + dy[i]
                            // 범위 이탈 스킵
                            if (nx !in 0 until N || ny !in 0 until N) continue
                            dp[x][y][n] += dp[nx][ny][n - 1]
                        }
                    }
                }
            }
            // 3. 정답 입력
            sb.append(dp[goal.x][goal.y][numMoves])
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}

private data class Node(val x: Int, val y: Int)
