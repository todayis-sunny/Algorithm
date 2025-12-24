def solution(sizes):
    return max(max(l) for l in sizes) * max(min(l) for l in sizes)