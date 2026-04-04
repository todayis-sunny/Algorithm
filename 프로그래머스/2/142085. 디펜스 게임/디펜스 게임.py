from heapq import heappop, heappush

def solution(n, k, enemy):
    result, total = 0, 0
    heap = []
    
    for e in enemy:
        heappush(heap, -e)
        total += e
        if total > n:
            if k == 0: break
            total += heappop(heap) 
            k -= 1
        result += 1
    return result