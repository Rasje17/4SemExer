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
        fringe.sort(key=((operator.attrgetter('TOTAL'))))     # sort fringe according to lowest total cost, in order to expand cheapest nodes first
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
    queue.append(node)      # FIFO
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
    element = queue[0]      # FIFO
    queue.remove(queue[0])
    return element

'''
Successor function, mapping the nodes to its successors
'''
def successor_fn(state):  # Lookup list of successor states
    return STATE_SPACE[state]  # successor_fn( 'C' ) returns ['F', 'G']


INITIAL_STATE = (('A', 'Dirty', 'Dirty'), 6)
GOAL_STATE1 = ('A', 'Clean', 'Clean')
GOAL_STATE2 = ('B', 'Clean', 'Clean')
# all stat spaces contain state, heuristic, path
STATE_SPACE = {
    ('A', 'Dirty', 'Dirty'):
        [(('A', 'Dirty', 'Dirty'), 1, 3), (('A', 'Clean', 'Dirty'), 1, 2), (('B', 'Dirty', 'Dirty'), 1, 3)],
    ('A', 'Clean', 'Dirty'):
        [(('A', 'Dirty', 'Dirty'), 1, 3), (('B', 'Clean', 'Dirty'), 1, 1)],
    ('B', 'Clean', 'Dirty'):
        [(('A', 'Clean', 'Dirty'), 1, 2), (('B', 'Clean', 'Clean'), 1, 0)],
    ('B', 'Clean', 'Clean'):
        [(('B', 'Clean', 'Dirty'), 1, 1)],
    ('B', 'Dirty', 'Dirty'):
        [(('A', 'Dirty', 'Dirty'), 1, 3), (('B', 'Dirty', 'Clean'), 1, 2)],
    ('B', 'Dirty', 'Clean'):
        [(('B', 'Dirty', 'Dirty'), 1, 3), (('A', 'Dirty', 'Clean'), 1, 1)],
    ('A', 'Dirty', 'Clean'):
        [(('B', 'Dirty', 'Clean'), 1, 2), (('A', 'Clean', 'Clean'), 1, 0)],
    ('A', 'Clean', 'Clean'):
        [(('A', 'Dirty', 'Clean'), 1, 1)]
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
