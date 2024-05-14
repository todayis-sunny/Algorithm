def solution(friends, gifts):
    length = len(friends)
    dic = dict()
    # 초기화 진행.
    for i in range(len(friends)):
        dic[friends[i]] = i
    arr = [[0] * length for _ in range(length)]
    table = [0] * length

    
    # 데이터 입력처리.
    for gift in gifts:
        fromF, toF = gift.split()
        fromK, toK = dic[fromF], dic[toF]
        arr[fromK][toK] += 1
        table[fromK] += 1
        table[toK] -= 1

    # 정답처리.
    answer = [0] * length
    for i in range(length):
        for j in range(i+1, length):
            if arr[i][j] > arr[j][i]:
                answer[i] += 1
            elif arr[i][j] < arr[j][i]:
                answer[j] += 1
            else:
                if table[i] > table[j]:
                    answer[i] += 1
                elif table[i] < table[j]:
                    answer[j] += 1

    return max(answer)