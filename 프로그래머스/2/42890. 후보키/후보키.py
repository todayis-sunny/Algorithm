def solution(relation):
    reuslt = list()
    for i in range(1, 1 << len(relation[0])):
        tmpSet = set()
        for j in range(len(relation)):
            tmp = ''
            for k in range(len(relation[0])):
                if i & (1 << k):
                    tmp += str(relation[j][k])
            tmpSet.add(tmp)

        if len(tmpSet) == len(relation):
            nD = True
            for num in reuslt:
                if (num & i) == num:
                    nD = False
                    break
            if nD:
                reuslt.append(i)
    return len(reuslt)