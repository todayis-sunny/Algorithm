def mapping(u_id, b_id):
    if len(u_id) != len(b_id):
        return False
    for i in range(len(u_id)):
        if b_id[i] == '*':
            continue
        if u_id[i] == b_id[i]:
            continue
        return False
    else:
        return True
    


def dfs(idx, arr, visited, case):
    # 마지막 인덱스까지 도착하면 종료
    if idx == len(arr):
        tmp = ''
        for v in visited:
            if v:
                tmp += '1'
            else:
                tmp += '0'
        case.add(tmp)
        return
    for i in arr[idx]:
        if visited[i]:
            continue
        else:
            visited[i] = True
            dfs(idx+1, arr, visited, case)
            visited[i] = False

def solution(user_id, banned_id):

    arr = [[] for _ in range(len(banned_id))]
    visited = [False] * len(user_id)
    
    for u in range(len(user_id)):
        for b in range(len(banned_id)):
            if mapping(user_id[u], banned_id[b]):
                arr[b].append(u)

    case = set()
    dfs(0, arr, visited, case)
    return len(case)