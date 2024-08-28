; multi-segment executable file template.

data segment
    ; add your data here!
    
    messaggio1 db "Inserisci un numero: $"
    messaggio2 db "Il risultato della somma e': $"
    newline db 10, 13, "$"
    num1 db ? 
    num2 db ?
    result db ? 
     
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
    
    ; Invio il messaggio di inserimento del primo numero
    lea dx, messaggio1
    mov ah, 09
    int 21h
    
    ; Chiedo l'inserimento del primo numero
    mov ah, 01
    int 21h
    mov num1, al
    sub num1, 30h
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
     
    ; Invio il messaggio di inserimento del secondo numero
    lea dx, messaggio1
    mov ah, 09
    int 21h  
     
    ; Chiedo l'inserimento del secondo numero
    mov ah, 01
    int 21h
    mov num2, al
    sub num2, 30h
    
    
    ; Faccio la somma tra i due numeri
    mov bl, num1
    mov bh, num2
    
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
    
    ; Scrivo il messaggio del risultato
    lea dx, messaggio2
    mov ah, 09
    int 21h
    
    
    add bl, bh
    mov result, bl
    cmp result, 10d
    jl minore_di_dieci
    

    ; Scrivo la prima cifra (sempre 1)
    mov dl, 31h
    mov ah, 02
    int 21h
    
    ; Ottengo la seconda cifra dividendo per 10
    mov ah, 0               ; assicuro ah vuoto visto che div fa: al = ax / operand
    mov al, result
    mov bl, 10d
    div bl                  ; questo e' uguale a fare: sub al, bl (Se result < 20)
    add ah, 30h 
    
    ; Scrivo la seconda cifra
    mov dl, ah
    mov ah, 02
    int 21h
    
    jmp fine
     

minore_di_dieci:
    mov dl, result
    add dl, 30h
    mov ah, 02
    int 21h   
    
fine:
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
