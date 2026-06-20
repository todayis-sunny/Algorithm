// [G3] 04775. 고드름

import java.util.PriorityQueue
import java.util.StringTokenizer

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        var N = 0 // 고드름 개수
        var L = 0 // 고드름 길이
        val pq = PriorityQueue<Icicle>(compareBy { it.endTime })
        lateinit var icicleArr: IntArray
        lateinit var sideBigger: IntArray // 선행 여부 개수
        var ans = 0L

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. N, L 입력
            readln().split(" ").map { it.toInt() }.apply {
                N = this[0]
                L = this[1]
            }
            sideBigger = IntArray(N)
            // 2. 고드름 입력
            icicleArr = IntArray(N) {
                readln().toInt()
            }
        }

        fun solve() {
            // 1. 선행 여부 개수 기입
            for (i in 0 until N) {
                for (j in -1 ..1 step 2) {
                    val idx = i + j
                    // a. 범위밖이면 제외
                    if (idx !in 0 until N) continue
                    // b. 더 큰 고드름이 있으면 선행 여부 증가
                    if (icicleArr[i] < icicleArr[idx]) sideBigger[i]++
                }
                if (sideBigger[i] == 0) pq.offer(Icicle(i,(L - icicleArr[i]).toLong()))
            }
            // 2. 고드름 녹이기
            while (pq.isNotEmpty()) {
                val water = pq.poll()
                ans = water.endTime
                for (j in -1..1 step 2) {
                    val idx = water.id + j
                    // a. 범위밖이면 제외
                    if (idx !in 0 until N) continue
                    // b. 0이 안되면 고드름 패스
                    if (--sideBigger[idx] != 0) continue
                    pq.offer(Icicle(idx, ans + (L - icicleArr[idx])))
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

private data class Icicle(val id: Int, val endTime: Long)
