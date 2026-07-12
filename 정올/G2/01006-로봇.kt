// [G2] 01006-로봇
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()

        // X 동 서 남 북
        val dx = intArrayOf(0, 0, 0, 1, -1)
        val dy = intArrayOf(0, 1, -1, 0, 0)
        val EMPTY = 0
        val WALL = 1
        val INF = Int.MAX_VALUE
        var R = 0
        var C = 0
        lateinit var factory: Array<IntArray>
        lateinit var commandCount: Array<Array<IntArray>>
        lateinit var start: Position
        lateinit var end: Position
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. R, C 입력
            st = StringTokenizer(readln())
            R = st.nextToken().toInt()
            C = st.nextToken().toInt()
            // 2. 공장 입력
            factory = Array(R) { IntArray(C) }
            for (r in 0 until R) {
                st = StringTokenizer(readln())
                for (c in 0 until C) {
                    factory[r][c] = st.nextToken().toInt()
                }
            }
            // 3. 시직, 도착 입력
            st = StringTokenizer(readln())
            start = Position(st.nextToken().toInt() - 1, st.nextToken().toInt() - 1, st.nextToken().toInt())
            st = StringTokenizer(readln())
            end = Position(st.nextToken().toInt() - 1, st.nextToken().toInt() - 1, st.nextToken().toInt())
        }

        fun solve() {
            // 1. 명령횟수 배열 초기화
            commandCount = Array(R) { Array(C) { IntArray(5) { INF } } }
            commandCount[start.x][start.y][start.d] = 0
            // 2. 같은 상태 예외 처리
            if (start == end) sb.append(0)
            // 3. BFS
            bfs()
        }

        fun output() {
            println(sb.toString())
        }

        fun bfs() {
            val queue = ArrayDeque<Position>()
            queue.addLast(start)
            while (queue.isNotEmpty()) {
                val cur = queue.removeFirst()
                // 1. 명령 1
                for (k in 1..3) {
                    val nxt = cmd1(cur, k)
                    // 범위 밖 탈출
                    if (nxt.x !in 0 until R || nxt.y !in 0 until C) break
                    // 벽은 탈출(k이상 부터는 통과 X)
                    if (factory[nxt.x][nxt.y] == WALL) break
                    // 이미 최소 횟수 스킵
                    if (commandCount[nxt.x][nxt.y][nxt.d] <= commandCount[cur.x][cur.y][cur.d] + 1) continue
                    // 큐에 삽입
                    queue.addLast(nxt)
                    commandCount[nxt.x][nxt.y][nxt.d] = commandCount[cur.x][cur.y][cur.d] + 1
                    if (nxt == end) {
                        sb.append(commandCount[nxt.x][nxt.y][nxt.d])
                        return
                    }
                }
                // 2. 명령 2
                for (turnRight in listOf(true, false)) {
                    val nxt = cmd2(cur, turnRight)
                    // 이미 최소 횟수 스킵
                    if (commandCount[nxt.x][nxt.y][nxt.d] <= commandCount[cur.x][cur.y][cur.d] + 1) continue
                    // 큐에 삽입
                    queue.addLast(nxt)
                    commandCount[nxt.x][nxt.y][nxt.d] = commandCount[cur.x][cur.y][cur.d] + 1
                    if (nxt == end) {
                        sb.append(commandCount[nxt.x][nxt.y][nxt.d])
                        return
                    }
                }
            }
        }

        fun cmd1(position: Position, k: Int): Position {
            return Position(position.x + dx[position.d] * k, position.y + dy[position.d] * k, position.d)
        }

        fun cmd2(position: Position, turnRight: Boolean): Position {
            val nd = if (turnRight) {
                when (position.d) {
                    1 -> 3
                    3 -> 2
                    2 -> 4
                    else -> 1
                }

            } else {
                when (position.d) {
                    1 -> 4
                    4 -> 2
                    2 -> 3
                    else -> 1
                }
            }
            return Position(position.x, position.y, nd)
        }
    }
    Solution().execute()
}

private data class Position(val x: Int, val y: Int, val d: Int)
