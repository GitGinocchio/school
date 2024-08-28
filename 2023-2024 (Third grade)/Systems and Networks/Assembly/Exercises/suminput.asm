data segment
    newline db 13, 10, "$" ; ritorno a capo e avanzamento riga
    inser1_message db "inserisci il primo numero: $"
    inser2_message db "inserisci il secondo numero: $"
    result_message db "il risultato e': $"
    
    n1 db ?
    n2 db ?
    result db ?
    
ends

stack segment
    dw   128  dup(0)
ends

code segment
start:
    mov ax, data
    mov ds, ax
    mov es, ax
    
    ;scrivo a schermo il messaggio per il primo numero
    lea dx, inser1_message
    mov ah, 9
    int 21h 
    
    ;prendo un carattere da input
    mov ah, 01
    int 21h
    
    ;tolgo 30h per passare dal carattere '1' al numero 1
    sub al, 30h
    ;salvo il numero in n1
    mov n1, al
    
    ;ritorno a capo
    lea dx, newline
    mov ah, 9
    int 21h
    
    ;scrivo a schermo il messaggio per il secondo numero
    lea dx, inser2_message
    mov ah, 9
    int 21h  
    
    ;prendo un carattere da input
    mov ah, 01    
    int 21h
    
    ;tolgo 30h per passare dal carattere '2' al numero 2
    sub al, 30h
    ;salvo il numero in n2
    mov n2, al
    
    mov bl, n1  ;carico n1
    add bl, n2  ;bl = n1 + n2
    
    mov result, bl
    
    cmp result, 10
    jl single_char
    jge double_char
    
 
single_char:
    
    mov dl, result
    add dl, 30h
    
    ; stampo il carattere singolo
    mov ah, 5
    int 21h

    jmp endfile
                    
double_char:
    mov ah, 0
    mov al, result
    
    ; divido il risultato in al per 10 in ah 
    mov ch, 10
    div ch
    
    mov n1, al
    mov n2, ah
    
    ; ritorno a capo
    lea dx, newline
    mov ah, 9
    int 21h
    
    ; scrivo il messaggio di risultato
    lea dx, result_message
    mov ah, 9
    int 21h
    
    ; scrivo il primo carattere
    mov dl, n1
    add dl, 30h
    
    mov ah, 2
    int 21h
    
    ; scrivo il secondo carattere                
    mov dl, n2
    add dl, 30h ;passo da numero 5 a carattere 5+30h = 35h che e il carattere '5'
    
    mov ah, 2
    int 21h 
    
    
    
    jmp endfile
         
    ;ritorno a capo
    ;lea dx, newline
    ;mov ah, 9
    ;int 21h
        
    ;scrivo a schermo il risultato
    ;lea dx, result_message
    ;mov ah, 9
    ;int 21h   
       
    ;scrivo il risultato
    ;mov dl, result
    ;mov ah, 2
    ;int 21h  
        
    ;ritorno a capo
    ;lea dx, newline
    ;mov ah, 9
    ;int 21h
endfile:
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.

