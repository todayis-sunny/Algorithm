def solution(elements):
    answer = 0
    cycle = elements + elements
    s = []
    for i in range(len(elements)):
        for j in range(len(elements)):
            s.append(sum(cycle[i:i+j]))
    return len(set(s))