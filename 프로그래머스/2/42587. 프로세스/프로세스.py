def solution(priorities, location):
    queue =  [(i,p) for i,p in enumerate(priorities)]
    answer = 0
    while True:
        curr = queue.pop(0)
        if any(curr[1] < q[1] for q in queue):
            queue.append(curr)
        else:
            answer += 1
            if curr[0] == location:
                return answer