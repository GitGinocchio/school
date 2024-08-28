; multi-segment executable file template.

data segment
    ; add your data here!
    newline db 10, 13, "$"
    messaggio1 db "Inserisci un numero: $"
    messaggio2 db "I numeri inseriti sommati tra di loro e divisi per 2 hanno un resto!$"
    
   
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
    ; Sommare due numeri, dividerli per 2, se la divisione ha il resto diverso da 0 si ferma, altrimenti richiede i numeri in input
     
inserimento:
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h

    ; Scrivo a schermo il messaggio di inserimento
    lea dx, messaggio1
    mov ah, 09
    int 21h
    
    ; Chiedo all'utente il primo numero e lo sposto in bl
    mov ah, 01
    int 21h
    sub al, 30h
    mov bl, al
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
    ; Scrivo a schermo il messaggio di inserimento
    lea dx, messaggio1
    mov ah, 09
    int 21h
           
    ; Chiedo all'utente il secondo numero e lo aggiungo al precedente memorizzato in bl
    mov ah, 01
    int 21h
    sub al, 30h
    add al, bl
 
    mov bh, 02d
    mov ah, 0
    div bh
    
    cmp ah, 0d
    je inserimento
     
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
    ; Scrivo a schermo il secondo messaggio
    lea dx, messaggio2
    mov ah, 09
    int 21h
    

    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
