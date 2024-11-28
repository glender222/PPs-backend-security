package com.example.demo.login.Enum;

public enum EstadoPPP {
    SIN_CARTA("0"),
    PENDIENTE("PENDIENTE"),
    APROBADA("1");

    private final String valor;

    EstadoPPP(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}