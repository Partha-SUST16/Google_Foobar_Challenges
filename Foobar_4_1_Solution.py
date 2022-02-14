from itertools import combinations

def solution(num_buns, num_required):
    copies_per_key = num_buns - num_required + 1
    key_rings = [[] for num in range(num_buns)]
    for key, bunnies in enumerate(combinations(range(num_buns),copies_per_key)):
        for bunny in bunnies:
            key_rings[bunny].append(key)
    return key_rings
    