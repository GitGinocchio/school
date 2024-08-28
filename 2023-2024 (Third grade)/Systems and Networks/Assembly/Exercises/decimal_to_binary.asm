; multi-segment executable file template.

data segment
    ; add your data here! 
    newline db 10, 13, "$"
    messaggio1 db "Inserisci un numero: $"
    messaggio2 db "Il numero in binario e' il seguente(il MSB e' a destra e non a sinistra): $"
    num db ?
   
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
    ; Il programma prende in input un numero decimale da 0 a 9 e lo trasforma in binario
    ; 8
    ; 8 / 2 = 4 r: 0
    ; 4 / 2 = 2 r: 0
    ; 2 / 2 = 1 r: 0
    ; 1 / 2 = 0 r: 1
    
    
    ; Scrivo il messaggio di input
    lea dx, messaggio1
    mov ah, 09
    int 21h
    
    ; Chiedo il numero in input
    mov ah, 01
    int 21h
    sub al, 30h
    mov num, al 
       
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
   
    mov bh, num   ; Metto il dividendo in bh
    mov bl, 2d    ; Metto in bl il divisore 2
    
    ; Scrivo il messaggio di output
    lea dx, messaggio2
    mov ah, 09
    int 21h
     
    ; Vado a capo
    lea dx, newline
    mov ah, 09
    int 21h
    
ottieni_cifra_binaria:
    
    mov ax, 0     ; Assicuro che ax=0
    mov al, bh    ; Sposto temporaneamente il numero da dividere da bh ad al
    div bl        ; al = ax / bl
    mov bh, al    ; Sposto il risultato della divisione nuovamente in bh
    
    mov dl, ah    ; Sposto il modulo del resto da ah a dl
    add dl, 30h   ; Trasformo il valore in un carattere ascii
    
    mov ah, 02    ; Scrivo il carattere a schermo
    int 21h
    
    cmp bh, 0d    ; Comparo il risultato in bh con 0
    jg ottieni_cifra_binaria
    
    
    mov ax, 4c00h ; exit to operating system.
    int 21h    
ends

end start ; set entry point and stop the assembler.
