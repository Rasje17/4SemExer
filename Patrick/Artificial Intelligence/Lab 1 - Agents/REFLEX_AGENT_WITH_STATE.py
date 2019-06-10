A = "A"
B = "B"
C = "C"
D = "D"
state = {}
action = None
model = {A: None, B: None, C: None, D: None}

RULE_ACTION = {     # defining possible actions
    1: "Suck",
    2: "Right",
    3: "Left",
    4: "Down",
    5: "Up",
    6: "NoOp"
}

rules = {       # defining what action to do based on environment
    (A, "Dirty"): 1,
    (B, "Dirty"): 1,
    (C, "Dirty"): 1,
    (D, "Dirty"): 1,
    (A, "Clean"): 2,
    (B, "Clean"): 4,
    (C, "Clean"): 3,
    (D, "Clean"): 5,
    (A, B, C, D, "Clean"): 6
}

Enviornment = {     # initial statespace
    A: "Dirty",
    B: "Clean",
    C: "Dirty",
    D: "Clean",
    "Current": A        # will only run NoOp if in the starting position. If all states are clean, but we are currently in C, we will go back to the starting environment before choosing NoOp.
}

def INTERPRET_INPUT(input):
    return input

def RULE_MATCH(state, rules):
    rule = rules.get(tuple(state))
    return rule

def UPDATE_STATE(state, action, percept):
    (location, status) = percept
    state = percept
    if model[A] == model[B] == model[C] == model[D] == "Clean":
        state = (A, B, C, D, "Clean")
    model[location] = status
    return state

def REFLEX_AGENT_WITH_STATE(percept):
    global state, action
    state = UPDATE_STATE(state, action, percept)
    rule = RULE_MATCH(state, rules)
    action = RULE_ACTION[rule]
    return action

def Sensors():      # detecting state of the current environment
    location = Enviornment["Current"]
    return (location, Enviornment[location])

def Actuators(action):      # how each action impacts the environment
    location = Enviornment["Current"]
    if action == "Suck":
        Enviornment[location] = "Clean"
    elif action == "Right" and location == A:
        Enviornment["Current"] = B
    elif action == "Down" and location == B:
        Enviornment["Current"] = C
    elif action == "Left" and location == C:
        Enviornment["Current"] = D
    elif action == "Up" and location == D:
        Enviornment["Current"] = A

def run(n):
    print("\tCurrent\tNew")
    print("Location\tStatus\tAction\tLocation\tStatus")
    for i in range(1, n):       # n not included!
        (location, status) = Sensors()
        print("{:12s}{:8s}".format(location, status), end="")
        action = REFLEX_AGENT_WITH_STATE(Sensors())
        Actuators(action)
        (location, status) = Sensors()
        print("{:8s}{:12s}{:8s}".format(action, location, status))

run(20)