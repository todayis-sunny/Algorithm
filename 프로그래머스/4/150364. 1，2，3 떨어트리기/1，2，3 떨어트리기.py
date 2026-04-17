from collections import deque

def solution(edges, target):
    # target을 0-based에서 1-based로 변경
    target = [0] + target
    # 0-based 0..N
    length = len(target) 
    # 초기화 (정렬 전)
    temp = [[] for _ in range(length)]
    # 부모노드, 자식노드
    for pn, cn in edges:
        temp[pn].append(cn)
    # 트리 초기화
    tree = [deque(sorted(temp[i])) for i in range(length)]
    # 리프노드 개수 세기
    goal = 0
    for i in range(1, length):
        # 리프 노드인 경우(길이가 0인 경우) 그리고 리프노드에 숫자를 떨어트려서 합을 만들어야 하는 target이 0보다 큰 경우
        if len(tree[i]) == 0 and target[i] > 0:
            goal += 1
    # memo : 리프 노드에 떨어지는 위치
    memo = []
    # count : 리프 노드에 떨어진 Xn의 개수
    count = [0] * length
    # flag : 해당 리프노드의 만족 여부
    flag = [False] * length
    # goal 목표를 다 달성할 때까지 반복
    while goal:
        # 최상위 노드에서 부터 떨어 트림
        node = 1
        while len(tree[node]):
            # 다음 노드를 꺼내고
            nextNode = tree[node].popleft()
            # 다시 집어 넣기
            tree[node].append(nextNode)
            # 현재 바라보는 노드 변경
            node = nextNode
        # 리프노드에 도달한 경우
        memo.append(node)
        count[node] += 1
        # 충족하는지 검사
        # 목표로 하는 타겟에 1을 다 떨어트려도 넘는 경우 불가능
        if count[node] > target[node]:
            return [-1]
        # 가능한 최솟값과 최댓값 사이에 있으면 목표를 달성한 경우
        elif count[node] <= target[node] <= count[node] * 3 and not flag[node]:
            goal -= 1
            flag[node] = True
    # 떨어트리기가 가능하게 종료되고 result를 반환하기
    for i in range(len(memo)):
        # 해당 리프노드에 떨어진 번호
        node = memo[i]
        # 1로 표기하는 경우
        if (count[node] - 1) <= target[node] - 1 <= (count[node] - 1) * 3:
            memo[i] = 1
            target[node] -= 1
        # 2로 표기하는 경우
        elif (count[node] - 1) <= target[node ] - 2 <= (count[node] - 1) * 3:
            memo[i] = 2
            target[node] -= 2
        # 3로 표기하는 경우
        else:
            memo[i] = 3
            target[node] -= 3
        count[node] -= 1
    
    return memo