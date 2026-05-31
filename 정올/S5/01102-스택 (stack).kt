// [S5] 01102. 스택 (stack)
import java.util.Stack
import java.util.StringTokenizer

fun main() {
    val CMD_PUSH = 'i'
    val CMD_POP = 'o'
    val CMD_COUNT = 'c'
    val STR_EMPTY = "empty"

    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        val stack = Stack<Int>()
        fun execute() {
            input()
            output()
        }

        fun input() {
            // 1. 입력
            N = readln().toInt()
            // 2. 반복
            repeat(N) {
                st = StringTokenizer(readln())
                val cmd = st.nextToken()
                when (cmd[0]) {
                    CMD_PUSH -> {
                        val number = st.nextToken().toInt()
                        stack.push(number)
                    }

                    CMD_POP -> {
                        if (stack.isEmpty()) {
                            sb.append(STR_EMPTY)
                        } else {
                            sb.append(stack.pop())
                        }
                        sb.append("\n")
                    }

                    CMD_COUNT -> {
                        sb.append(stack.size).append("\n")
                    }
                }
            }
        }


        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}
