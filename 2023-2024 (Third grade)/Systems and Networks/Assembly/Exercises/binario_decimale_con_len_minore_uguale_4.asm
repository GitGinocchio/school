; multi-segment executable file template.

data segment
    messaggio1 db "Inserisci il numero dei bit nella stringa in binario: $"
    messaggio2 db "Inserisci il numero in binario: $"
    newline db 10, 13, "$"
    lenbytes db ?
    result dw ?
    bit db ?
    
ends                                                  

stack segment
    dw   128  dup(0)
ends

code segment
start:
; set segment registers:
    mov ax, data
    mov ds, ax
    mov es, ax

    ; add your code here
    ; 1. Chiedere da quanti bit e' formata la stringa in binario (PER ORA E' SUPPORTATA SOLAMENTE UNA LUNGHEZZA <=4)
    ; 2. Loop che chiede un carattere che puo' essere 0 o 1
    ; 3. Per ogni carattere:
         ; Se il carattere e' 1 moltiplicare 2 per la posizione e sommarlo al risultato 
    
    ; Scrivo il messaggio e chiedo lenbytes   
    lea dx, messaggio1
    mov ah, 09
    int 21h

    mov ah, 01
    int 21h
    sub al, 30h       ; Trasformo lenbytes da carattere a cifra
    mov lenbytes, al  ; Salvo il valore nella variabile lenbytes
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h 
   
    ; Scrivo il messaggio2
    lea dx, messaggio2
    mov ah, 09
    int 21h

    mov cl, lenbytes  ; Sposto il valore della variabile nel registro del contatore
    sub cl, 01d       ; contatore per il numero di inserimenti
             
             
inserimento_bit:
    
    ; Chiedo il bit alla posizione salvata in cl
    mov ah, 01
    int 21h
    sub al, 30h
    mov bit, al
    

    cmp bit, 1d       ; Se il bit e' diverso da uno salto l'elevazione 
    jne salta_eleva
    
    cmp cl, 0d        ; Se il bit e' uno ma e' nella posizione 0 (cioe' LSB) 
    je lsb
    
    mov bl, 2d        ; Utilizzato per fare l'elevazione
    mov ch, 0d        ; Contatore per le elevazioni
     
eleva:
    mul bl            ; ax = al (bit) *  

    inc ch            ; Incremento il contatore delle elevazioni
                      
    cmp ch, cl        ; Se il contatore delle elevazioni e' minore della posizione del bit continuo ad elevare
    jl eleva
    
    add result, ax    ; Alla fine dell'elevazione sommo il risultato a result
    mov bl, 0d 
    mov ax, 0d
    
    jmp salta_eleva   ; Salto la sezione lsb
    
    
lsb: 
    add result, 01d   ; Sommo 1 al risultato perche' 2 elevato alla 0 e' uguale a 1
 
    
salta_eleva:

    dec cl                 ; Decremento cl di 1
    cmp cl, 0d             ; Se ci sono altri inserimenti da fare torno sopra
    jge inserimento_bit
    
    lea dx, newline
    mov ah, 09
    int 21h
        
    cmp result, 10d        ; Comparo il risultato finale con il numero decimale 10
    jl stampa_singola      ; Se il risultato e' minore di 10 stampo un carattere singolo
    
    ; Scrivo la seconda cifra
    mov dl, 31h
    mov ah, 02
    int 21h
    
    mov ah, 0
    mov ax, result
    mov bl, 10d
    div bl 
    add ah, 30h
    
    ; Scrivo la seconda cifra
    mov dl, ah
    mov ah, 02
    int 21h
    
    jmp fine
    
stampa_singola:
    
    ; Scrivo la prima cifra
    mov dx, result
    add dx, 30h
    mov ah, 02
    int 21h

fine:
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
