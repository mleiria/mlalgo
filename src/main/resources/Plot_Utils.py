import matplotlib.pyplot as plt
import numpy as np

fun = lambda x: pow(x, 3) - x - 2.0

x = np.arange(1.0, 2.0, 0.01)
y = fun(x)

#plt.plot(x, y)
#plt.show()

fig, ax = plt.subplots()
ax.plot(x, fun(x))
#ax.set_aspect('equal')
ax.grid(True, which='both')

ax.axhline(y=0, color='k')
ax.axvline(x=0, color='k')
plt.savefig('/home/manuel/Elements/DeepLearningBlog/mleiria.github.io/images/2021-05-03-Metodos_Numericos-Biseccao_1.png')
plt.show()