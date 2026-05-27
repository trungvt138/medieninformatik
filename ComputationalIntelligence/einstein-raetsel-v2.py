from constraint import Problem, AllDifferentConstraint
import time

problem = Problem()

colors  = ['red', 'blue', 'green', 'ivory', 'yellow']
nations = ['english', 'ukrainian', 'japanese', 'spaniard', 'norwegian']
pets    = ['snake', 'fox', 'horse', 'dog', 'zebra']
drinks  = ['coffee', 'tea', 'milk', 'juice', 'water']
cigars  = ['OldGold', 'Kools', 'Chesterfields', 'LuckyStrike', 'Parliaments']

# Each value gets a variable = which house (1–5) it belongs to
for group in [colors, nations, pets, drinks, cigars]:
    problem.addVariables(group, [1, 2, 3, 4, 5])
    problem.addConstraint(AllDifferentConstraint(), group)

# Clue 2: english lives in the red house
problem.addConstraint(lambda a, b: a == b, ['english', 'red'])
# Clue 3: spaniard owns the dog
problem.addConstraint(lambda a, b: a == b, ['spaniard', 'dog'])
# Clue 4: coffee is drunk in the green house
problem.addConstraint(lambda a, b: a == b, ['coffee', 'green'])
# Clue 5: ukrainian drinks tea
problem.addConstraint(lambda a, b: a == b, ['ukrainian', 'tea'])
# Clue 6: green house is immediately right of ivory
problem.addConstraint(lambda g, i: g == i + 1, ['green', 'ivory'])
# Clue 7: OldGold smoker owns the snake
problem.addConstraint(lambda a, b: a == b, ['OldGold', 'snake'])
# Clue 8: Kools smoker lives in the yellow house
problem.addConstraint(lambda a, b: a == b, ['Kools', 'yellow'])
# Clue 9: milk is drunk in house 3
problem.addConstraint(lambda a: a == 3, ['milk'])
# Clue 10: norwegian lives in house 1
problem.addConstraint(lambda a: a == 1, ['norwegian'])
# Clue 11: Chesterfields smoker lives next to fox owner
problem.addConstraint(lambda a, b: abs(a - b) == 1, ['Chesterfields', 'fox'])
# Clue 12: Kools smoker lives next to horse owner
problem.addConstraint(lambda a, b: abs(a - b) == 1, ['Kools', 'horse'])
# Clue 13: LuckyStrike smoker drinks juice
problem.addConstraint(lambda a, b: a == b, ['LuckyStrike', 'juice'])
# Clue 14: japanese smokes Parliaments
problem.addConstraint(lambda a, b: a == b, ['japanese', 'Parliaments'])
# Clue 15: blue house is house 2
problem.addConstraint(lambda a: a == 2, ['blue'])

start = time.time()
solution = problem.getSolution()


if solution:
    for i in range(1, 6):
        row = [
            next(v for v in colors  if solution[v] == i),
            next(v for v in nations if solution[v] == i),
            next(v for v in pets    if solution[v] == i),
            next(v for v in drinks  if solution[v] == i),
            next(v for v in cigars  if solution[v] == i),
        ]
        print(f"House {i}: {', '.join(row)}")

elapsed = time.time() - start

print(f"\nRuntime: {elapsed:.4f}s")