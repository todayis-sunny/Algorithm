def solution(a, b):
    num1 = int(str(a) + str(b))
    num2 = 2 * a * b
    if num1 >= num2:
        return num1
    return num2