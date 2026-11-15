def solution(sequence):
    arr = [[0 for _ in range(len(sequence) + 1)] for _ in range(2)]
    weight = 1
    for i in range(len(sequence)):
        arr[0][i + 1] = arr[0][i] + sequence[i] * weight
        arr[1][i + 1] = arr[1][i] - sequence[i] * weight
        weight *= -1
    return max(max(arr[0]) - min(arr[0]), max(arr[1]) - min(arr[1]))