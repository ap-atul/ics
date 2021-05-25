from random import getrandbits, randint
from math import sqrt, floor

PRIME_SIZE = 48

def isprime(p):
    u = int(floor(sqrt(p)))
    for i in range(2, u+1):
        if p % i == 0:
            return False
    return True

def findprime():
    num = getrandbits(PRIME_SIZE)
    c = isprime(num)
    while c is False:
        num = getrandbits(PRIME_SIZE)
        c = isprime(num)
    return num

def gcd(a, b):
    r = a % b
    while r:
        a, b = b, r					#you can use built-in gcd function math.gcd(a,b)
        r = a % b
    return b

def lcm(a, b):
    g = gcd(a, b)
    return a // g * b

def xgcd(a, b):
    """return (g, x, y) such that a*x + b*y = g = gcd(a, b)"""
    x0, x1, y0, y1 = 0, 1, 1, 0
    while a != 0:
        q, b, a = b // a, a, b % a
        y0, y1 = y1, y0 - q * y1
        x0, x1 = x1, x0 - q * x1
    return b, x0, y0

def mulinv(a, b):
    """return x such that (x * a) % b == 1"""
    g, x, _ = xgcd(a, b)
    if g == 1:
        return x % b
    raise ValueError("No inverse found. Impossible Condition.")

def gen_publickey(y):
    e = randint(2, y)
    while gcd(e, y) != 1:
        e = randint(2, y)
    return e

def gen_privatekey(e, y):
    return mulinv(e, y)

def keygen():
    p = findprime()
    q = findprime()
    y = lcm(p-1, q-1)					
    e = gen_publickey(y)
    d = gen_privatekey(e, y)
    return e, d, p*q
