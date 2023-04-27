# 1244. 최대 상금 <D3>


def dfs(n):
    global ans
    if n == swap:
        ans = max(ans, int(''.join(map(str, arr))))
        return

    # l개에서 2개 뽑는 모든 조합(들을 교환)
    for i in range(l-1):
        for j in range(i + 1, l):
            arr[i], arr[j] = arr[j], arr[i]

            chk = int(''.join(map(str, arr)))
            if (n, chk) not in visited:
                dfs(n+1)
                visited.append((n, chk))
            # 반드시 원상복구
            arr[i], arr[j] = arr[j], arr[i]


tc = int(input())

for t in range(1, tc + 1):
    nums, swap = input().split()
    swap = int(swap)
    arr = []
    for num in nums:
        arr.append(int(num))
    l = len(arr)
    ans = 0
    visited = []
    dfs(0)
    print(f'#{t} {ans}')