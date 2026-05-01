def solution(priorities, location):
    queue =  [(i,p) for i,p in enumerate(priorities)]
    result = 0
    while True:
        curr = queue.pop(0)
        if any(curr[1] < q[1] for q in queue):
            queue.append(curr)
        else:
            result += 1
            if curr[0] == location:
                return result