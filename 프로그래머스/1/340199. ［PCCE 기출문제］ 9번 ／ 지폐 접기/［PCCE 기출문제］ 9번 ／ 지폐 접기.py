def solution(wallet, bill):
    ans = 0
    
    while min(bill) > min(wallet) or max(bill) > max(wallet):
            if bill[0] > bill[1]:
                bill[0] //= 2
            else:
                bill[1] //= 2
            ans += 1
    return ans