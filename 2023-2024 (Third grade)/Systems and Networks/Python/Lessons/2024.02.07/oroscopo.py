import requests
from os import system
from colorama import Fore

segni = [
    "aquarius",
    "pisces",
    "aries",
    "taurus",
    "gemini",
    "cancer",
    "leo",
    "virgo",
    "libra",
    "scorpio",
    "sagittarius",
    "capricorn"
]

for n,segno in enumerate(segni): 
    print(f"{n} - {Fore.MAGENTA}{segno}{Fore.RESET}")

segno = ""
while segno not in segni:
    segno = input("Scegli un segno zodiacale: ")
    if segno.isnumeric(): segno = segni[int(segno)]
system("cls")

response = requests.get(f"https://ohmanda.com/api/horoscope/{segno}").json()

print(f"L'oroscopo di oggi {Fore.CYAN}{response['date']}{Fore.RESET} per {Fore.GREEN}{response['sign']}{Fore.RESET} è: \n")
print(f"{Fore.MAGENTA}{response['horoscope']}{Fore.RESET}")
