def solution(array):
    array = sorted(array)
    answer = array[(len(array)-1)//2]
    return answer