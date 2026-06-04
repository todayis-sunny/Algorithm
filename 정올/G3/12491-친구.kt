// [G3] 12491. 친구
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        var N = 0
        var K1 = 0
        var K2 = 0
        lateinit var students: Array<Student>
        lateinit var ans: IntArray
        val sb = StringBuilder()
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, K1, K2 입력
            st = StringTokenizer(readln())
            N = st.nextToken().toInt()
            K1 = st.nextToken().toInt()
            K2 = st.nextToken().toInt()
            // 2. 학생 정보 입력
            students = Array(N) { i ->
                st = StringTokenizer(readln())
                Student(i, st.nextToken().toInt(), st.nextToken().toInt())
            }
            // 3. ans 초기화
            ans = IntArray(N)
        }

        fun solve() {
            // 1. 거리순 정렬
            students.sortWith(compareBy { it.home })
            // 2. 같은 학교 K1이하
            var left = 0
            var right = 0
            var count = IntArray(N + 1)
            for (curr in 0 until N) {
                // a. 자신의 학교 인원 증가
                count[students[curr].school]++
                // b. 좌측 제거
                while (students[curr].home - students[left].home > K1) {
                    count[students[left++].school]--
                }
                // c. 우측 추가
                while (right + 1 < N && students[right + 1].home - students[curr].home <= K1) {
                    count[students[++right].school]++
                }
                // d. 자기자신 제외하고 같은 학교 친구 추가
                ans[students[curr].id] += count[students[curr].school] - 1
            }
            // 3. 다른 학교 K2이하
            left = 0
            right = 0
            count = IntArray(N + 1)
            for (curr in 0 until N) {
                // a. 자신의 학교 인원 증가
                count[students[curr].school]++
                // b. 좌측 제거
                while (students[curr].home - students[left].home > K2) {
                    count[students[left++].school]--
                }
                // c. 우측 추가
                while (right + 1 < N && students[right + 1].home - students[curr].home <= K2) {
                    count[students[++right].school]++
                }
                // d. 자기자신 제외하고 다른 학교 친구 추가
                ans[students[curr].id] += (right - left + 1) - count[students[curr].school]
            }
        }

        fun output() {
            println(ans.joinToString(" "))
        }
    }
    Solution().execute()
}

private data class Student(val id: Int, val home: Int, val school: Int)
