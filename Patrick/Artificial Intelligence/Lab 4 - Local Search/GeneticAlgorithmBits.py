import math
import random


p_mutation = 0.2        # probability of mutation happening
num_of_generations = 30    # maximum number of generations - stops before this point if a solution is found


def genetic_algorithm(population, fitness_fn, minimal_fitness):
    for generation in range(num_of_generations):
        print("Generation {}:".format(generation))
        print_population(population, fitness_fn)

        new_population = set()

        for i in range(len(population)):
            mother, father = random_selection(population, fitness_fn)
            child = reproduce(mother, father)

            if random.uniform(0, 1) < p_mutation:
                child = mutate(child)

            new_population.add(child)

        # Add new population to population, use union to disregard
        # duplicate individuals
        population = population.union(new_population)

        fittest_individual = get_fittest_individual(population, fitness_fn)

        if minimal_fitness <= fitness_fn(fittest_individual):
            break

    print("Final generation {}:".format(generation))
    print_population(population, fitness_fn)

    return fittest_individual


def print_population(population, fitness_fn):
    for individual in population:
        fitness = fitness_fn(individual)
        print("{} - fitness: {}".format(individual, fitness))


def reproduce(mother, father):
    '''
    Reproduce two individuals with single-point crossover
    Return the child individual
    '''

    crossover_point = random.randint(0, (len(mother)-1))    # chooses crossover point randomly

    mother_part = mother[:crossover_point]
    father_part = father[crossover_point:]

    child = mother_part + father_part       # combines parts of two parents into a new individual

    return child


def mutate(individual):
    '''
    Mutate an individual by randomly assigning one of its bits
    Return the mutated individual
    '''

    r = random.randint(0, len(individual)-1)    # chooses random index

    mutation = individual[:r]   # adds everything from before that index

    if individual[r] == 0:  # flips the bit of the chosen index; if it was 0, add a 1 instead, and vice versa
        mutation += (1,)
    else:
        mutation += (0,)

    mutation += individual[(r+1):]  # then add the rest of the original bitstring

    return mutation


def random_selection(population, fitness_fn):
    """
    Compute fitness of each in population according to fitness_fn and add up
    the total. Then choose 2 from sequence based on percentage contribution to
    total fitness of population
    Return selected variable which holds two individuals that were chosen as
    the mother and the father
    """

    # Python sets are randomly ordered. Since we traverse the set twice, we
    # want to do it in the same order. So let's convert it temporarily to a
    # list.
    ordered_population = list(population)
    total_fitness = 0

    for individual in ordered_population:       # adds up total fitness in the population, this is used in roulette wheel selection
        total_fitness += fitness_fn(individual)

    # picks two parents using roulette wheel selection
    selection1 = selection(ordered_population, total_fitness, fitness_fn)
    selection2 = selection(ordered_population, total_fitness, fitness_fn)

    selected = selection1, selection2

    return selected


def selection(elements, total_fitness, fitness_fn):     # picks two parents using roulette wheel selection
    r = random.randint(0, total_fitness)
    temp = 0
    for individual in elements:
        temp += fitness_fn(individual)
        if temp >= r:
            return individual


def fitness_function(individual):
    enu = enumerate(reversed(individual))
    fitness = 0
    for pair in enu:
            fitness += math.pow(2, pair[0]) * pair[1]   # calculates the value of the bitstring
    '''
    Computes the decimal value of the individual
    Return the fitness level of the individual

    Explanation:
    enumerate(list) returns a list of pairs (position, element):

    enumerate((4, 6, 2, 8)) -> [(0, 4), (1, 6), (2, 2), (3, 8)]

    enumerate(reversed((1, 1, 0))) -> [(0, 0), (1, 1), (2, 1)]
    '''

    return fitness


def get_fittest_individual(iterable, func):
    return max(iterable, key=func)      # fittest individual is the one with the highest value


def get_initial_population(n, count):
    '''
    Randomly generate count individuals of length n
    Note since its a set it disregards duplicate elements.
    '''
    return set([
        tuple(random.randint(0, 1) for _ in range(n))
        for _ in range(count)
    ])


def main():
    bitLength = 6       # amount of bits in each individual
    numIndividuals = 4      # amount of individuals at start
    minimal_fitness = math.pow(2, bitLength) - 1    # breakpoint; if the fitness reaches this level, it is deemed good enough, and the loop is stopped.

    # Curly brackets also creates a set, if there isn't a colon to indicate a dictionary
    '''
    initial_population = {
        (1, 1, 0),
        (0, 0, 0),
        (0, 1, 0),
        (1, 0, 0)
    }
    '''
    initial_population = get_initial_population(bitLength, numIndividuals)

    fittest = genetic_algorithm(initial_population, fitness_function, minimal_fitness)
    print('Fittest Individual: ' + str(fittest))


if __name__ == '__main__':
    #pass
    main()
