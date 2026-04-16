import math

def solution(n, k):
    answer = []
    number = [i for i in range(1, n + 1)]

    while number:
        perm = math.factorial(n - 1) # 현재 자리수의 순열 개수
        idx = (k - 1) // perm # k 값을 인덱스에 맞추기 위한 1 걈소
        answer.append(number.pop(idx)) # 위에서 구한 인덱스 값을 answer에 삽입
        k = k % perm # 현재 자리수의 순열 개수로 나눈 나머지로 조정
        n -= 1 # n을 줄여 다음 자리로 이동

    return answer