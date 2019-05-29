from _socket import herror
import operator


class Node:  # Node has only PARENT_NODE, STATE, DEPTH
    def __init__(self, state, heuristic, parent=None, depth=0, path=0):
        self.STATE = state
        self.PARENT_NODE = parent
        self.DEPTH = depth
        self.HEURISTIC = heuristic
        self.PATH = path
        self.TOTAL = 0

    def path(self):  # Create a list of nodes from the root to this node.
        current_node = self
        path = [self]
        while current_node.PARENT_NODE:  # while current node has parent
            current_node = current_node.PARENT_NODE  # make parent the current node
            path.append(current_node)   # add current node to path
        return path

    def display(self):
        print(self)


    def __repr__(self):
        return 'State: ' + str(self.STATE) + ' - Depth: ' + str(self.DEPTH) + ' - TOTAL ' + str(self.TOTAL)


'''
Search the tree for the goal state and return path from initial state to goal state
'''
def TREE_SEARCH():
    fringe = []
    initial_node = Node(INITIAL_STATE[0], INITIAL_STATE[1])
    fringe = INSERT(initial_node, fringe)
    while fringe is not None:
        fringe.sort(key=((operator.attrgetter('TOTAL'))))
        node = REMOVE_FIRST(fringe)
        if node.STATE == GOAL_STATE1 or node.STATE == GOAL_STATE2:
            return node.path()
        children = EXPAND(node)
        fringe = INSERT_ALL(children, fringe)

        print("fringe: {}".format(fringe))


'''
Expands node and gets the successors (children) of that node.
Return list of the successor nodes.
'''
def EXPAND(node):
    successors = []
    children = successor_fn(node.STATE)
    for child in children:
        s = Node(child[0], child[1])  # create node for each in state list
        s.STATE = child[0]  # e.g. result = 'F' then 'G' from list ['F', 'G']
        s.PARENT_NODE = node
        s.DEPTH = node.DEPTH + 1
        s.PATH = node.PATH + child[2]
        s.TOTAL = node.PATH + child[2] + child[1]
        successors = INSERT(s, successors)
    return successors


'''
Insert node in to the queue (fringe).
'''
def INSERT(node, queue):
    queue.append(node)
    return queue



'''
Insert list of nodes into the fringe
'''

def INSERT_ALL(list, queue):
    for node in list:
        queue.append(node)
    return queue



'''
Removes and returns the first element from fringe
'''
def REMOVE_FIRST(queue):
    element = queue[0]
    queue.remove(queue[0])
    return element

'''
Successor function, mapping the nodes to its successors
'''
def successor_fn(state):  # Lookup list of successor states
    return STATE_SPACE[state]  # successor_fn( 'C' ) returns ['F', 'G']


INITIAL_STATE = ('A', 6)
GOAL_STATE1 = 'K'
GOAL_STATE2 = 'L'
# all stat spaces contain state, heuristic, path
STATE_SPACE = {
    'A': [('C', 5, 2), ('D', 2, 4), ('B', 5, 1)],
    'B': [('F', 5, 5), ('E', 4, 4)],
    'C': [('E', 4, 1)],
    'D': [('H', 1, 1), ('I', 2, 7), ('J', 1, 2)],
    'E': [('G', 4, 2), ('H', 1, 3)],
    'F': [('G', 4, 1)],
    'G': [('K', 0, 6)],
    'H': [('K', 0, 6), ('L', 0, 5)],
    'I': [('L', 0, 3)],
    'J': [],
    'K': [],
    'L': []
    }

'''
Run tree search and display the nodes in the path to goal node
'''
def run():
    path = TREE_SEARCH()
    print('Solution path:')
    for node in path:
        node.display()


if __name__ == '__main__':
    run()
