from random import shuffle


class CSP:
    def __init__(self, variables, domains, neighbours, constraints):
        self.variables = variables
        self.domains = domains
        self.neighbours = neighbours
        self.constraints = constraints

    def backtracking_search(self):
        return self.recursive_backtracking({})

    def recursive_backtracking(self, assignment):
        if self.is_complete(assignment):
            return assignment
        var = self.select_unassigned_variable(assignment)
        for value in self.order_domain_values(var, assignment):
            if self.is_consistent(var, value, assignment):
                assignment[var] = value
                result = self.recursive_backtracking(assignment)
                if result == assignment:
                    return result
                del assignment[var]
        return assignment

    def select_unassigned_variable(self, assignment):
        for variable in self.variables:
            if variable not in assignment:
                return variable

    def is_complete(self, assignment):
        for variable in self.variables:
            if variable not in assignment:
                return False
        return True


    def order_domain_values(self, variable, assignment):
        all_values = self.domains[variable][:]
        #shuffle(all_values)
        return all_values

    def is_consistent(self, variable, value, assignment):
        if not assignment:
            return True

        for constraint in self.constraints.values():
            for neighbour in self.neighbours[variable]:
                if neighbour not in assignment:
                    continue

                neighbour_value = assignment[neighbour]
                if not constraint(variable, value, neighbour, neighbour_value):
                    return False
        return True


def create_australia_csp():
    costaRica, panama, colombia, ecuador, peru, venezuela, guyana, suriname, frenchGuyana, brasil, bolivia, paraguay, chile, argentina, uruguay = 'Costa Rica', 'Panama', 'Colombia', 'Ecuador', 'Peru', 'Venezuela', 'Guyana', 'Suriname', 'French Guyana', 'Brasil', 'Bolivia', 'Paraguay', 'Chile', 'Argentina', 'Uruguay'
    values = ['Red', 'Green', 'Blue', 'Yellow']
    variables = [costaRica, panama, colombia, ecuador, peru, venezuela, guyana, suriname, frenchGuyana, brasil, bolivia, paraguay, chile, argentina, uruguay]
    domains = {
        costaRica: values[:],
        panama: values[:],
        colombia: values[:],
        ecuador: values[:],
        peru: values[:],
        venezuela: values[:],
        guyana: values[:],
        suriname: values[:],
        frenchGuyana: values[:],
        brasil: values[:],
        bolivia: values[:],
        paraguay: values[:],
        chile: values[:],
        argentina: values[:],
        uruguay: values[:]

    }
    neighbours = {
        costaRica: [panama],
        panama: [costaRica, colombia],
        colombia: [panama, venezuela, ecuador, peru, brasil],
        ecuador: [colombia, peru],
        peru: [ecuador, colombia, brasil, bolivia, chile],
        venezuela: [colombia, brasil, guyana],
        guyana: [venezuela, brasil, suriname],
        suriname: [guyana, brasil, frenchGuyana],
        frenchGuyana: [suriname, brasil],
        brasil: [uruguay, argentina, paraguay, bolivia, peru, colombia, venezuela, guyana, suriname, frenchGuyana],
        bolivia: [peru, brasil, paraguay, argentina, chile],
        paraguay: [bolivia, brasil, argentina],
        chile: [peru, bolivia, argentina],
        argentina: [chile, bolivia, paraguay, brasil, uruguay],
        uruguay: [argentina, brasil]
    }

    def constraint_function(first_variable, first_value, second_variable, second_value):
        return first_value != second_value

    constraints = {
        costaRica: constraint_function,
        panama: constraint_function,
        colombia: constraint_function,
        ecuador: constraint_function,
        peru: constraint_function,
        venezuela: constraint_function,
        guyana: constraint_function,
        suriname: constraint_function,
        frenchGuyana: constraint_function,
        brasil: constraint_function,
        bolivia: constraint_function,
        paraguay: constraint_function,
        chile: constraint_function,
        argentina: constraint_function,
        uruguay: constraint_function,


    }

    return CSP(variables, domains, neighbours, constraints)


if __name__ == '__main__':
    australia = create_australia_csp()
    result = australia.backtracking_search()
    for area, color in sorted(result.items()):
        print("{}: {}".format(area, color))

    # Check at https://mapchart.net/australia.html
