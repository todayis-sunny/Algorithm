// [G5] 04207-[swat]치킨배달
import java.util.StringTokenizer
import kotlin.math.abs

fun main() {
    class Solution {
        lateinit var st: StringTokenizer
        val sb = StringBuilder()
        val EMPTY = 0
        val HOUSE = 1
        val TELEPHONE_POLE = 2
        var N = 0
        var M = 0
        lateinit var map: Array<IntArray>
        val houseList = ArrayList<Node>()
        val telephonePoleList = ArrayList<Node>()
        var limitHouse = 0
        var limitTelephonePole = 0
        var ans = Int.MAX_VALUE
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
            // 2. 지도 입력
            map = Array(N) { IntArray(N) }
            for (i in 0 until N) {
                st = StringTokenizer(readln())
                for (j in 0 until N) {
                    map[i][j] = st.nextToken().toInt()
                }
            }
        }

        fun solve() {
            // 1. 집, 전봇대 리스트 담기
            for (x in 0 until N) {
                for (y in 0 until N) {
                    if (map[x][y] == HOUSE) {
                        houseList.add(Node(x, y))
                    } else if (map[x][y] == TELEPHONE_POLE) {
                        telephonePoleList.add(Node(x, y))
                    }
                }
            }
            limitHouse = houseList.size
            limitTelephonePole = telephonePoleList.size
            // 2. 조합해서 거리 계산하기
            combination(0, 0, IntArray(M) { -1 })
            // 3. 정답 입력
            sb.append(ans)
        }

        fun combination(depth: Int, index: Int, arr: IntArray) {
            if (depth == M) {
                var total = 0
                // 1. 집들
                for (h in 0 until limitHouse) {
                    var min = Int.MAX_VALUE
                    // a. 선택된 전봇대
                    for (i in 0 until M) {
                        min = minOf(
                            min,
                            getDistance(
                                telephonePoleList[arr[i]].x,
                                telephonePoleList[arr[i]].y,
                                houseList[h].x,
                                houseList[h].y
                            )
                        )
                    }
                    total += min
                }
                ans = minOf(ans, total)
                return
            }
            for (i in index until limitTelephonePole) {
                arr[depth] = i
                combination(depth + 1, i + 1, arr)
                arr[depth] = -1
            }
        }

        fun getDistance(x1: Int, y1: Int, x2: Int, y2: Int): Int {
            return abs(x1 - x2) + abs(y1 - y2)
        }

        fun output() {
            println(sb.toString())
        }
    }
    Solution().execute()
}

private data class Node(val x: Int, val y: Int)
