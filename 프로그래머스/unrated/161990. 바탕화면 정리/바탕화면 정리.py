def solution(wallpaper):
    answer = [-1, 50, -1, -1]
    for i in range(len(wallpaper)):
        if '#' in wallpaper[i]:
            if answer [0] == -1:
                answer[0] = i
            answer[2] = i + 1
        for j in range(len(wallpaper[0])):
            if wallpaper[i][j] == '#':
                if j < answer[1]:
                    answer[1] = j
                if answer[3] < j + 1:
                    answer[3] = j + 1

    return answer