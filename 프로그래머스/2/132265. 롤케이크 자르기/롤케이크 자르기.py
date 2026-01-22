from collections import Counter

def solution(topping):
    dic = Counter(topping)
    setDic = set()
    result = 0
    for i in topping:
        dic[i] -= 1
        setDic.add(i)
        if dic[i] == 0:
            dic.pop(i)
        if len(dic) == len(setDic):
            result += 1
    return result