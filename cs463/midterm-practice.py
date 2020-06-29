
# def largest(x, left: int, right: int):
#     if left == right:
#         return left
#     m = int((right+left)/2)
#     y = largest(x, left, m)
#     z = largest(x, m+1, right)
#     return y if x[y] > x[z] else z
#
#
# data = [1, 2, 10, 3, 4, 5]
# print(largest(data, 0, len(data)-1))


def test(n):
    result = 0
    for i in range(0, 3*n):
        for j in range(0, 5*n):
            for k in range(1, 7*j*j + 5*j):
                result += 1
    return result


for i in range(0,10):
    print(f"test({i}) = {test(i)}")
