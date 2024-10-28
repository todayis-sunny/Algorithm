def solution(order):
    latte = sum([1 for latte in order if "latte" in latte])
    america = sum([1 for america in order if "americano" in america])
    anything = sum([1 for anything in order if "anything" in anything])
    return (latte * 5000) + (america * 4500) + (anything * 4500)