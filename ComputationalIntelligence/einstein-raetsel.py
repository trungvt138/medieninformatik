from itertools import permutations
import time

# Values and ranges:
colors = ['red', 'blue', 'green', 'ivory', 'yellow']
nations = ['english', 'ukrainian', 'japanese', 'spaniard', 'norwegian']
pets = ['snake', 'fox', 'horse', 'dog', 'zebra']
drinks = ['coffee', 'tea', 'milk', 'juice', 'water']
cigars   = ['OldGold', 'Kools', 'Chesterfields', 'LuckyStrike', 'Parliaments']

def solve():
    for colors_perm in permutations(colors):
        # Clue 15
        if colors_perm[1] != 'blue':
            continue
        # Clue 6
        if colors_perm.index('green') != colors_perm.index('ivory') + 1:
            continue 

        for nations_perm in permutations(nations):
            # Clue 10
            if nations_perm[0] != 'norwegian': 
                continue
            # Clue 2
            if colors_perm[nations_perm.index('english')] != 'red':
                continue

            for pets_perm in permutations(pets):
                # Clue 3
                if pets_perm[nations_perm.index('spaniard')] != 'dog':
                    continue

                for drinks_perm in permutations(drinks):
                    # Clue 5
                    if drinks_perm[nations_perm.index('ukrainian')] != 'tea':
                        continue
                    # Clue 4
                    if colors_perm[drinks_perm.index('coffee')] != 'green':
                        continue
                    # Clue 9
                    if drinks_perm[2] != 'milk':
                        continue

                    for cigars_perm in permutations(cigars):
                        # Clue 7
                        if pets_perm[cigars_perm.index('OldGold')] != 'snake':
                            continue
                        # Clue 8
                        if colors_perm[cigars_perm.index('Kools')] != 'yellow':
                            continue
                        # Clue 11
                        if abs(cigars_perm.index('Chesterfields') - pets_perm.index('fox')) != 1:
                            continue
                        # Clue 12
                        if abs(cigars_perm.index('Kools') - pets_perm.index('horse')) != 1:
                            continue
                        # Clue 13
                        if drinks_perm[cigars_perm.index('LuckyStrike')] != 'juice':
                            continue
                        # Clue 14
                        if cigars_perm[nations_perm.index('japanese')] != 'Parliaments':
                            continue

                        print("Solution found!")
                        for i in range(5):
                            print(f"House {i+1}: {colors_perm[i]}, {nations_perm[i]}, {pets_perm[i]}, {drinks_perm[i]}, {cigars_perm[i]}")

start = time.time()
solve()
print(f"\nRuntime: {time.time() - start:.4f}s")




                