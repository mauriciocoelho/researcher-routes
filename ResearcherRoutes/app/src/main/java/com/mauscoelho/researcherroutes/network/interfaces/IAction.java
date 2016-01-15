package com.mauscoelho.researcherroutes.network.interfaces;

public interface IAction<T> {
    void OnCompleted(T response);
    void OnError(T response);
}
