from matplotlib import pyplot as plt
from numpy import average


hours = []
temperatures = []
with open("temperature.txt","r") as f:
    for line in f.readlines():
        hour, temperature = line.split(' ')
        hours.append(int(hour))
        temperatures.append(int(temperature))

plt.plot(hours,temperatures)
retta = ((min(hours),max(hours)),(min(temperatures),max(temperatures)))
plt.plot(retta)
plt.show()

