// [S4] 05917. 접시 닦기
import java.util.Stack
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        var T = 0
        val space = Array(3) { Stack<Int>() }
        lateinit var queries: Array<Query>
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, T 입력
            st = StringTokenizer(readln())
            N = st.nextToken().toInt()
            T = st.nextToken().toInt()
            // 2. 쿼리 입력
            queries = Array(T) {
                st = StringTokenizer(readln())
                Query(st.nextToken().toInt(), st.nextToken().toInt())
            }
        }

        fun solve() {
            // 1. 스택 초기화
            repeat(N) {
                space[0].push(N - it)
            }
            // 2. 쿼리 실행
            for (query in queries) {
                repeat(query.count) {
                    space[query.cmd].push(space[query.cmd - 1].pop())
                }
            }
            // 3. 정답 처리
            while (space[2].isNotEmpty()) {
                sb.append(space[2].pop()).append("\n")
            }
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}

private data class Query(val cmd: Int, val count: Int)
