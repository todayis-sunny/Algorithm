import heapq as hq

def solution(n, works):

    heap = []
    for w in works:
        hq.heappush(heap, -w)
    while n and heap:
        w = hq.heappop(heap)
        if heap: # 다음 일거리가 있으면 target보다 -1
            d = min(heap[0] - w + 1, n)
            w += d
            n -= d
            if w == 0: # 일을 완료하면 무시
                continue
            hq.heappush(heap, w)
        else: # 다음 일거리가 없으면
            w = min(w + n, 0)
            return w ** 2

    return sum([x ** 2 for x in heap])