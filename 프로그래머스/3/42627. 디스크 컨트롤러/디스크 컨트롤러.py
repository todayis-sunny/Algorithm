import heapq

def solution(jobs):
    result = 0
    curr = 0 # 현재시간
    count = 0 # 처리개수
    start = -1 # 마지막 완료시간
    heap = []
    
    while count < len(jobs):
        for job in jobs:
            if start < job[0] <= curr:
                heapq.heappush(heap,[job[1],job[0]])
        
        if heap:
            current = heapq.heappop(heap)
            start = curr
            curr += current[0]
            result += curr - current[1] #요청으로부터 처리시간
            count += 1
        else:
            curr += 1
            
    return result // len(jobs)
