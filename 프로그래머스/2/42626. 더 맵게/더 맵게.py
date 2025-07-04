from heapq import heappop, heappush

def solution(S, K):
    heap = []
    for i in S:
        heappush(heap, i)

    cnt = 0
    while heap[0] < K:
        heappush(heap, heappop(heap) + heappop(heap) * 2)
        cnt += 1
        
        if len(heap) == 1 and heap[0] < K:
            return -1
    return cnt