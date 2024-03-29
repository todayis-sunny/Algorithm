# 02112. 보호 필름.

def dfs(idx, medicine):
    global answer
    for w in range(W):
        cnt = 0
        target = '2'
        for d in range(D):
            if film[d][w] != target:  # 다르면 다시 세기.
                cnt = 1
                target = film[d][w]
            else:  # 같으면 cnt 증가.
                cnt += 1
            if cnt >= K:  # 합격기준이면 다음 셀 검사.
                break
        else:  # 합격기준 미흡이면 break
            break
    else:  # 성능검사 통과
        answer = min(answer, medicine)
    if idx == D:
        return
    if medicine >= answer:
        return

    backup = film[idx]
    # 약품 0 투어
    film[idx] = str(bin(1 << W))[3:]
    dfs(idx + 1, medicine + 1)
    film[idx] = backup

    # 약품 1 투여
    film[idx] = str(bin((1 << W) - 1))[2:]
    dfs(idx + 1, medicine + 1)
    film[idx] = backup
    # 약품 미투여
    dfs(idx + 1, medicine)
    #
    return


for tc in range(1, int(input()) + 1):
    D, W, K = map(int, input().split())
    film = [input().replace(" ", "") for _ in range(D)]
    answer = 1e9
    dfs(0, 0)
    print(f"#{tc} {answer}")