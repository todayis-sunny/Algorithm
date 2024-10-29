import heapq

def solution(S, K):
    heap = []
    for i in S:
        heapq.heappush(heap, i)

    cnt = 0
    while heap[0] < K:
        heapq.heappush(heap, heapq.heappop(heap) + heapq.heappop(heap) * 2)
        cnt += 1
        
        if len(heap) == 1 and heap[0] < K:
            return -1
    return cnt