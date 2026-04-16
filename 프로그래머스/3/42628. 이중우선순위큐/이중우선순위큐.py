from heapq import heapify, heappush, heappop

def solution(operations):
    answer = []
    hq = []
    
    for operation in operations:
        alphabet, number = operation.split()
        number = int(number)

        if alphabet == 'I':
            heappush(hq, number)    
        else: # alphabet == 'D'
            if hq: # 빈 큐에서 데이터를 삭제하라는 연산이 주어졌을 시 무시
                if number == -1:
                    heappop(hq) # 최솟값을 삭제
                else:
                    hq.sort()
                    hq.pop() # 최댓값을 삭제
                    
    # 모든 연산을 처리한 후
    hq.sort()
    if hq: # 큐가 비어있지 않음
        answer = [hq[-1], hq[0]]
    else: # 큐가 비어있음
        answer = [0, 0]
        
    return answer