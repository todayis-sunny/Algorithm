// [G5] 01328-빌딩
import java.util.Stack
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        lateinit var building: IntArray
        lateinit var result: IntArray
        val stack = Stack<Int>()
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().trim().toInt()
            building = IntArray(N + 1)
            result = IntArray(N + 1)
            // 2. 빌딩 입력
            for (i in 1 .. N) {
                building[i] = readln().trim().toInt()
            }
        }

        fun solve() {
            // 1. 빌딩 하나씩 검사
            for (curr in 1 .. N) {
                while (stack.isNotEmpty() && building[stack.peek()] < building[curr]) {
                    result[stack.pop()] = curr
                }
                stack.push(curr)
            }
            // 2. 정답 출력
            for (i in 1 .. N) {
                sb.append(result[i]).append("\n")
            }
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
