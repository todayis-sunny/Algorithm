def solution(elements):
    cycle = elements + elements
    s = []
    for i in range(len(elements)):
        for j in range(len(elements)):
            s.append(sum(cycle[i : i + j]))
    return len(set(s))