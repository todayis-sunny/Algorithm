def solution(price, money, count):
    return max(0, count * (price + price * (count)) // 2 - money)