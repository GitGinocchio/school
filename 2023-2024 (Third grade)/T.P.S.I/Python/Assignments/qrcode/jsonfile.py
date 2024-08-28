import json
import os

"""
Premetto che questo pezzo di codice l'ho preso da un progetto mio personale nel quale utilizzo dei file .json per il salvataggio di alcuni dati.
(per questo motivo potrebbero esserci funzionalita' aggiuntive come la possibilita' di caricare file .jsonc [commentati...])

L'idea e' venuta fuori quando ho notato che molto spesso dovevo chiamare un metodo .save() per salvare le modifiche dei dati ad un file.
O che il caricamento di un file json in python richiedeva molte righe anche se il modulo json e' ben strutturato.

Mi sono sempre chiesto se ci fosse un metodo per il salvataggio automatico dei dati una volta che vengono modificati eliminati o aggiunti.
Al momento questa sembra essere la strategia migliore che ho trovato...
Inizialmente e' stato difficile creare questo codice, perche' online cose simili non sono facili da trovare...
"""

# creo una classe JsonDict che eredita dalla classe dict.
# questo perche' ho bisogno che quando un elemento del dizionario o il dizionario stesso vengono/viene eliminati/o 
#deve venire chiamata anche un metodo per salvare i dati all'interno del file specificato
#per questo ho aggiunto la riga: if self.file.autosave: self.file.save() 
#(senza questa riga, la classe JsonDict e' praticamente identica a quella da cui eredita)
class _JsonDict(dict):
    def __init__(self, data : dict, file : 'JsonFile'):
        super().__init__(data)
        self.file = file

    def __setitem__(self, key, value):
        super().__setitem__(key, value)
        if self.file.autosave: self.file.save()

    def __delitem__(self, key):
        super().__delitem__(key)
        if self.file.autosave: self.file.save()

# Stessa cosa e' valida per la classe JsonList che eredita dalla classe list. 
# in questo caso ho aggiunto la modifica di altri due metodi comunemente usati .append() e.remove()
class _JsonList(list):
    def __init__(self, data : list, file : 'JsonFile'):
        super().__init__(data)
        self.file = file

    def __setitem__(self, index, value):
        super().__setitem__(index, value)
        if self.file.autosave: self.file.save()

    def __delitem__(self, index):
        super().__delitem__(index)
        if self.file.autosave: self.file.save()
    
    def append(self, value):
        super().append(value)
        if self.file.autosave: self.file.save()
    
    def remove(self, value):
        super().remove(value)
        if self.file.autosave: self.file.save()
        
# Questa e' una classe che eredita da json.JSONDecoder, questa classe viene utilizzata dalla libreria json per il caricamento dei dati.
# in particolare questa classe modifica il modo in cui i dati vengono caricati in python.
# la prima cosa che questo oggetto compie quando viene chiamato automaticamente il metodo decode() e' rimuovere tutti i commenti presenti all'interno delle righe del file json.
# (probabilmente e' presente un metodo migliore per fare questo, l'idea iniziale era quella di utilizzare il modulo 're')
# successivamente la classe "trasforma" i dizionari e le liste comuni in python in "autosalvanti"... in parole povere...
class CustomDecoder(json.JSONDecoder):
    def __init__(self, file : 'JsonFile'):
        super().__init__()
        self.file = file

    def _remove_comments(self, s):
        longcomment = False
        lines = []
        for line in s.split('\n'):
            line = str(line.strip())
            doubleslash = line.find('//')
            slashastrsk = line.find('/*')
            astrskslash = line.find('*/')

            # Commenti singoli // 
            if line.count('\"',0,doubleslash) % 2 == 0 and doubleslash >= 0:
                line = line[:doubleslash]

            #Fine commento lungo */
            if longcomment:
                if astrskslash >= 0:
                    longcomment = False
                    line = line[astrskslash+2:]
                else: continue

            #Inizio commento lungo /*
            if line.count('\"',0,slashastrsk) % 2 == 0 and slashastrsk >= 0:
                if astrskslash < 0: longcomment = True
                line = line[:slashastrsk]

            if line != '': lines.append(line)
        return '\n'.join(lines)
            
    def decode(self, s):
        s = self._remove_comments(s)
        obj = dict(super().decode(s))

        for key,value in obj.items():
            if isinstance(value, dict):
                obj[key] = _JsonDict(value,self.file)
            elif isinstance(value, list):
                obj[key] = _JsonList(value,self.file)
            else:
                obj[key] = value

        return obj

# Questa e' la classe principale che viene utilizzata all'interno del file main.py per il caricamento dei dati.
# presenta alcuni metodi per l'inserimento e la rimozione dei dati all'interno delle varie chiavi presenti nel file json.
# inoltre presenta anche un metodo che permette di ottenere un iteratore se necessario.
# gli ultimi due metodi rimanenti sono copy() che crea un oggeto JsonFile identico a quello creato in precedenza. (non torna utile molto spesso)
# il metodo save() invece e' quello che viene chiamato automaticamente per salvare i dati all'interno del file specificato.
class JsonFile(dict):
    def __init__(self, fp : str = None,*,indent : int = 4,encoding : str = 'utf-8', autosave : bool = True):
        """
        Subclass of dict for loading or creating JSON files.

        !! Warning !! 
        Not all dict methods supports :autosave: attribute.
        """
        assert fp.endswith('.json') or fp.endswith('.jsonc'),'fp must be a json file and end with ".json" or ".jsonc" (JSON with comments)'
        if fp is not None:
            #assert os.path.exists(fp), "File does not exist."
            if os.path.exists(fp):
                with open(fp,encoding=encoding) as jsf: super().__init__(json.load(jsf,cls=CustomDecoder,file=self))
            else:
                super().__init__()
        else:
            super().__init__()
        self.encoding = encoding
        self.autosave = autosave
        self.indent = indent
        self.fp = fp

    def __setitem__(self, key, value):
        if isinstance(value,dict):
            data = _JsonDict(value, self)
        elif isinstance(value,list):
            data = _JsonList(value, self)
        else: 
            data = value

        self.update({key : data})
        
        if self.autosave: self.save()

    def __delitem__(self, key):
        if key in dict(self.items()):
            self.pop(key)
            if self.autosave: self.save()
        else:
            raise KeyError(f"Key '{key}' not found in '{self.fp}'.")
    
    def __iter__(self):
        return iter(self.content)

    def copy(self):
        return JsonFile(self.fp,indent=self.indent,encoding=self.encoding,autosave=self.autosave)

    def save(self, fp : str = None):
        with open(self.fp if fp is None else fp,'w',encoding=self.encoding) as jsf: json.dump(self,jsf,indent=self.indent)
