// [S2] 01761-숫자 야구
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        val questions = ArrayList<Triple<String, Int, Int>>()
        var ans = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().trim().toInt()
            // 2. 질문 입력
            repeat(N) {
                st = StringTokenizer(readln())
                questions.add(Triple(st.nextToken(), st.nextToken().toInt(), st.nextToken().toInt()))
            }
        }

        fun solve() {
            // 1. 가능한 모든 세 자리 수 후보를 완전탐색
            for (i in 1..9) {
                for (j in 1..9) {
                    if (i == j) continue

                    for (k in 1..9) {
                        if (i == k || j == k) continue

                        val candidate = "$i$j$k"
                        var possible = true

                        // a. 현재 후보가 모든 질문 결과와 일치하는지 확인
                        for ((question, expectedStrike, expectedBall) in questions) {
                            val (strike, ball) = check(candidate, question)

                            if (strike != expectedStrike || ball != expectedBall) {
                                possible = false
                                break
                            }
                        }

                        // b. 모든 질문을 통과한 후보만 정답 개수에 추가
                        if (possible) {
                            ans++
                        }
                    }
                }
            }

            sb.append(ans)
        }

        fun output() {
            println(sb.toString())
        }

        fun check(candidate: String, question: String): Pair<Int, Int> {
            var strike = 0
            var ball = 0

            for (i in 0 until 3) {
                if (candidate[i] == question[i]) {
                    strike++
                } else if (candidate.contains(question[i])) {
                    ball++
                }
            }

            return Pair(strike, ball)
        }
    }
    Solution().execute()
}
