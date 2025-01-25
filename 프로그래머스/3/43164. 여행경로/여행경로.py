def solution(tickets):
    answer = []
    graph = {}
    for st, ed in tickets:
        if st not in graph:
            graph[st] = []
        graph[st].append(ed)
        graph[st].sort(reverse=True)

    stack = ["ICN"]
    while stack:
        tm = stack[-1]
        if tm not in graph or len(graph[tm]) == 0:
            answer.append(stack.pop())
        else:
            stack.append(graph[tm].pop())

    return answer[::-1]