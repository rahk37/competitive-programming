# Get the number of tests
num_tests = int(input())
BOARD_LEN = 8
# Save the initial positions in an array
tests = []
# Initialize chess board
board = [[0] * BOARD_LEN] * BOARD_LEN
# Initialize moves
X_MOVES = [1, 2, 2, 1, -1, -2, -2, -1]
Y_MOVES = [-2, -1, 1, 2, 2, 1, -1, -2]


def check_range(x, y):
    """
    Checks if the position is in range
    :param x: x position
    :param y: y position
    :return: true if it is in range, false if it is not in range
    """
    return 0 <= x < 8 and 0 <= y < 8


def solve(init_pos):
    """
    This function finds the the furthest hiding places on the board
    Given an initial position for the knight chess piece.
    :param init_pos: Initial Position
    :return:
    """
    # Set the Initial position for the knight as row and col variables
    row = ord(init_pos[0]) - 97
    col = int(init_pos[1]) - 1
    board[row][col] = 0
    queue = [(row, col)]

    for i in range(8):
        val = queue.append((X_MOVES[i], Y_MOVES[i]))
        # Check if the move is within the board
        check_range(val)


for x in range(num_tests):
    tests.append(input())
    solve(tests[x])