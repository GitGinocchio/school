from enum import Enum
from typing import Any
import requests

#https://comuni-ita.readme.io/reference/comuni-regione

VOWELS = "AEIOU"

class Months(Enum):
    gennaio = 'A'
    febbraio = 'B'
    marzo = 'C'
    aprile = 'D'
    maggio = 'E'
    giugno = 'H'
    luglio = 'L'
    agosto = 'M'
    settembre = 'P'
    ottobre = 'R'
    novembre = 'S'
    dicembre = 'T'

OddAC = {
    '0': 0,
    '1': 1,
    '2': 2,
    '3': 3,
    '4': 4,
    '5': 5,
    '6': 6,
    '7': 7,
    '8': 8,
    '9': 9,
    'A': 0,
    'B': 1,
    'C': 2,
    'D': 3,
    'E': 4,
    'F': 5,
    'G': 6,
    'H': 7,
    'I': 8,
    'J': 9,
    'K': 10,
    'L': 11,
    'M': 12,
    'N': 13,
    'O': 14,
    'P': 15,
    'Q': 16,
    'R': 17,
    'S': 18,
    'T': 19,
    'U': 20,
    'V': 21,
    'W': 22,
    'X': 23,
    'Y': 24,
    'Z': 25
}

EvenAC = {
    '0': 1,
    '1': 0,
    '2': 5,
    '3': 7,
    '4': 9,
    '5': 13,
    '6': 15,
    '7': 17,
    '8': 19,
    '9': 21,
    'A': 1,
    'B': 0,
    'C': 5,
    'D': 7,
    'E': 9,
    'F': 13,
    'G': 15,
    'H': 17,
    'I': 19,
    'J': 21,
    'K': 2,
    'L': 4,
    'M': 18,
    'N': 20,
    'O': 11,
    'P': 3,
    'Q': 6,
    'R': 8,
    'S': 12,
    'T': 14,
    'U': 16,
    'V': 10,
    'W': 22,
    'X': 25,
    'Y': 24,
    'Z': 23
}

cni = {
    0  : 'A',
    1  : 'B',
    2  : 'C',
    3  : 'D',
    4  : 'E',
    5  : 'F',
    6  : 'G',
    7  : 'H',
    8  : 'I',
    9  : 'J',
    10 : 'K',
    11 : 'G',
    12 : 'M',
    13 : 'N',
    14 : 'O',
    15 : 'P',
    16 : 'Q',
    17 : 'R',
    18 : 'S',
    19 : 'T',
    20 : 'U',
    21 : 'V',
    22 : 'W',
    23 : 'X',
    24 : 'Y',
    25 : 'Z'
}

#nome = input("Inserisci il tuo nome: ")
#cognome = input("Inserisci il tuo cognome: ")
#anno = input("Inserisci la tua data di nascita separata da punti: ")
#fiscale = ""

class CodiceFiscale:
    def __init__(self, names : tuple | str, surnames : tuple | str, year : int, month : str, day : int, sex : bool, comune : str, cap : str | int = None):
        assert len(str(year)) == 4, 'L\'anno deve essere espresso con quattro cifre: es. 1988'
        assert any(m.name == month.lower() for m in Months), 'Il mese inserito non esiste o non e\' in un formato valido es. gennaio'
        assert len(str(day)) <= 2, 'Il giorno deve essere espresso con una o due cifre: es. 02, 2, 12'
        self.names = names if type(names) is str else ''.join(names)
        self.surnames = surnames if type(surnames) is str else ''.join(surnames)
        self.year = year
        self.month = month
        self.day = day
        self.sex = sex
        self.comune = comune
        self.cap = cap
        self.codice = f'{self.part1()}{self.part2()}{self.part3()}{self.part4()}{self.part5()}{self.part6()}{self.part7()}'

    def __str__(self):
        return self.codice
    
    def __eq__(self, CodiceFiscale: 'CodiceFiscale') -> bool:
        if self.codice == CodiceFiscale.codice: return True
        else: return False

    def part1(self):
        part = ''
        for char in (uppersurnames:=''.join(self.surnames).upper()):
            if char not in VOWELS: 
                part+=char
            if len(part) == 3: break

        for char in uppersurnames:
            if char in VOWELS: part+=char
            if len(part) == 3: break

        while len(part) < 3: part+='X'

        return part

    def part2(self):
        cons = [char for char in ''.join(self.names).upper() if char not in VOWELS]
        vowels = [char for char in ''.join(self.names).upper() if char in VOWELS]
        part = ""

        if (lenght:=len(cons)) > 3:
            part = f'{cons[0]}{cons[2]}{cons[3]}'
        else:
            part = ''.join(cons)

        if lenght == 2:
            part += vowels[0]
        elif lenght == 1:
            part +=''.join(vowels[:2])

        while len(part) < 3: part+='X'
        
        return part

    def part3(self):
        return str(self.year)[-2::]

    def part4(self):
        for m in Months:
            if m.name == self.month.lower():
                return m.value

    def part5(self):
        if self.sex:
            return str(self.day+40)
        else:
            return f'{self.day}' if len(str(self.day)) == 2 else f'0{self.day}'

    def part6(self):
        url = f"https://axqvoqvbfjpaamphztgd.functions.supabase.co/comuni?nome={self.comune}{f'&cap={self.cap}' if self.cap else ''}"

        response = requests.get(url).json()
        return str(response[0]['codiceCatastale'])

    def part7(self):
        s = 0
        for p,char in enumerate(f'{self.part1()}{self.part2()}{self.part3()}{self.part4()}{self.part5()}{self.part6()}'):
            values = [value for key,value in (EvenAC if p % 2 == 0 else OddAC).items() if key == char]
            s+=values[0]
        return cni[s % 26]

ismaele = CodiceFiscale('Ismaele','Mattiolo',2007,'marzo',21,False,'Noventa Vicentina')
giulio = CodiceFiscale('Giulio','Tognetto',2007,'agosto',2,False,'Noventa Vicentina')

print(ismaele == giulio)