days = ["FRI","SAT","SUN","MON","TUE","WED","THU"]
months = [31, 29, 31, 30, 31, 30,31, 31, 30, 31, 30, 31]
def solution(a, b):
    result = 0
    
    for i in range(a - 1):
        result += months[i]
    
    result += b - 1
    result = result % 7
    
    return days[result]