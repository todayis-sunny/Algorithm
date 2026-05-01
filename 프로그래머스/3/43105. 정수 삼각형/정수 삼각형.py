
def memozation(triangle, row, col):
    left, right = -1, -1
    l = col - 1
    r = col
    if col == 0:
        triangle[row][col] += triangle[row - 1][col]
    elif col == row:
        triangle[row][col] += triangle[row - 1][col - 1]
    else:
        triangle[row][col] += max(triangle[row - 1][col], triangle[row - 1][col - 1])

def solution(triangle):
    length = len(triangle)
    if length == 1:
        return triangle[0][0]
    
    for row in range(1, length):
        for col in range(row+1):
            memozation(triangle, row, col)
    return max(triangle[-1])

