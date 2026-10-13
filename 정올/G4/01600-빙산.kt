// [G4] 01600-빙산
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)
        var N = 0
        var M = 0
        lateinit var iceberg : Array<IntArray>
        lateinit var visited : Array<BooleanArray>
        val waitingQueue = ArrayDeque<Node>()
        var ans = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, M 입력
            st = StringTokenizer(readln())
            N = st.nextToken().toInt()
            M = st.nextToken().toInt()
            // 2. 빙산 입력
            iceberg = Array(N) { IntArray(M) }
            visited = Array(N) { BooleanArray(M) }
            for (i in 0 until N) {
                st = StringTokenizer(readln())
                for (j in 0 until M) {
                    iceberg[i][j] = st.nextToken().toInt()
                }
            }
        }

        fun solve() {
            // 1. 빙산 분리
            var year = 0
            while (true) {
                var count = 0
                visited = Array(N) { BooleanArray(M) }
                for (r in 0 until N) {
                    for (c in 0 until M) {
                        // a. 바다는 스킵
                        if (iceberg[r][c] == 0) continue
                        // b. 이미 발견된 빙산은 스킵
                        if (visited[r][c]) continue
                        // c. 빙산 발견
                        if (++count >= 2) {
                            ans = year
                            return
                        }
                        bfs(r, c)
                    }
                }
                if (count == 0) break
                melting()
                year++
            }
        }

        /**
         * 빙산 녹이기 전
         *
         * @param x 빙산 발견 x
         * @param y 빙산 발견 y
         */
        fun bfs(x: Int, y: Int) {
            val queue = ArrayDeque<Node>()
            queue.addLast(Node(x, y, 0))
            visited[x][y] = true
            while (queue.isNotEmpty()) {
                val node = queue.removeFirst()
                val x = node.x
                val y = node.y
                var cnt = 0
                for (i in 0 until 4) {
                    val nx = x + dx[i]
                    val ny = y + dy[i]
                    // 범위밖 스킵
                    if (nx !in 0 until N || ny !in 0 until M) continue
                    // 방문 빙산 스킵
                    if (visited[nx][ny]) continue
                    // 빙산 제외
                    if (iceberg[nx][ny] != 0) {
                        queue.addLast(Node(nx, ny, 0))
                        visited[nx][ny] = true
                        continue
                    }
                    // 바다
                    cnt++

                }
                waitingQueue.addLast(Node(x, y, maxOf(iceberg[x][y] - cnt, 0)))

            }
        }

        /**
         * 빙산 녹이기
         *
         */
        fun melting() {
            while (waitingQueue.isNotEmpty()) {
                val node = waitingQueue.removeFirst()
                iceberg[node.x][node.y] = node.value
            }
        }

        fun output() {
            sb.append(ans)
            println(sb.toString())
        }
    }
    Solution().execute()
}

private data class Node(val x: Int, val y: Int, val value: Int)
