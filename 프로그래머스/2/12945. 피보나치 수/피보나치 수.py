def solution(n):
    MOD = 1_234_567
    f1, f2 = 0, 1
    for i in range(n):
        f1, f2 = f2, f1 + f2
    return f1 % MOD