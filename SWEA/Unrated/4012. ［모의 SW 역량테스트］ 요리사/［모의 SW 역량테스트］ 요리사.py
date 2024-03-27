# 4012. 요리사

def bitMaker(n, bits):
    global answer
    if n == halfN:
        if bitmask[((1 << N)-1) ^ bits] != 0:
            value = abs(bitmask[bits] - bitmask[((1 << N) - 1) ^ bits])
            answer = min(answer, value)
        return
    for i in range(N):
        newBits = bits | (1 << i)
        if bitmask[newBits] != 0:
            continue
        if bits & (1 << i) == 0:  # 중복 되지 않는 새로운 식재료.
            value = 0
            for j in range(N):
                if bits & (1 << j) != 0:  # 선택되지 않은 식재료는 무시.
                    value += synergy[i][j] + synergy[j][i]
            bitmask[newBits] = bitmask[bits] + value
            bitMaker(n+1, newBits)


for tc in range(1, int(input()) + 1):
    N = int(input())
    halfN = N//2
    synergy = [list(map(int, input().split())) for _ in range(N)]
    bitmask = [0] * (2**N - (2**(N//2) - 1))    # (2**N - 1 - (2**(N//2) - 1) + 1)
    answer = 1e9
    bitMaker(0, 0)

    print(f"#{tc} {answer}")
