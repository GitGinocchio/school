from tempfile import TemporaryFile as tempfile
from jsonfile import JsonFile
from colorama import Fore
from time import sleep
from os import system
from os import chdir
from os import path
import subprocess
import qrcode

CONFIG = JsonFile("config.json")

def mode():
    system("cls")
    modes = [
        "1) Visualizza i link salvati",
        "2) Aggiungi un link alla lista",
        "3) Rimuovi un link dalla lista",
        "4) Uscita dal programma"
    ]
    for mode in modes: print(mode)
    while True:
        choice = input("Qual'è l'azione che vuoi svolgere?\n")

        if choice.isnumeric() and int(choice) <=len(modes) and int(choice) >= 1:
            return int(choice)
        else:
            system('cls')
            print(f"{Fore.LIGHTRED_EX}La modalità scelta non esiste!{Fore.RESET}")
            sleep(2)
            choose()

def choose():
    system('cls')
    for n,link in enumerate(CONFIG['links']): 
        print(f"{Fore.LIGHTGREEN_EX}{n+1}{Fore.RESET}) Nome: {Fore.LIGHTYELLOW_EX}{link['name']}{Fore.RESET}") #\tLink: {link['url']}

    while True:
        choice = input(f"Scegli un link tramite il suo {Fore.LIGHTGREEN_EX}id{Fore.RESET}: ")

        if choice.isnumeric():
            if int(choice)-1 in range(0,len(CONFIG['links'])):
                return CONFIG['links'][int(choice)-1]
            else:
                system('cls')
                print(f"{Fore.LIGHTRED_EX}L'id inserito non è presente nella lista!{Fore.RESET}")
                sleep(2)
                choose()
        else:
            system('cls')
            print(f"{Fore.LIGHTRED_EX}L'input inserito non è valido!{Fore.RESET}")
            sleep(2)
            choose()

def show_qrcode(link : str):
    with tempfile('+wb',delete=True,prefix='qrcode',suffix='.png') as qrfile:
        qr = qrcode.make(link,version=1,error_correction=qrcode.ERROR_CORRECT_L,box_size=7,border=1)
        qr.save(qrfile)
        
        subprocess.run(f'start explorer "{qrfile.name}"',shell=True)
        system('cls')
        print("Se hai finito di visualizzare il qrcode puoi procedere, ricorda che il qrcode verrà eliminato")
        print(f"Il qrcode al momento si trova in: {qrfile.name}")
        input('\nPremi un tasto per continuare...')

def add():
    system("cls")
    while True:
        url = input(f"Inserisci l'url da salvare: ")

        if url.startswith(('http','https')):
            break
        else:
            system('cls')
            print(f"{Fore.LIGHTRED_EX}L'url deve iniziare con http o https!{Fore.RESET}")
            sleep(2)
            add()

    name = input(f"Inserisci il nome con cui vuoi salvare il seguente indirizzo: ")
    CONFIG['links'].append({"name" : name, "url" : url})
    print("Indirizzo salvato con successo!")
    sleep(2)

def remove():
    system("cls")
    for n,link in enumerate(CONFIG['links']): print(f"{n+1}) name: {link['name']}")

    while True:
        choice = input(f"Scegli un nome tramite il suo {Fore.LIGHTGREEN_EX}id{Fore.RESET}: ")

        if choice.isnumeric():
            if int(choice)-1 in range(0,len(CONFIG['links'])):
                del CONFIG['links'][int(choice)-1]
                print("Link eliminato con successo!")
                sleep(2)
                return
            else:
                system('cls')
                print(f"{Fore.LIGHTRED_EX}L'id inserito non è presente nella lista!{Fore.RESET}")
                sleep(2)
                remove()
        else:
            system('cls')
            print(f"{Fore.LIGHTRED_EX}L'input inserito non è valido!{Fore.RESET}")
            sleep(2)
            remove()

if __name__ == '__main__':
    chdir(path.dirname(__file__))

    while True:
        m = mode()
        if m == 1:
            link = choose()
            show_qrcode(link['url'])
        elif m == 2:
            add()
        elif m == 3:
            remove()
        elif m == 4:
            print("Uscita dal programma...")
            exit()