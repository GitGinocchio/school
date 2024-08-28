#UPPERCASE 65 -> 90
#LOWERCASE 97 -> 122
UPPERCASE = (65,90)
LOWERCASE = (97,122)

#p = 3
#string = "cruur"

#for i in string:
    #print(ord(i))
    #if ord(i) - p  <= LOWERCASE[0]:
        #print(chr(ord(i) - p + 26))
    #else:
        #print(chr(ord(i) - p))
    #if ord(i) + p >= LOWERCASE[1]:
        #print(chr(ord(i) - 26 + p))
    #else:
        #print(chr(ord(i) + p))


def encrypt(text : str, lower : bool = True, step : int = 3):
    return "".join([chr(ord(i) - 26 + step) if ord(i) + step >= (LOWERCASE[1] if lower else UPPERCASE[1]) - step else chr(ord(i) + step) for i in (text.lower() if lower else text.upper())])

def decrypt(text : str, lower : bool = True, step : int = 3):
    return "".join([chr(ord(i) - step + 26) if ord(i) - step <= (LOWERCASE[0] if lower else UPPERCASE[0]) else chr(ord(i) - step) for i in (text.lower() if lower else text.upper())])


print(decrypt("cruur",False))

print(encrypt("zorro",False))