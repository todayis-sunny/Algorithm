def solution(num_list):
    num1 = 1
    num2 = sum(num_list) ** 2
    for num in num_list:
        num1 *= num
        if num1 >= num2:
            return 0
    return 1