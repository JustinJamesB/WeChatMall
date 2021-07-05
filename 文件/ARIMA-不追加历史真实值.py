# -*- coding: utf-8 -*-
"""
Created on Fri Jan 15 23:37:09 2021

@author: JoeLiao‘s ASUS
"""

from pandas import read_csv
from pandas import datetime
from matplotlib import pyplot
from statsmodels.tsa.arima_model import ARIMA
from sklearn.metrics import mean_squared_error
 
 
series = read_csv('shampoo-sales-12-31.csv', header=None, parse_dates=[0], index_col=0, squeeze=True,)
print(series)
X = series.values
size = int(len(X) * 0.66)
train, test = X[0:size], X[size:len(X)]
history = [x for x in train]
predictions = list()
lastData=0
for t in range(len(test)+31):
    model = ARIMA(history, order=(5,1,0))
    model_fit = model.fit(disp=0)
    output = model_fit.forecast()
    yhat = output[0]
    predictions.append(yhat)
    if t < len(test) :
        obs = test[t]
        history.append(obs)
        print('date=%.10s, predicted=%f, expected=%f' % (series.index[size+t],yhat, obs))
    else:
        history.append(lastData)
        print('predicted=%f' % yhat)
    lastData=yhat
#均方误差
#error = mean_squared_error(test, predictions)
#print('Test MSE: %.3f' % error)
# plot
pyplot.plot(test)
pyplot.plot(predictions, color='red')
pyplot.show()