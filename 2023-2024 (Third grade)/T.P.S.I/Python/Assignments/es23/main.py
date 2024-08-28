from package import media,venditetotali



n = int(input("Inserisci il numero di prodotti che hai acquistato: "))

lista_prezzi = []
lista_quantita = []

for i in range(n):
    nome = input(f"\nInserisci il nome del {i+1} prodotto: ")
    quantita = float(input(f'Inserisci la quantità (kg) di {nome} che hai acquistato: '))
    prezzo = float(input(f'Inserisci il prezzo per un chilogrammo di {nome}: '))
    
    lista_prezzi.append(prezzo)
    lista_quantita.append(quantita)

print(f"\nIl prezzo medio è: {media(lista_prezzi)}")
print(f"La quantità media è: {media(lista_quantita)}")
print(f'Il valore totale delle vendite è: {venditetotali(lista_prezzi,lista_quantita)}')