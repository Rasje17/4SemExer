A = "A"
B = "B"
percepts = []
table = {
    ((A, "Clean"),): "Right",
    ((A, "Dirty"),): "Suck",
    ((B, "Clean"),): "Left",
    ((B, "Dirty"),): "Suck",
    ((A, "Clean"), (A, "Dirty")): "Suck",
    ((A, "Clean"), (B, "Clean")): "Left",
    ((A, "Clean"), (B, "Dirty")): "Suck",
    ((A, "Dirty"), (A, "Clean")): "Right",
    ((B, "Clean"), (A, "Clean")): "Right",
    ((B, "Clean"), (A, "Dirty")): "Suck",
    ((B, "Dirty"), (B, "Clean")): "Left",
    ((B, "Dirty"), (B, "Dirty")): "Suck",
    ((A, "Clean"), (B, "Dirty"), (B, "Clean")): "Left",
    ((A, "Clean"), (B, "Clean"), (A, "Clean")): "Done",
    # continue statespace ...
}

def LOOKUP(percepts, table):
    action = table.get(tuple(percepts))
    return action

def TABLE_DRIVEN_AGENT(percept):
    percepts.append(percept)
    action = LOOKUP(percepts, table)
    return action

def run():
    print("Action\tPercepts")
    print(TABLE_DRIVEN_AGENT((A, "Clean")), "\t", percepts)
    print(TABLE_DRIVEN_AGENT((B, "Dirty")), "\t", percepts)
    print(TABLE_DRIVEN_AGENT((B, "Clean")), "\t", percepts)

run()