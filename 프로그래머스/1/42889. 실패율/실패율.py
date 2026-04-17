def solution(N, stages):
    people = len(stages)
    failrate = {}
    for i in range(1, N+1):
        if people != 0:
            failrate[i] = stages.count(i) / people
            people -= stages.count(i)
        else:
            failrate[i] = 0
    print(failrate)

    return sorted(failrate, key = lambda i : failrate[i], reverse = True)