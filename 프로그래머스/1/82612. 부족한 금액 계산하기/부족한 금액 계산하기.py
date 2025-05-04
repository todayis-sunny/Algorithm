def solution(price, money, count):
    answer = count * (price + price * (count)) // 2 - money
    return answer if answer > 0 else 0