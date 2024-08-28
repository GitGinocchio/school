from time import sleep
from os import system
import random
from colors import Fore

#Costanti
MLPERSECOND = 15                                    #ml al secondo che eroga la pompa
SECONDSTOHEATWATER = 30                             #secondi necessari per far si che l'acqua si scaldi prima di fare il caffe'
MAXMLWATER = 500                                    #ml massimi contenuti dal serbatoio
MAXPODS = 10                                        #numero di cialde massime contenute nella macchina prima che debba essere svuotata

#Variabili
#inizializzo il valore delle variabili come un numero casuale da 0 al massimo prestabilito...
watertank = random.randrange(0,MAXMLWATER)          #500    #valore attuale di acqua nel serbatoio in ml
podcontainer = random.randrange(0,MAXPODS)          #0      #numero attuale di pod all'interno della macchinetta

# lista di bevande (dizionari) che hanno tutti le stesse chiavi "name" e "ml"
drinks = [
    {"name" : "Caffe",              "ml" : 30},
    {"name" : "Cappuccino",         "ml" : 150},
    {"name" : "Caffe lungo",        "ml" : 60},
    {"name" : "Caffe macchiato",    "ml" : 40},
    {"name" : "Latte macchiato",    "ml" : 180},
    {"name" : "Cioccolata calda",   "ml" : 170},
    {"name" : "Espresso",           "ml" : 30},
    {"name" : "Te",                 "ml" : 90}
]


"""
    Funzione utilizzata per creare e visualizzare tutte le barre di caricamento all'interno del programma.
    Parametri:

    - step : int | float
        valore di aumento della barra di caricamento ad ogni iterazione

    - max : int | float = 100
        valore massimo della barra di caricamento, di base impostato a 100.

    - start : int | float = 0
        valore iniziale da dove far partire la barra di caricamento completato, di base impostato a 0.

    - delay : int | float = 1
        tempo di attesa (in secondi) dopo l'aggiornamento della barra di caricamento, di base impostato a 1.

    - lenbar : int = 50
        lunghezza totale della barra di caricamento, di base impostata a 50 caratteri.

    - unit : str = 'ml'
        unita' di misura che viene visualizzata di fianco al valore attuale della barra di caricamento, di base impostato con 'ml' (millilitri).

    Questa funziona non ritorna nessun valore, si limita a visualizzare a schermo una barra di caricamento e delle stringhe.
"""
def loadingbar(step : int | float = 1, max : int | float = 100, start : int | float = 0, delay : int | float = 1, lenbar : int = 50, unit : str = 'ml'):
    current = start
    while current < max:
        current+=step
        percentage = current * 100 / max
        bar = '█' * int(percentage * lenbar / 100)
        blankbar = '░' * (lenbar - len(bar))

        if percentage > 70:  color = Fore.GREEN
        elif percentage > 30: color = Fore.YELLOW
        else: color = Fore.RED
        
        print(f"\r{color}{bar}{Fore.RESET}{blankbar} {current if current < max else max}{unit} {percentage if percentage <= 100 else 100:.1f}%", end="", flush=True)
        sleep(delay)

"""
    Funzione utilizzata per la gestione dell'interfaccia di scelta delle bevande e la gestione degli ingressi dell'utente con le relative eccezzioni...
    Questa funzione non accetta parametri ma ritorna un valore di tipo dict (dizionario) con le chiavi "name" e "ml", le istruzioni per creare correttamente quella bevanda.
    Queste informazioni vengono ottenute grazie alla scelta dell'utente utilizzando l'id o il nome della bevanda che vuole preparare.
"""
def choose_pod():
    pod = None
    while not pod:
        system('cls')
        print(f'{Fore.YELLOW}Scrivi \"exit\" per terminare l\'esecuzione del programma{Fore.RESET}\n')
        
        print(f'Acqua: {Fore.GREEN}{watertank}{Fore.RESET} ml\tCialde vuote: {Fore.GREEN}{podcontainer}{Fore.RESET}\n')
        print('Id\tMl\tNome\t')
        for id,drink in enumerate(drinks): print(f"{Fore.YELLOW}{id}{Fore.RESET}:\t{drink['ml']}\t{Fore.GREEN}{drink['name']}{Fore.RESET}")
        
        userinput = input(f'\nScegli una bevanda utilizzando il suo {Fore.GREEN}nome{Fore.RESET} o il suo {Fore.YELLOW}id{Fore.RESET}: ' + Fore.CYAN).lower().strip()
        print(Fore.RESET)
        try:
            if userinput == 'exit': pod = 'exit'
            elif userinput.isdecimal() or userinput.isnumeric(): # elif len(userinput) == 1 and ord(userinput) in range(48,58)
                assert userinput.isnumeric(),"L'indice della bevanda deve essere un numero intero positivo!"
                assert int(userinput) <= len(drinks),"Non esiste nessuna bevanda per questo indice!"
                pod = drinks[int(userinput)]
            elif userinput.isascii():
                assert any([drink for drink in drinks if drink["name"].lower() == userinput]), "Non esiste nessuna bevanda con questo nome!"
                pod = [drink for drink in drinks if drink["name"].lower() == userinput][0]
            else:
                raise AssertionError("Non sono ammessi caratteri speciali per questo input!")

        except AssertionError as e: 
            system('cls')
            print(Fore.RED,e,Fore.RESET,sep='')
            sleep(2)
    return pod

"""
    Funzione chiamata solamente una volta all'interno del programma che prevede il riscaldamento dell'acqua per poter preparare le bevande.
    Questa funziona non accetta parametri e non ritorna nessun valore, si limita a visualizzare a schermo una barra di caricamento e delle stringhe.
"""
def water_heating():
    system('cls')
    print(Fore.YELLOW,'Riscaldamento dell\'acqua in corso...',Fore.RESET,sep='')
    loadingbar(1,SECONDSTOHEATWATER,unit=' s')
    sleep(2)

"""
    Funzione che si occupa di chiedere all'utente se ha posizionato correttamente il bicchiere, prima di procedere con la preparazione della bevanda.
    Questa funziona non accetta parametri e non ritorna nessun valore, si limita a visualizzare a schermo una barra di caricamento e delle stringhe.
"""
def ensure_cup():
    while True:
        system('cls')
        userinput = input(f'Il bicchiere è stato posizionato correttamente? ({Fore.GREEN}Y{Fore.RESET}/{Fore.RED}N{Fore.RESET}): {Fore.CYAN}').lower().strip()
        print(Fore.RESET)
        
        if userinput != 'n' and userinput != 'y':
            system('cls')
            print(Fore.RED,'Input non valido...',Fore.RESET,sep='')
            sleep(2)
            continue    #Fine di questa iterazione, continua con la prossima... (per evitare il break finale)
        elif userinput == 'n':
            system('cls')
            print(Fore.YELLOW,'Inserire correttamente il bicchiere...',Fore.RESET,sep='')
            sleep(2)
            continue    #Fine di questa iterazione, continua con la prossima... (per evitare il break finale)

        break # <-- Break chiamato esclusivamente quando entrambe le condizioni sono false.

"""
    Funzione utilizzata per controllare che l'acqua sia sufficiente per preparare la bevanda precedentemente scelta dall'utente.
    Se l'acqua non e' abbastanza per preparare la bevanda scelta, la macchina procede con l'aggiungerne di altra altrimenti termina.
    
    Parametri:

    - pod : dict
        dizionario contenente le chiavi e i valori della bevanda che bisogna preparare.

    Questa funzione non ritorna nessun valore.
"""
def ensure_water(pod : dict):
    global watertank    # <-- specifico alla funzione che watertank non e' ad un livello locale ma globale e che quindi non e' stata inizializzata all'interno della funzione
    system('cls')
    print(Fore.YELLOW,'Sto controllando se c\'è abbastanza acqua nella macchinetta...',Fore.RESET,sep='')
    sleep(2)
    if watertank < pod['ml']:
        system('cls')
        print(Fore.RED,'È finita l\'acqua nella macchinetta...',Fore.RESET,sep='')
        sleep(2)
        system('cls')
        print(Fore.YELLOW,'Sto caricando l\'acqua nella macchinetta...',Fore.RESET,sep='')
        loadingbar(MLPERSECOND,MAXMLWATER,watertank) # pod['ml']
        watertank = MAXMLWATER # 0
    else:
        system('cls')
        print(Fore.GREEN,'Nella macchinetta c\'è abbastanza acqua...',Fore.RESET,sep='')
    sleep(2)

"""
    Funzione utilizzata per controllare che il contenitore delle cialde vuote all'interno della macchinetta non sia pieno.
    Se il contenitore e' pieno la macchinetta procede con lo svuotamento.

    Questa funziona non accetta parametri e non ritorna nessun valore, si limita a visualizzare a schermo delle stringhe.
"""
def ensure_podcontainer():
    global podcontainer     # <-- specifico alla funzione che podcontainer non e' ad un livello locale ma globale e che quindi non e' stata inizializzata all'interno della funzione
    system('cls')
    print(Fore.YELLOW,'Sto controllando quante cialde ci sono nella macchinetta...',Fore.RESET,sep='')
    sleep(2)
    if not podcontainer < MAXPODS:
        system('cls')
        print(Fore.RED,'Il contenitore delle cialde è pieno, svuotamento in corso...',Fore.RESET,sep='')
        podcontainer = 0
    else:
        system('cls')
        print(Fore.GREEN,'Il contenitore delle cialde non è completamente pieno...',Fore.RESET,sep='')
    sleep(2)

"""
    Funzione che si occupa del processo di preparazione della bevanda.
    Parametri:

    - pod : dict
        dizionario contenente le chiavi e i valori della bevanda che bisogna preparare.
    
    Questa funziona non accetta parametri e non ritorna nessun valore, si limita a visualizzare a schermo una barra di caricamento e delle stringhe.
"""
def dispense(pod : dict):
    global podcontainer, watertank  # <-- specifico alla funzione che watertank e podcontainer non sono ad un livello locale ma globale e che quindi non sono state inizializzate all'interno della funzione
    system('cls')
    print(Fore.YELLOW,f"Sto preparando: {pod['name']}",Fore.RESET,sep='')
    loadingbar(MLPERSECOND,pod['ml'])
    podcontainer+=1
    watertank-=pod['ml']
    system('cls')
    print(fr"""{Fore.LIGHTMAGENTA_EX}
             ( (
              ) )
            .........
            |       |]     {Fore.GREEN}{pod['name']} preparato con successo!{Fore.LIGHTMAGENTA_EX}
            \       /     
             `-----'
    {Fore.RESET}""")
    sleep(2)



if __name__ == '__main__':              # condizione che controlla se il file viene avviato (e non importato...)
    water_heating()
    while True:
        pod = choose_pod()
        if pod == 'exit': break         # condizione di uscita del ciclo generale
        ensure_cup()
        ensure_water(pod)
        ensure_podcontainer()
        dispense(pod)
