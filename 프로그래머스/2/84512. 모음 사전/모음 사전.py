def solution(word):
    result = 0
    dic = ["A", "E", "I", "O", "U"]
    li = [5 ** i for i in range(len(dic))]
    
    for i in range(len(word) - 1, -1, -1):
        idx = dic.index(word[i])
        for j in range(5 - i):
            result += li[j] * idx
        result += 1
    return result