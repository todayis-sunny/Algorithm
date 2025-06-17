def solution(k, score):
    answer = []
    arr = []
    for i in score:
        if len(arr) < k:
            arr.append(i)
        else:
            if min(arr)<i:
                arr.remove(min(arr))
                arr.append(i)
        answer.append(min(arr))
    return answer