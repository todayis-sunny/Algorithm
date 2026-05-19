package jungol.solved_3_g2.p06326
// [G2] 06326. 백열등 2

fun main() {
    class Solution {
        val sb = StringBuilder()
        val rgb = "RGB"
        var N = 0
        lateinit var S: String
        var A = 0L // 가장 좌측 끄는 비용
        var B = 0L // 가장 우측 끄는 비용
        var C = 0L // 원하는 전등 바꾸는 비용

        // opt[i % 3] = 구간의 우측 끝이 i번째 전등일 때, 좌측 제거 + 색 교체 비용의 최솟값
        lateinit var opt: LongArray
        var ans = 0L

        fun execute() {
            input()
            solve()
            output()
        }

        fun input() {
            // 1. 입력
            N = readln().toInt()
            S = readln()
            readln().split(" ").map { it.toLong() }.apply {
                A = this[0]
                B = this[1]
                C = this[2]
            }
        }

        fun solve() {
            // 1. ans 초기화 (좌측 or 우측 중 싼 방향으로 N개)
            ans = minOf(A, B) * N
            // 2. opt 초기화 (2 -> 0 -> 1)
            /*
            opt[i % 3] = 구간의 우측 끝이 i번째 전등일 때
                    좌측 제거 + 색 교체 비용의 최솟값
            i=0 → opt[0] = A    (구간 [0] 1개, (0+1)*A 와 같음)
            i=1 → opt[1] = 2*A  (구간 [0,1] 2개, (1+1)*A 와 같음)
            i=2 → opt[2] = 0    (루프에서 처음 계산, 초기 누적값 없음)
             */
            opt = longArrayOf(A, 2 * A, 0)
            // 3. opt 메모제이션
            for (i in 2 until N) {
                for (j in 0..2) {
                    if (S[i + j - 2] != rgb[j]) opt[i % 3] += C
                }
                // 구간 [0..i]를 전부 좌측에서 제거하는 비용 (i + 1) * A 와 비교
                // 교체 비용보다 그냥 다 끄는게 싸면 덮어씌움
                opt[i % 3] = minOf(opt[i % 3], (i + 1) * A)
                // 우측 나머지 (i+1..N-1) 를 B원씩 제거하는 비용 추가
                // 현재까지 최솟값 갱신
                ans = minOf(ans, opt[i % 3] + (N - 1 - i) * B)
            }
            sb.append(ans)
        }

        fun output() {
            println(sb)
        }
    }
    Solution().execute()
}


/*
 풀이날짜 2026. 5. 19.
 소요시간 00h 00m
 */