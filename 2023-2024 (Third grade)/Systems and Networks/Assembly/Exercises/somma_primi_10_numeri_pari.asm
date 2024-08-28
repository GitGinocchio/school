; multi-segment executable file template.

data segment            
    ; add your data here!
    num db 0
    sum db 0
    counter db 1
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
    
    mov cl, counter
    mov al, num
    mov bl, sum

ciclo:
    
    add al, 2
    add bl, al
    
    inc cl
    
    cmp cl, 10
    jl ciclo 
    
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
