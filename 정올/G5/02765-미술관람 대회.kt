// [G5] 02765. 미술관람 대회
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)
        var N = 0
        lateinit var originalPicture: Array<CharArray>
        lateinit var specialPicture: Array<CharArray>
        lateinit var originalVisited: Array<BooleanArray>
        lateinit var specialVisited: Array<BooleanArray>
        var originalCount = 0
        var specialCount = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. 그림 입력 (원본 그림을 먼저 입력받음)
            originalPicture = Array(N) { readln().toCharArray() }
        }

        fun solve() {
            // 1. 적록색약 그림 세팅 (원본 그림을 순회하며 'G'면 'R'로, 아니면 그대로 복사)
            specialPicture = Array(N) { r ->
                CharArray(N) { c ->
                    if (originalPicture[r][c] == 'G') 'R' else originalPicture[r][c]
                }
            }
            // 2. 방문배열 초기화
            originalVisited = Array(N) { BooleanArray(N) }
            specialVisited = Array(N) { BooleanArray(N) }
            // 3. 탐색
            for (isOrigin in listOf(true, false)) {
                val visited = if (isOrigin) originalVisited else specialVisited
                var count = 0
                for (x in 0 until N) {
                    for (y in 0 until N) {
                        if (visited[x][y]) continue
                        bfs(x, y, isOrigin)
                        count++
                    }
                }
                if (isOrigin) originalCount = count else specialCount = count
            }
            // 4. 정답 처리
            sb.append(originalCount).append(" ").append(specialCount)
        }

        fun output() {
            println(sb.toString())
        }

        fun bfs(x: Int, y: Int, isOrigin: Boolean) {
            val picture = if (isOrigin) originalPicture else specialPicture
            val visited = if (isOrigin) originalVisited else specialVisited
            val color = picture[x][y]
            val queue = ArrayDeque<Node>()
            queue.addLast(Node(x, y))
            visited[x][y] = true
            while (queue.isNotEmpty()) {
                val (cx, cy) = queue.removeFirst()
                for (i in 0 until 4) {
                    val nx = cx + dx[i]
                    val ny = cy + dy[i]
                    // 범위밖은 스킵
                    if (nx !in 0 until N || ny !in 0 until N) continue
                    // 이미 방문한곳 스킵
                    if (visited[nx][ny]) continue
                    // 컬러가 다른 경우 스킵
                    if (picture[nx][ny] != color) continue
                    queue.addLast(Node(nx, ny))
                    visited[nx][ny] = true
                }
            }
        }
    }
    Solution().execute()
}

private data class Node(val x: Int, val y: Int)
