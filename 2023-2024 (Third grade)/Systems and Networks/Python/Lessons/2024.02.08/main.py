import numpy as np

def choice(*scelte : str):
    while True:
        scelta = input(f"Scegli un opzione ({'/'.join(scelte)}): ").lower()

        if any([s for s in scelte if s == scelta]): return scelta
        else: print("Scelta non valida!")

def getretta():
    x=np.linspace(0,2*np.pi,200)
    m=int(input("inserisci il coefficiente angolare della retta: "))
    q=int(input("inserisci l'intercetta della retta: "))
    #print("y=",m,"x+",q)
    #sistemare il segno davanti a q
    #print("coordinate punto:")
    x1=int(input("inserisci l'ascissa del punto: "))
    y1=int(input("inserisci l'ordinata del punto: "))
    if (y1==m*x1+q): print("il punto P(",x1,";",y1,") appartiene alla retta y=",m,"x+",q)
    else: print("il punto non appartiene alla retta")
    return m,q
    #F1=m*x+q
    #plt.plot(F1, label="retta")

scelta = choice("retta","parabola")


match(scelta):
    case "retta":
        scelta = choice("implicita","esplicita")
        m,q = getretta()
        if scelta == "implicita":
            if m==0:
                print(f"{m}x+{q}=0")
            elif q == 0:
                print(f"{m}x+{q}=0")
            else: 
                print(f"{m}x+{q}=0")
        elif scelta == "esplicita":
            if m==0:
                print(f"y={q}")
            elif q == 0:
                print(f"y={m}x")
            else: 
                print(f"y={m}x+{q}")

    case "parabola":
        pass


