def solution(storey):
    result = 0

    while storey:
        remainder = storey % 10
        # 6 ~ 9
        if remainder > 5:
            result += (10 - remainder)
            storey += 10
        # 0 ~ 4
        elif remainder < 5:
            result += remainder
        # 5
        else:
            if (storey // 10) % 10 > 4:
                storey += 10
            result += remainder
        storey //= 10

    return result