

def robot_path(rows, columns):
    if rows == 1 and columns == 1:
        # base case, with a 1x1 grid
        return 1
    elif rows == 1 and columns > 1:
        # last row, so we can only move right
        return robot_path(rows, columns - 1)
    elif columns == 1 and rows > 1:
        # last column, so we can only move down
        return robot_path(rows - 1, columns)
    else:
        # can either move right or left, so we count both
        return robot_path(rows - 1, columns) + robot_path(rows, columns - 1)


def find_target_sum(x, t):
    if t == 0:
        return True
    if len(x) == 0 or t < 0:
        return False

    # remove current element from consideration
    # update the target sum
    a = find_target_sum(x[:-1], t - x[-1])

    # # keep current element and branch
    b = find_target_sum(x[:-1], t)

    return a or b


# helper function to take a flat array and make it triangular
def make_triangular_array(x):
    result = []
    level = 1
    i = 0

    while i < len(x):
        temp = []
        for j in range(0, level):
            temp.append(x[i])
            i += 1
        result.append(temp)
        level += 1

    return result


# I'm using the bottom up method to compute this.
# basically, we start at the bottom row. calculated_values at the start will be a copy of
# the bottom row of the triangle. Then we work backwards up the triangle, calculating the minimum
# weight of the values that are near the current position in the triangle.
def min_sum_descent(values):
    values = make_triangular_array(values)
    # print(f"\033[4m{values[x]}\033[0m ", end="")

    n = len(values)  # how many levels to the triangle
    calculated_values = [0] * len(values)  # create an array to hold the calculated paths

    # initialize the path with the values from the bottom row of the triangle
    calculated_values = [values[n-1][i] for i in range(0, len(values[n-1]))]

    # start with the second row from the bottom and work our way up
    for i in range(n-2, -1, -1):
        for j in range(0, len(values[i])):  # go through each value in the row
            v = values[i][j]  # current value
            v1 = calculated_values[j]  # row+1 value 1
            v2 = calculated_values[j+1]  # row+1 value 2
            calculated_values[j] = v + min(v1, v2)  # calculate the value + the min of the two neighbors

    # returning [0] since that will represent the starting point at the tip of the triangle
    # the calculations threw me at first because index 0 might not be the smallest value.
    # but it's the only one that represents a full path, not a partial path
    return calculated_values[0]


print(f"possible paths = {robot_path(4, 5)}")

print(f"expected = 14, actual = ", min_sum_descent([2, 5, 4, 1, 4, 7, 8, 6, 9, 6]), end="")
print()

print(f"find_target_sum = {find_target_sum([5, 2, 4], 6)}")
