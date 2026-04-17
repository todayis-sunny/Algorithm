def solution(k, score):
    ans = []
    arr = []
    for i in score:
        if len(arr) < k:
            arr.append(i)
        else:
            if min(arr)<i:
                arr.remove(min(arr))
                arr.append(i)
        ans.append(min(arr))
    return ans