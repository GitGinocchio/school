import math
from colorama import Fore


a = int(input("inserisci il primo coefficente: "))
b = int(input("inserisci il secondo coefficente: "))
c = int(input("inserisci il terzo coefficente: "))
print(f"\nEquazione: {a}x*x + {b}x + {c} = 0\n")

if a==0:
    print(f"{Fore.GREEN}info{Fore.RESET}: Equazione di primo grado.\n")
    # equazione di I grado
    pass 
else:
    print(f"{Fore.GREEN}info{Fore.RESET}: Equazione di secondo grado.\n")
    #equazione di II grado
    delta = math.pow(b,2)-4*a*c

    if delta < 0:
        print(f"{Fore.GREEN}info{Fore.RESET}: delta minore di 0.\n")
        risultato = None
    elif delta == 0:
        print(f"{Fore.GREEN}info{Fore.RESET}: delta uguale a 0.\n")
        risultato = (-b/(2*a),-b/(2*a)) # x1, x2
    else:
        print(f"{Fore.GREEN}info{Fore.RESET}: delta maggiore di 0.\n")
        risultato = ((- b + math.sqrt(delta)) / 2 * a,(- b - math.sqrt(delta)) / 2 * a) #x1,x2



if risultato != None:
    print(f"Risultato equazione: {Fore.GREEN}x1: {risultato[0]}, x2: {risultato[1]}{Fore.RESET}")
else:
    print(f"{Fore.RED}L'equazione non ammette soluzioni reali.{Fore.RESET}")