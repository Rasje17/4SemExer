A = "A"
B = "B"
RULE_ACTION = {     # defining actions
    1: "Suck",
    2: "Right",
    3: "Left",
    4: "NoOp"
}
rules = {       # defining which action to choose based on current environment state
    (A, "Dirty"): 1,
    (B, "Dirty"): 1,
    (A, "Clean"): 2,
    (B, "Clean"): 3,
    (A, B, "Clean"): 4
}

Enviornment = {     # initial statespace
    A: "Dirty",
    B: "Dirty",
    "Current": A
}

def INTERPRET_INPUT(input):
    return input

def RULE_MATCH(state, rules):
    rule = rules.get(tuple(state))
    return rule

def SIMPLE_REFLEX_AGENT(percept):
    state = INTERPRET_INPUT(percept)
    rule = RULE_MATCH(state, rules)
    action = RULE_ACTION[rule]
    return action

def Sensors():      # detecting state of the current environment
    location = Enviornment["Current"]
    return (location, Enviornment[location])

def Actuators(action):      # acting on the environment
    location = Enviornment["Current"]
    if action == "Suck":
        Enviornment[location] = "Clean"
    elif action == "Right" and location == A:
        Enviornment["Current"] = B
    elif action == "Left" and location == A:
        Enviornment["location"] = B

def run(n):
    print("\tCurrent\t\tNew")
    print("Location\tStatus\tAction\tLocation\tStatus")
    for i in range(1, n):       # n is not included!
        (location, status) = Sensors()
        print("{:12s}{:8s}".format(location, status), end="")
        action = SIMPLE_REFLEX_AGENT(Sensors())
        Actuators(action)
        (location, status) = Sensors()
        print("{:8s}{:12s}".format(action, location, status))

run(10)