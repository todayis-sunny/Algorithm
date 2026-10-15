def solution(s):
    li = []
    for i in s.split("},"):
        li.append(i.replace("{","").replace("}","").split(","))
    li.sort(key = len)
    result = []
    for i in li:
        for j in i:
            if j not in result:
                result.append(j)
    return list(map(int, result))