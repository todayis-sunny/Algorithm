// [S2] 01370. 회의실 배정
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        var limitTime = 0 // 종료예정 시간
        lateinit var meetings: Array<Meeting>
        var ans = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
            // 2. 회의 입력
            meetings = Array(N) {
                st = StringTokenizer(readln())
                Meeting(st.nextToken().toInt(), st.nextToken().toInt(), st.nextToken().toInt())
            }
        }

        fun solve() {
            // 1. 종료시간 기준 오름차순
            meetings.sortWith(compareBy { it.endTime})
            // 2. 회의 선정
            for (meeting in meetings) {
                // a. 시작시간이 예정된 종료시간보다 선행인 경우 스킵
                if (meeting.startTime < limitTime) continue
                // b. 아닌 경우 회의 진행 가능
                ans++
                sb.append(meeting.id).append(" ")
                limitTime = meeting.endTime
            }
        }

        fun output() {
            println(ans)
            println(sb.toString())
        }
    }
    Solution().execute()
}

private data class Meeting(val id: Int, val startTime: Int, val endTime: Int)
