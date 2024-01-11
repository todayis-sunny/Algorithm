# 1961. 숫자 배열 회전 <D2>

def solution(array, angle, index):
    value = ""
    if angle == 90:
        for a in range(len(array)):
            value += str(array[-(a + 1)][index])
    elif angle == 180:
        for a in range(len(array)):
            value += str(array[-(index + 1)][-(a + 1)])
    elif angle == 270:
        for a in range(len(array)):
            value += str(array[a][-(index + 1)])

    return value


for t in range(1, int(input()) + 1):
    N = int(input())
    arr = [list(map(int, input().split())) for _ in range(N)]
    print(f"#{t}")
    for n in range(N):
        print(solution(arr, 90, n), solution(arr, 180, n), solution(arr, 270, n))
