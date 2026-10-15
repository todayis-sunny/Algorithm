// [G4] 01060-최소비용신장트리
import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var adj: Array<IntArray>
        lateinit var visited: BooleanArray
        val priorityQueue = PriorityQueue<Edge>(Comparator.comparing { it.cost })
        var count = 0
        var ans = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N입력
            N = readln().toInt()
            adj = Array(N) { IntArray(N) }
            visited = BooleanArray(N)
            // 2. 비용 입력
            for (r in 0 until N) {
                st = StringTokenizer(readln())
                for (c in 0 until N) {
                    adj[r][c] = st.nextToken().toInt()
                }
            }
        }

        fun solve() {
            // 1. 0번 노드부터 탐색
            for (i in 0 until N) {
                if (adj[0][i] == 0) continue
                priorityQueue.offer(Edge(0, i, adj[0][i]))
            }
            visited[0] = true
            count++
            while (priorityQueue.isNotEmpty() && count < N) {
                val edge = priorityQueue.poll()
                if (visited[edge.to]) continue
                visited[edge.to] = true
                ans += edge.cost
                count++
                for (i in 0 until N) {
                    if (adj[edge.to][i] == 0 || visited[i]) continue
                    priorityQueue.offer(Edge(edge.to, i, adj[edge.to][i]))
                }
            }
            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}

private data class Edge(val from: Int, val to: Int, val cost: Int)
