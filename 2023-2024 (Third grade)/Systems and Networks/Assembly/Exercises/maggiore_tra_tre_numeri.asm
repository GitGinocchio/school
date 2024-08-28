; multi-segment executable file template.

data segment
    ; add your data here!
    newline db 10, 13, "$"
    message dw "Il numero maggiore e': $"
    num1 db ?
    num2 db ?
    num3 db ?
    maggiore db ?
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax
    ; add your code here
    
    ; Chiedo il primo numero all'utente e lo trasformo da ascii a decimale
    mov ah, 01
    int 21h
    sub al, 30h
    mov num1, al
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
    ; Chiedo il secondo numero all'utente e lo trasformo da ascii a decimale
    mov ah, 01
    int 21h
    sub al, 30h
    mov num2, al
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
    ; Chiedo il terzo numero all'utente e lo trasformo da ascii a decimale
    mov ah, 01
    int 21h
    sub al, 30h
    mov num3, al
    
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
    ; comparo i due numeri
    ; se il primo e' maggiore lo lascio in ah
    ; se il secondo e' maggiore lo sposto in ah 
    
    ; metto il primo numero in ah
    mov ah, num1
    ; metto il primo numero in ah
    mov al, num2
    
    
compare:
    
    ; comparo i due numeri
    cmp ah, al
    jl secondo_maggiore
    
    ; comparo il numero maggiore con il terzo numero
    mov al, num3
    cmp ah, al
    jl secondo_maggiore
    
    jmp print   

secondo_maggiore:
    ; se il numero in ah e' minore: ah = al 
    mov ah, al
    
    ; ritorno nella sezione dei controlli
    jmp compare 


print:
    ;trasformo il numero da decimale a carattere ascii
    mov maggiore, ah
    add maggiore, 30h
    
    ; srivo il messaggio del maggiore 
    lea dx, message
    mov ah, 09
    int 21h
    
    ; scrivo il numero maggiore
    mov dl, maggiore
    mov ah, 02
    int 21h 
    
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
