; multi-segment executable file template.

data segment
    ; add your data here!
    newline db 13, 10, "$" ; ritorno a capo e avanzamento riga
    counter db 0
   
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax
    
    
while:
    
    lea dx, newline
    mov ah, 9
    int 21h
          
    mov dl, counter
    add dl, 30h
    mov ah, 2
    int 21h 
          
    add counter, 1
    
    
    cmp counter, 10 
    jl while
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
