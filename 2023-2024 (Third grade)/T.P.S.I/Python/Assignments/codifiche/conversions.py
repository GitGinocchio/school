


def bin(n : int):
    assert n >= 0
    bin = ""
    while n > 0:
        bin = str(n % 2) + bin
        n = int(n / 2)

    p = 2
    i=1
    while len(bin) > p:
        p*=i
        i+=1

    return "0" * (p-len(bin))  + bin

def dec(bin : str):
    return sum(int(bin[abs(len(bin) - 1 - i)]) * 2 ** i for i in range(len(bin)-1,-1,-1))

def excess2(n : int):
    """
    Codice non pesato con complemento a 9 

    (p)   8 4 2 1     (p)   8 4 2 1

    (0)   0 0 1 1     (9)   1 1 0 0\n
    (1)   0 1 0 0     (8)   1 0 1 1\n
    (2)   0 1 0 1     (7)   1 0 1 0\n
    (3)   0 1 1 0     (6)   1 0 0 1\n
    (4)   0 1 1 1     (5)   1 0 0 0\n
    """ 
    assert n >= 0 and dec+3 <= 9, 'n out of range'
    return bin(n+3)

