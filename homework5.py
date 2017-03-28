import matplotlib

matplotlib.pyplot.use('fivethirtyeight')

# blue line x -> y()
matplotlib.pyplot.plot([4, 6, 9, 14, 25, 47, 103, 177, 354, 741],
                       [1, 2, 4, 8, 16, 32, 64, 128, 256, 512], 'b-')

matplotlib.pyplot.plot([4, 6, 9, 14, 25, 47, 103, 177, 354, 741],
                       [1, 2, 4, 8, 16, 32, 64, 128, 256, 512], 'ro')

matplotlib.pyplot.axis([0, 1000, 0, 512])
matplotlib.pyplot.ylabel('l')
matplotlib.pyplot.xlabel('t')
matplotlib.pyplot.show()
