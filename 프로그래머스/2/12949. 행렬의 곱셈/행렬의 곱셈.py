def solution(arr1, arr2):
    result = []
    m, n, r = len(arr1), len(arr1[0]), len(arr2[0])
    for i in range(m):
        array = arr1[i]
        info = []
        for j in range(r):
            total = 0
            for k in range(n):
                total += array[k] * arr2[k][j]
            info.append(total)
        result.append(info)
    return result