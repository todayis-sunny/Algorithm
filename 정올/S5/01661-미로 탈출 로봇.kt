// [S5] 01661-미로 탈출 로봇
import java.util.StringTokenizer

fun main() {
    val dy = listOf(-1, 1, 0, 0)
    val dx = listOf(0, 0, -1, 1)
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var X = 0
        var Y = 0
        lateinit var maze: Array<IntArray>
        lateinit var start: Node
        lateinit var end: Node
        var ans = 0

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. X(가로), Y(세로) 입력
            st = StringTokenizer(readln())
            X = st.nextToken().toInt()
            Y = st.nextToken().toInt()
            // 2. 출발, 도착 입력 (x y 순서 → Node(y, x)로 저장)
            st = StringTokenizer(readln())
            val sx = st.nextToken().toInt() - 1
            val sy = st.nextToken().toInt() - 1
            val ex = st.nextToken().toInt() - 1
            val ey = st.nextToken().toInt() - 1
            start = Node(sy, sx)
            end = Node(ey, ex)
            // 3. 미로 입력 (벽 '1' → -1, 길 '0' → 0)
            maze = Array(Y) { readln().map { -(it - '0') }.toIntArray() }
        }

        fun solve() {
            bfs()
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }

        fun bfs() {
            if (start == end) return  // ans = 0

            val queue = ArrayDeque<Node>()
            queue.add(start)
            maze[start.y][start.x] = 1
            while (queue.isNotEmpty()) {
                val curr = queue.removeFirst()
                for (i in 0..3) {
                    val ny = curr.y + dy[i]
                    val nx = curr.x + dx[i]
                    // 1. 범위 밖 스킵
                    if (ny !in 0 until Y || nx !in 0 until X) continue
                    // 2. 방문 또는 벽 스킵
                    if (maze[ny][nx] != 0) continue
                    // 3. 탐색
                    maze[ny][nx] = maze[curr.y][curr.x] + 1
                    if (ny == end.y && nx == end.x) {
                        ans = maze[ny][nx] - 1
                        return
                    }
                    queue.add(Node(ny, nx))
                }
            }
        }
    }
    Solution().execute()
}

data class Node(val y: Int, val x: Int)
