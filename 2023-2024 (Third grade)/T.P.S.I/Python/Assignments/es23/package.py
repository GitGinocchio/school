
media = lambda Iterable: sum(Iterable) / len(Iterable)
"""Funzione che ottiene la media di un iterable sommando tutti gli elementi al suo interno e dividendo per il numero di elementi"""

venditetotali = lambda prezzi, quantita: sum(map(lambda x, y: x*y,prezzi,quantita))
"""Moltiplica Il prezzo i-esimo per la quantità i-esima presenti nelle due liste"""