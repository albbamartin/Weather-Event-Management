from sklearn.linear_model import LinearRegression
import pandas as pd
import numpy as np
import time
import os

def predictor(db, days_from_now):

    db = pd.read_csv(path, names=['ts', 'temperature'])

    X = db.drop(columns=['temperature'])
    y = db.drop(columns=['ts'])

    momentToPredict = getMomentToPredictInMs(days_from_now)

    LR = LinearRegression()
    LR.fit(X, y)
    y_pred = LR.predict(momentToPredict.reshape(-1, 1))
    
    return (pd.to_datetime(momentToPredict, unit='ms').isoformat(), y_pred[0][0])

def getMomentToPredictInMs(days_from_now):

    ms_of_a_day = 86400000
    pressentMoment = np.array(time.time() * 1000)
    momentToPredict = pressentMoment + days_from_now * ms_of_a_day

    return momentToPredict


path = os.path.dirname(os.path.abspath(__file__)) + '/weather.csv'

days_from_now = int(input("¿Para dentro de cuántos días quieres saber la temperatura?: "))
dateAndPrediction = predictor(path, days_from_now)
print("La temperatura dentro de", days_from_now, "días (", dateAndPrediction[0], ") será de", round(dateAndPrediction[1], 2), "grados centígrados")