# 01244. 최대 상금.

def dfs(n):
    global answer

    if n == N:
        answer = max(answer, int("".join(map(str, arr))))
        return

    for i in range(length-1):
        for j in range(i+1, length):
            arr[i], arr[j] = arr[j], arr[i]
            check = int("".join(map(str, arr)))*10 + n
            if check not in visited:
                dfs(n+1)
                visited.append(check)
            arr[i], arr[j] = arr[j], arr[i]


TC = int(input())

for tc in range(1, TC + 1):
    numbers, N = input().split()
    N = int(N)
    arr = []
    for num in numbers:
        arr.append(int(num))
    length = len(arr)
    answer = 0
    visited = []
    dfs(0)
    print(f"#{tc} {answer}")
