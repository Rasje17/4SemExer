import math
import random


p_mutation = 0.2
num_of_generations = 100


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

        if 1 > fitness_fn(fittest_individual):
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

    crossover_point = random.randint(0, (len(mother)-1))

    mother_part = mother[:crossover_point]
    father_part = father[crossover_point:]

    child = mother_part + father_part

    return child


def mutate(individual):
    '''
    Mutate an individual by randomly assigning one of its bits
    Return the mutated individual
    '''

    r = random.randint(0, len(individual)-1)

    mutation = individual[:r]

    random_int = random.randint(1, len(individual))
    mutation += (random_int,)

    mutation += individual[(r+1):]

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
    n = len(ordered_population[0])
    worst_fitness = (n*(n-1)/2)
    total_fitness = 0

    for individual in ordered_population:
        temp = (1 - (fitness_fn(individual)/worst_fitness))*100
        total_fitness += temp

    selection1 = selection(ordered_population, total_fitness, worst_fitness, fitness_fn)
    selection2 = selection(ordered_population, total_fitness, worst_fitness, fitness_fn)

    selected = selection1, selection2

    return selected


def selection(elements, total_fitness, worst_fitness, fitness_fn):
    r = random.randint(0, int(total_fitness))
    temp = 0
    for individual in elements:
        temp += (1 - (fitness_fn(individual)/worst_fitness))*100
        if temp >= r:
            return individual


def fitness_function(individual):
    '''
    Computes the decimal value of the individual
    Return the fitness level of the individual

    Explanation:
    enumerate(list) returns a list of pairs (position, element):

    enumerate((4, 6, 2, 8)) -> [(0, 4), (1, 6), (2, 2), (3, 8)]

    enumerate(reversed((1, 1, 0))) -> [(0, 0), (1, 1), (2, 1)]
    '''

    fitness = 0

    for x in range(len(individual)):
        for y in range(len(individual)):
            if x != y:
                if abs(x-y) == abs(individual[x]-individual[y]):
                    fitness += 1
                if individual[x] == individual[y]:
                    fitness += 1

    return fitness/2    # all conflicts will be counted twice


def get_fittest_individual(iterable, func):
    return min(iterable, key=func)


def get_initial_population(n, count):
    '''
    Randomly generate count individuals of length n
    Note since its a set it disregards duplicate elements.
    '''
    return set([
        tuple(random.randint(1, n) for _ in range(n))
        for _ in range(count)
    ])


def main():
    n = 4
    minimal_fitness = (n*(n-1)/2)

    # Curly brackets also creates a set, if there isn't a colon to indicate a dictionary
    '''
    initial_population = {
        (1, 1, 0),
        (0, 0, 0),
        (0, 1, 0),
        (1, 0, 0)
    }
    '''
    initial_population = get_initial_population(n, 4)   #(length, individuals)

    fittest = genetic_algorithm(initial_population, fitness_function, minimal_fitness)
    print('Fittest Individual: ' + str(fittest))


if __name__ == '__main__':
    #pass
    main()
