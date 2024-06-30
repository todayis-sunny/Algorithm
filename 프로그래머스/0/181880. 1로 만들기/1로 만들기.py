def solution(num_list):
    return sum(len(bin(i)) - 3 for i in num_list)