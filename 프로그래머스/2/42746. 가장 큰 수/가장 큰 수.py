def solution(numbers):
    numbers = list(map(str, numbers))
    numbers.sort(key=lambda x: x * 3, reverse=True)

    # 정렬 후 첫 번째가 0이면 나머지도 모두 0
    if numbers[0] == "0":
        return "0"

    return "".join(numbers)