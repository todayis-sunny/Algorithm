// [G3] 08950. 상현이의 수강신청 대작전
import java.util.Stack
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        val IMP = -1
        var N = 0
        var M = 0
        lateinit var dp: IntArray
        lateinit var selected: Array<BooleanArray>
        lateinit var subjectsArr: Array<Subjects>
        var maxPreference = -1 // 최대 선호도
        var lastCredit = 0 // 최근 수강 학점

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

            dp = IntArray(M + 1) { IMP }
            selected = Array(N + 1) { BooleanArray(M + 1) }
            // 2. P, C 입력 (선호도, 학점)
            subjectsArr = Array(N + 1) { Subjects(0, 0) }
            for (i in 1..N) {
                st = StringTokenizer(readln())
                subjectsArr[i] = Subjects(st.nextToken().toInt(), st.nextToken().toInt())
            }
        }

        fun solve() {
            // 1. dp 초기화
            dp[0] = 0
            // 2. 메모제이션
            for (i in 1..N) {
                for (j in M downTo subjectsArr[i].credit) {
                    if (dp[j - subjectsArr[i].credit] == IMP) continue
                    if (dp[j] >= dp[j - subjectsArr[i].credit] + subjectsArr[i].preference) {
                        continue
                    } else {
                        dp[j] = dp[j - subjectsArr[i].credit] + subjectsArr[i].preference
                        selected[i][j] = true
                    }
                }
            }
            // 3. 최적해 찾기
            for (c in 0..M) {
                if (dp[c] == IMP || maxPreference >= dp[c]) continue
                maxPreference = dp[c]
                lastCredit = c
            }
            // 4. 역추적하여 정답 출력
            val stack = Stack<Int>()
            for (i in N downTo 1) {
                if (!selected[i][lastCredit]) continue
                stack.push(i)
                lastCredit -= subjectsArr[i].credit
            }
            if (stack.isEmpty()) {
                sb.append(-1)
            } else {
                sb.append(stack.size).append("\n")
                while (stack.isNotEmpty()) {
                    sb.append(stack.pop()).append(" ")
                }
            }
        }

        fun output() {
            println(sb)
        }
    }
    Solution().execute()

}

private data class Subjects(val preference: Int, val credit: Int)
