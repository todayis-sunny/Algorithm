def solution(rank, attendance):

    arr = sorted([(x, i) for i, x in enumerate(rank) if attendance[i]])

    return arr[0][1] * 10000 + arr[1][1] * 100 + arr[2][1]