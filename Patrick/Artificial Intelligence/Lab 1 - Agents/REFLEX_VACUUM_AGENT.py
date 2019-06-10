A = "A"
B = "B"
C = "C"
D = "D"

Enviornment = {
    A: "Dirty",
    B: "Clean",
    C: "Dirty",
    D: "Dirty",
    "Current": A
}

def REFLEX_VACUUM_AGENT(loc_st):
    if loc_st[1] == "Dirty":
        return "Suck"
    if loc_st[0] == A:
        return "Right"
    if loc_st[0] == B:
        return "Down"
    if loc_st[0] == C:
        return "Left"
    if loc_st[0] == D:
        return "Up"

def Sensors():
    location = Enviornment["Current"]
    return (location, Enviornment[location])

def Actuators(action):
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

def run(n, make_agent):
    print("\tCurrent\tNew")
    print("Loction\tStatus\tAction\tLocation\tStatus")
    for i in range(1, n):
        (location, status) = Sensors()
        print("{:12s}{:8s}".format(location, status), end="")
        action = make_agent(Sensors())
        Actuators(action)
        (location, status) = Sensors()
        print("{:8s}{:12s}{:8s}".format(action, location, status))

run(20, REFLEX_VACUUM_AGENT)