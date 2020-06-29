

def robot_path(rows, columns, blocked_cells, robot_path_memo={}):  # added blocked_cells parameter

    if (rows, columns) in robot_path_memo:
        print(f'pulling from memo: ({rows},{columns}) = {robot_path_memo[(rows, columns)]}')
        return robot_path_memo[(rows, columns)]

    if rows == 1 and columns == 1:
        # base case, with a 1x1 grid
        return 1
    elif (rows, columns) in blocked_cells:
        return 0
    elif rows == 1 and columns > 1:
        # last row, so we can only move right
        v = robot_path(rows, columns - 1, blocked_cells, robot_path_memo)
        robot_path_memo[(rows, columns - 1)] = v
        return v
    elif columns == 1 and rows > 1:
        # last column, so we can only move down
        v = robot_path(rows - 1, columns, blocked_cells, robot_path_memo)
        robot_path_memo[(rows - 1, columns)] = v
        return v
    else:
        # can either move right or down, so we count both
        down = robot_path(rows - 1, columns, blocked_cells, robot_path_memo)
        robot_path_memo[(rows - 1, columns)] = down

        right = robot_path(rows, columns - 1, blocked_cells, robot_path_memo)
        robot_path_memo[(rows, columns - 1)] = right

        return down+right


invalid_cells = [
    (1, 3),
    (2, 4),
    (3, 4)
]

print(f"possible paths = {robot_path(4, 5, invalid_cells)}")

