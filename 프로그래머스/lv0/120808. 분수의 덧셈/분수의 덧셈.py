from math import gcd

def get_lcm(A, B):
    return A * B // gcd(A, B)

def get_gcd(A, B):
    return gcd(A, B)

def solution(numer1, denom1, numer2, denom2):
    answer = []
    
    denom_lcm = get_lcm(A=denom1, B=denom2)
    
    answer_numer = numer1 * (denom_lcm // denom1) + numer2 * (denom_lcm // denom2)
    answer_denom = denom_lcm
    numer_denom_gcd = get_gcd(A=answer_numer, B=answer_denom)
    
    answer = [answer_numer // numer_denom_gcd, answer_denom // numer_denom_gcd]
    
    return answer