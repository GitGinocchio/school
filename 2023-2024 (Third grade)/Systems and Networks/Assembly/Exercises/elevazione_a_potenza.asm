; multi-segment executable file template.

data segment            
    ; add your data here!
    num db 2
    exp db 2
    counter db 0
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
          
multiply:

    mov al, num
    mul num
    add cl, 1
    
    cmp cl, exp
    jl multiply
    
    mov dl, al
    add dl, 30h
    mov ah, 02
    int 21h 
    
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
