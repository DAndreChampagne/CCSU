
def Sum(n):
    result = 0
    for i in range(1, n+1):
        for j in range(1, i+1):
            for k in range(1, j+1):
                result += 1
    return result


for x in range(0, 10):
    print(f"sum({x}) = {Sum(x)}")
