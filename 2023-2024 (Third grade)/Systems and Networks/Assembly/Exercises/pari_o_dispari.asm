; multi-segment executable file template.

data segment
    newline db 13, 10, "$"                                      ; ritorno a capo e avanzamento riga
    insertmessage db "Inserisci un numero: $"
    evn_message db "Il numero e' pari $"
    odd_message db "Il numero e' dispari $"
ends
                                     
stack segment
    dw   128  dup(0)
ends

code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax
    
    
    ; scrivo a schermo il messaggio di inserimento del numero
    lea dx, insertmessage
    mov ah, 9
    int 21h 
    
    ; prendo un carattere da input
    mov ah, 1
    int 21h
    
    ; trasformo il numero in intero
    sub al, 30h
     
    ; salvo in un registro il numero 2 in decimale
    
    ; al = al / bl (resto: ah)
    mov bl, 2d
    div bl
    
    ; comparo il resto della divisione con il numero 0 in decimale
    ; se sono uguali salto a iseven invece se sono diversi salto in isodd
    cmp ah, 0d
    je iseven
    jne isodd
               
               
    ; vado a capo
    lea dx, newline
    mov ah, 9
    int 21h
          
iseven:
    lea dx, newline
    mov ah, 9
    int 21h

    lea dx, evn_message
    mov ah, 9
    int 21h
    jmp stop
    
isodd:
    lea dx, newline
    mov ah, 9
    int 21h

    lea dx, odd_message
    mov ah, 9
    int 21h
    jmp stop
 

stop:
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
