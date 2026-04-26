def solution(k, score):
    result = []
    arr = []
    for i in score:
        if len(result) < k:
            arr.append(i)
        else:
            if min(arr)<i:
                arr.remove(min(arr))
                arr.append(i)
        result.append(min(arr))
    return result