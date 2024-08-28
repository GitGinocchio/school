; multi-segment executable file template.

data segment
    ; add your data here!
    messaggio db "Inserisci un numero: $"
    newline db 10, 13, "$"
    num dw ?
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
    
    ; Scrivo il messaggio
    lea dx, messaggio
    mov ah, 09h
    int 21h
    
    ; Chiedo il numero in input
    mov ah, 01h
    int 21h
    sub ax, 0030h
    mov num, ax
    
    mov ax, num   ; Numero
    mov bx, num   ; Numero che viene decrementato di uno ogni volta
fattoriale:
    dec bx
    mul bx                            
    
    cmp bx, 0000d
    jg fattoriale
    
    
    

    
    
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
