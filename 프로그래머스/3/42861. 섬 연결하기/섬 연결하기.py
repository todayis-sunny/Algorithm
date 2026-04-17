def solution(n, costs):
    answer = 0
    costs.sort(key = lambda x:x[2])
    tree = set([0])
    while len(tree) < n: 
        for c in costs:
            if len(set(c[:2]) & tree) == 1:
                tree.update(c[:2])
                answer += c[2]
                break
    return answer