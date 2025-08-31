def solution(lottos, win_nums):
    rank = [6, 5, 4, 3, 2, 1]
    count = 0
    temp = 0
    for num in lottos:
        if num == 0:
            temp += 1
        elif num in win_nums:
            count += 1
    maxR = count + temp
    minR = count
    if maxR <= 1:
        maxR = 1
    if minR <= 1:
        minR = 1
    return [rank.index(maxR)+1, rank.index(minR)+1]