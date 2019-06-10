from math import floor

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
    """
    returns True if the state is either a win or a tie (board full)
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

    if isinstance(state, int):
        return True

    for e in state:
        if e > 2:
            return False

    return True


def utility_of(state):
    """
    returns +1 if winner is X (MAX player), -1 if winner is O (MIN player), or 0 otherwise
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

    print(state)

    # use length of board states to determine whose turn it is
    if len(state) % 2 == 0:
        winner = 1
    else:
        winner = -1

    return winner


def successors_of(state):
    """
    returns a list of tuples (move, state) as shown in the exercise slides
    :param state: State of the checkerboard. Ex: [0; 1; 2; 3; X; 5; 6; 7; 8]
    :return:
    """

    moves = []

    for i in range(len(state)):
        if state[i] > 2:
            for j in range(floor((state[i]-1)/2)):
                if j < 1:
                    continue
                '''
                temp_state = state
                temp_state[i] = i-j
                temp_state.append(j)
                # new = [i-j, j]
                moves.append((temp_state))
                '''
                moves.append((state[:i] + [i-j] + [j] + state[i+1:]))

    return moves

def display(state):
    print(state)


def main():
    n = 7
    state = [n]
    while not is_terminal(state):
        newstate = minmax_decision(state)
        state = newstate
        #if not is_terminal(state):
        display(newstate)
        #state[int(input('Your move? '))] = 'O'
    display(state)


def argmax(iterable, func):
    return max(iterable, key=func)


if __name__ == '__main__':
    main()
