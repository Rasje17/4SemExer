def minmax_decision(state):

    def max_value(state):
        if is_terminal(state):
            return utility_of(state)
        v = -infinity
        for a, s in successors_of(state):
            v = max(v, min_value(s))
        print('V: ' + str(v))
        return v

    def min_value(state):
        if is_terminal(state):
            return utility_of(state)
        v = infinity
        for a, s in successors_of(state):
            v = min(v, max_value(s))
        return v

    infinity = float('inf')
    action, state = argmax(successors_of(state), lambda a: min_value(a[1]))
    return action


def is_terminal(state):
    x_val = state.count('X')
    O_val = state.count('O')
    if x_val + O_val >= 9:
        return True

    if str(state).isalpha():
        return True

    for c in [0, 3, 6]:
        if state[c + 0] == state[c + 1] == state[c + 2]:
            return True

    for c in [0, 1, 2]:
        if state[c] == state[c +3] == state[c+6]:
            return True

    if state[0] == state[4] == state[8]:
        return True

    if state[6] == state[4] == state[2]:
        return True

    return False

    """
    returns True if the state is either a win or a tie (board full)
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

def utility_of(state):
    result = 0

    # test for x win
    for c in [0, 3, 6]:
        if state[c + 0] == state[c + 1] == state[c + 2] == 'X':
            result = 1

    for c in [0, 1, 2]:
        if state[c] == state[c + 3] == state[c + 6] == 'X':
            result = 1

    if state[0] == state[4] == state[8] == 'X':
        result = 1

    if state[6] == state[4] == state[2] == 'X':
        result = 1

    #test for O win
    for c in [0, 3, 6]:
        if state[c + 0] == state[c + 1] == state[c + 2] == 'O':
            result = -1

    for c in [0, 1, 2]:
        if state[c] == state[c + 3] == state[c + 6] == 'O':
            result = -1

    if state[0] == state[4] == state[8] == 'O':
        result = -1

    if state[6] == state[4] == state[2] == 'O':
        result = -1

    """
    returns +1 if winner is X (MAX player), -1 if winner is O (MIN player), or 0 otherwise
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """
    return result


def successors_of(state):
    insert = 'X'
    if state.count('X') > state.count('O'):
        insert = 'O'
    returnList =[]
    for i in state:
        if i != 'X' and i != 'O':
            returnList.append((i, state[:i]+[insert]+state[(i+1):]))
    #print(returnList)
    return returnList

    """
    returns a list of tuples (move, state) as shown in the exercise slides
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

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
    print("Game is over! Congratulations!")


def argmax(iterable, func):
    return max(iterable, key=func)


if __name__ == '__main__':
    main()
