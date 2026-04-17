def solution(sequence):
    table = [[0 for _ in range(len(sequence) + 1)] for _ in range(2)]
    weight = 1
    for i in range(len(sequence)):
        table[0][i + 1] = table[0][i] + sequence[i] * weight
        table[1][i + 1] = table[1][i] - sequence[i] * weight
        weight *= -1
    return max(max(table[0]) - min(table[0]), max(table[1]) - min(table[1]))