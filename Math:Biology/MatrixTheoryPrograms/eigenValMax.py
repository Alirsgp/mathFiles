# Homework #1, code to find eigenvalues of 2x2 symmetric matrics with entries between -1 and 1.

import numpy as np
import random 
from joblib import Parallel, delayed
import multiprocessing
from statistics import mean
import time

num_cores = multiprocessing.cpu_count()

avg = 0
iteration = range(100000)

eVals = []

def findEVals():
    randA = random.uniform(-1, 1)
    randB = random.uniform(-1, 1)
    randC = random.uniform(-1, 1)
    m = np.matrix([
        [randA, randB],
        [randB, randA]
    ])
    eigenValues = np.linalg.eigvals(m)
    absEig = max(max(eigenValues), -min(eigenValues))
    # print("Abs Eig is", absEig)
    return absEig

# Serial algo for finding eVals

# start = time.time()
# for index in enumerate(iteration):
#     eVals.append(findEVals())
# end = time.time()
# print("Time taken", end - start)
# print("Average greatest eVal", mean(eVals))

# Parallel algo for finding eVals

start = time.time()
eVals = Parallel(n_jobs = num_cores, verbose = 5)(delayed(
    findEVals)() for index in iteration
)
end = time.time()
print("Time taken", end - start)
print("Average greatest eVal", mean(eVals))

# Results: 2x2 matrices with [[a, b], [b, a]] and [[a, b], [b, c]] have max abs val of eVals converge to 1 and 0.99, respectively.






