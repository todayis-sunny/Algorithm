# 4012. 요리사

def bitMaker(n, bits):
    if n == halfN:
        return
    for i in range(N):
        if bitmask[bits | (1 << i)] != 0:
            continue
        if bits & (1 << i) == 0:  # 중복 되지 않는 새로운 식재료.
            value = 0
            for j in range(N):
                if bits & (1 << j) != 0:  # 선택되지 않은 식재료는 무시.
                    value += synergy[i][j] + synergy[j][i]
            bitmask[bits | (1 << i)] = bitmask[bits] + value
            bitMaker(n+1, bits | (1 << i))


def bitFinder(bits):
    global answer
    value = abs(bitmask[bits] - bitmask[((1 << N)-1) ^ bits])
    answer = min(answer, value)
    return


for tc in range(1, int(input()) + 1):
    N = int(input())
    halfN = N//2
    synergy = [list(map(int, input().split())) for _ in range(N)]
    bitmask = [0] * (2**N - (2**(N//2) - 1))    # (2**N - 1 - (2**(N//2) - 1) + 1)

    bitMaker(0, 0)

    answer = 1e9
    for bit in range(len(bitmask)):
        if bin(bit)[2:].count('1') == halfN:
            bitFinder(bit)

    print(f"#{tc} {answer}")
