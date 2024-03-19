# 01039. [G2] 교환.
def dfs(n):
    global answer

    if n == N:
        answer = max(answer, int("".join(map(str, arr))))
        return

    for i in range(length-1):
        for j in range(i+1, length):
            if i == 0 and arr[j] == 0:
                continue
            arr[i], arr[j] = arr[j], arr[i]
            check = int("".join(map(str, arr)))*10 + n

            if check not in visited:
                dfs(n+1)
                visited.append(check)
            arr[i], arr[j] = arr[j], arr[i]


numbers, N = input().split()
N = int(N)
arr = []
for num in numbers:
    arr.append(int(num))
length = len(arr)
answer = -1
visited = []
dfs(0)
print(answer)