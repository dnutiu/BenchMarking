from matplotlib import pyplot as plt

plt.style.use('fivethirtyeight')

# blue line
plt.plot([50, 100, 250, 500, 1000, 5000, 10000, 100000, 500000],
         [0.4, 0.6, 1, 4, 9, 83, 244, 18318, 460536], 'b-')

# red circle
plt.plot([50, 100, 250, 500, 1000, 5000, 10000, 100000, 500000],
         [0.4, 0.6, 1, 4, 9, 83, 244, 18318, 460536], 'ro')

plt.axis([0, 500000, 0, 500000])
plt.ylabel('Benchmark time in ms')
plt.xlabel('Number of pi digits')
plt.show()
