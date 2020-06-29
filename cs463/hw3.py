
# https://stackoverflow.com/questions/2130016/splitting-a-list-into-n-parts-of-approximately-equal-length
def divideList(x, n):
    avg = len(x) / float(n)
    out = []
    last = 0.0

    while last < len(x):
        out.append(x[int(last):int(last + avg)])
        last += avg

    return out


def weight(coins):
    if len(coins) < 3:
        raise Exception("number of coins have to be >= 3")

    if len(coins) == 3:
        a, b, c = coins[0], coins[1], coins[2]
    else:
        # divide list into roughly 3 equal chunks and then
        # average the weights
        coins = divideList(coins, 3)
        a, b, c = sum(coins[0]) / len(coins[0]), sum(coins[1]) / len(coins[1]), sum(coins[2]) / len(coins[2])

    # Compare the coins/piles until we find a pair of the same weight.
    # Once we do it's the third one that is the different one, and then
    # we can compare the different one to one of the piles to determine
    # if it's lighter or heavier.
    if a == b:
        if (c < a): print("lighter")
        else:       print("heavier")
    elif a == c:
        if (b < a): print("lighter")
        else:       print("heavier")
    else:
        if (a < c): print("lighter")
        else:       print("heavier")
    
        
# driver
weight([1,1,1,1,1,1,1,1,1,1, 0.9])
weight([1,1,1,1,1,1,1,1,1,1, 1.1])
weight([1.1, 1,1,1,1,1,1,1,1,1,1])
weight([0.9, 1,1,1,1,1,1,1,1,1,1])