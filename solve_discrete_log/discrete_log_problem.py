import gmpy2
from gmpy2 import mpz

'''
Goal is to write a program to compute discrete log modulo a prime p.
g,h - some elements in Zp, h = g^x. 
Goal of this programm is to find x.

hint: h/(g^x1) == (g^B)^x0
'''

def setHashTable(h, g, p, B):
    '''
    Func is used for fill a hash table, where
    key is x1 and value is h/(g^x1)

    return: hash table
    '''
    table = {}
    for x in range(B):
        table[(gmpy2.powmod(g, -x, p)*h) % p] = x
    return table


def checkCorresponding(table, g, B, p):
    '''
    B - number from meet in the middle attack
    Func verifies if computing element is in hash table.

    return: needed elements x0,x1
    '''
    base = gmpy2.powmod(g, B, p)
    for x0 in range(B):
        value = gmpy2.powmod(base, x0, p)
        if(table.has_key(value)):
            x1 = table.get(value)
            return (x0, x1)

    
def main():
    p = mpz('your number')
    g = mpz('your number')
    h = mpz('your number')
    B = mpz('your number')
    hashTable = setHashTable(h, g, p, B)
    (x0, x1) = checkCorresponding(hashTable, g, B, p)
    result = (x0*B + x1) % p
    print "The discrete log modulo a prime p is ", result
    
main()
