// [G4] 01953-팀배분
import java.util.StringTokenizer

fun main() {
    class Solution {
        val reversal = arrayOf(0, 2, 1)
        lateinit var st: StringTokenizer
        var N = 0
        val sb = StringBuilder()
        lateinit var students: Array<ArrayList<Int>>
        lateinit var teamInfo: IntArray
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            students = Array(N + 1) { arrayListOf() }
            teamInfo = IntArray(N + 1)
            // 2. 싫어하는 사람들 입력
            repeat(N) { n ->
                st = StringTokenizer(readln())
                val K = st.nextToken().toInt()
                repeat(K) { k ->
                    students[n + 1].add(st.nextToken().toInt())
                }
            }
        }

        fun solve() {
            for (i in 1 .. N) {
                // 1. 이미 팀 결성이면 스킵
                if (teamInfo[i] != 0) continue
                // 2. 팀이 없다면 탐색
                bfs(i)
            }

            teamInfo.indices
                .filter { teamInfo[it] != 0 }
                .groupBy { teamInfo[it] }
                .toSortedMap()
                .forEach { (_, indices) ->
                    sb.append(indices.size).append('\n')
                    sb.append(indices.joinToString(" ")).append('\n')
                }
        }

        fun output() {
            println(sb.toString())
        }

        fun bfs(student: Int) {
            val queue = ArrayDeque<Match>()
            queue.add(Match(student, 1))
            teamInfo[student] = 1
            while (queue.isNotEmpty()) {
                val match = queue.removeFirst()
                for (nxt in students[match.cur]) {
                    if (teamInfo[nxt] != 0) continue
                    queue.add(Match(nxt, reversal[match.team]))
                    teamInfo[nxt] = reversal[match.team]
                }
            }
        }
    }
    Solution().execute()
}

data class Match(val cur: Int, val team: Int)
