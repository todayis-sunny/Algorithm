# 01245. [diff_5] 균형점.

def force(m, d):
    if d == 0:
        return 0
    return m / (d*d)


def check(idx1, idx2):
    left = arr[idx1][0]
    right = arr[idx2][0]
    while left < right:
        mid = (left + right) / 2

        if round(left, 10) == round(right, 10):
            return round(mid, 10)
        F = 0.0

        for L in range(0, idx1 + 1):
            F -= force(arr[L][1], abs(mid - arr[L][0]))

        for R in range(idx2, length):
            F += force(arr[R][1], abs(mid - arr[R][0]))

        if F < 0:
            left = round(mid, 13);
        elif F > 0:
            right = round(mid, 13);
        else:
            return round(mid, 10)


TC = int(input())

for tc in range(1, TC+1):
    N = int(input())
    arr = []
    data = list(map(int, input().split()))
    answer = []
    for n in range(N):
        arr.append([data[n], data[n+N]])
    length = len(arr)

    for n in range(N-1):
        answer.append(check(n, n+1))

    print(f"#{tc} ", end="")
    for ans in answer:
        print(f"{ans:.10f} ", end="")
    print()
