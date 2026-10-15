def solution(arr1, arr2):
    result = []
    m, n, r = len(arr1), len(arr1[0]), len(arr2[0])
    for i in range(m):
        array = arr1[i]
        info = []
        for j in range(r):
            hap = 0
            for k in range(n):
                hap += array[k] * arr2[k][j]
            info.append(hap)
        result.append(info)
    return result