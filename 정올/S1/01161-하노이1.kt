// [S1] 01161. 하노이1
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0
        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N 입력
            N = readln().toInt()
        }

        fun solve() {
            // 1. 하노이 시뮬레이션
            hanoi(N, 1, 3, 2)
        }

        fun output() {
            println(sb.toString())
        }

        fun hanoi(disk: Int, from: Int, to: Int, other: Int) {
            // disk == 1이면 가장 작은 원판 하나만 옮기면 된다.
            // 더 쪼갤 필요 없이 from 기둥에서 to 기둥으로 바로 이동한다.
            if (disk == 1) {
                sb.append("$disk : $from -> $to\n")
                return
            }
            // 1. 가장 큰 원판 disk를 옮기기 위해
            // 그 위에 있는 disk - 1개의 작은 원판들을 보조 기둥 other로 옮긴다.
            hanoi(disk - 1, from, other, to)
            // 2. 이제 가장 큰 원판 disk를 from 기둥에서 to 기둥으로 옮긴다.
            sb.append("$disk : $from -> $to\n")
            // 3. 보조 기둥 via에 옮겨두었던 disk - 1개의 원판들을
            // 다시 목표 기둥 to로 옮긴다.
            hanoi(disk - 1, other, to, from)
        }
    }
    Solution().execute()
}

/*
 풀이날짜 2026. 06. 22
 소요시간 00h 00m
 */
