// [G4] 01889.N Queen

fun main() {
    class Solution {
        val dx = intArrayOf(-1, -1, 1, 1)
        val dy = intArrayOf(-1, 1, -1, 1)
        var N = 0
        lateinit var visited: BooleanArray
        lateinit var board: IntArray
        var ans = 0

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            N = readln().toInt()
        }

        fun solve() {
            board = IntArray(N) { -1 }
            visited = BooleanArray(N)

            dfs(0)
        }

        fun output() {
            println(ans)
        }

        fun dfs(depth: Int) {
            if (depth == N) {
                ans++
                return
            }
            for (i in 0 until N) {
                if (visited[i]) continue
                if (check(depth, i)) {
                    board[depth] = i
                    visited[i] = true
                    dfs(depth + 1)
                    visited[i] = false
                    board[depth] = -1
                }
            }
        }

        fun check(x: Int, y: Int): Boolean {
            for (i in 0 until 4) {
                var k = 0
                while (true) {
                    val nx = x + dx[i] * k
                    val ny = y + dy[i] * k
                    if (nx < 0 || nx >= N || ny < 0 || ny >= N) break
                    if (board[nx] == ny) return false
                    k++
                }
            }
            return true
        }
    }
    Solution().execute()
}
