def minmax_decision(state):

    def max_value(state):
        if is_terminal(state):
            return utility_of(state)
        v = -infinity
        for (a, s) in successors_of(state):
            v = max(v, min_value(s))
        print('V: ' + str(v))
        return v

    def min_value(state):
        if is_terminal(state):
            return utility_of(state)
        v = infinity
        for (a, s) in successors_of(state):
            v = min(v, max_value(s))
        return v

    infinity = float('inf')
    action, state = argmax(successors_of(state), lambda a: min_value(a[1]))
    return action


def is_terminal(state):
    """
    returns True if the state is either a win or a tie (board full)
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

    is_done = True

    for i in range(len(state)):
        if i in state:
            is_done = False

    for i in [0, 3, 6]:
        if state[i] == state[i+1] == state[i+2]:
            return True

    for i in [0, 1, 2]:
        if state[i] == state[i+3] == state[i+6]:
            return True

    if state[0] == state[4] == state[8]:
        return True

    if state[2] == state[4] == state[6]:
        return True

    return is_done


def utility_of(state):
    """
    returns +1 if winner is X (MAX player), -1 if winner is O (MIN player), or 0 otherwise
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

    winner = 0

    for i in [0, 3, 6]:
        if state[i] == state[i + 1] == state[i + 2] == 'O':
            winner = -1
    for i in [0, 3, 6]:
        if state[i] == state[i + 1] == state[i + 2] == 'X':
            winner = 1

    for i in [0, 1, 2]:
        if state[i] == state[i + 3] == state[i + 6] == 'O':
            winner = -1
    for i in [0, 1, 2]:
        if state[i] == state[i + 3] == state[i + 6] == 'X':
            winner = 1

    if state[0] == state[4] == state[8] == 'O':
        winner = -1
    if state[0] == state[4] == state[8] == 'X':
        winner = 1

    if state[2] == state[4] == state[6] == 'O':
        winner = -1
    if state[2] == state[4] == state[6] == 'X':
        winner = 1

    return winner


def successors_of(state):
    """
    returns a list of tuples (move, state) as shown in the exercise slides
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

    turn = 'X'
    if state.count('X') > state.count('O'):
        turn = 'O'

    moves = []

    for i in range(len(state)):
        if str(state[i]).isdigit():
            moves.append((i, state[:i] + [turn] + state[i+1:]))
            # print(moves)

    return moves


def display(state):
    print("-----")
    for c in [0, 3, 6]:
        print(state[c + 0], state[c + 1], state[c + 2])


def main():
    board = [0, 1, 2, 3, 4, 5, 6, 7, 8]
    while not is_terminal(board):
        board[minmax_decision(board)] = 'X'
        if not is_terminal(board):
            display(board)
            board[int(input('Your move? '))] = 'O'
    display(board)


def argmax(iterable, func):
    return max(iterable, key=func)


if __name__ == '__main__':
    main()
