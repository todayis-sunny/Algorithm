from collections import deque

def solution(queue1, queue2):
    # 두 큐의 합
    sq1, sq2 = sum(queue1), sum(queue2)
    if (sq1 + sq2) % 2 == 1:
        return -1
    # 큐로 선언
    dq1 = deque(queue1)
    dq2 = deque(queue2)
    # 최대횟수 선언
    limit = len(queue1) * 4
    answer = 0
    while sq1 != sq2:
        if answer > limit:
            return -1
        if sq1 < sq2:
            e = dq2.popleft()
            sq1 += e
            sq2 -= e
            dq1.append(e)
        else:
            e = dq1.popleft()
            sq1 -= e
            sq2 += e
            dq2.append(e)
        answer += 1

    return answer




# 목표
    # 각 큐의 원소 합이 같도록 만드려고 함

# 과정
    # 해당 q에서 원소를 뽑으면 다른 q에 삽입
    # q1에서 원소를 뽑으면 q2에 삽입
    # q2에서 원소를 뽑으면 q1에 삽입

# 반환
    # 작업의 최소 횟수
    # 어떤 방법으로도 할 수 없다면, return -1
        # 1) 모든 원소들의 합 케이스를 추출해서 각 합을 구함
            # 1-1) 모든 원소의 합이 홀수면 불가능
            # 1-2) 모든 원소의 합의 짝수이고 이에 해당하는 절반의 케이스가 없으면 불가능
            
# 후보
    # 완탐
    # 완탐을 하게 되면 2 ** 300,000
    # 불가능해 보임
    
    # 
    
    