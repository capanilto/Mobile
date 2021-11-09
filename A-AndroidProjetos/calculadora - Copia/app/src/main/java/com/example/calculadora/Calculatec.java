package com.example.calculadora;


public class Calculatec {

    private double operando;
    private double doperandoAnterior;
    private String operandoAnterior;
    private double memoria;

    public Calculatec() {
        operando = 0;
        doperandoAnterior = 0;
        operandoAnterior = "";
        memoria = 0;
    }

    public double getOperando() {
        return operando;
    }

    public void setOperando(double operando) {
        this.operando = operando;
    }

    public void realizarOperacaoAnterior() {
        if (!operandoAnterior.equals("")) {
            if(operandoAnterior.equals("+")) {
                operando = doperandoAnterior + operando;
            } else if(operandoAnterior.equals("-")) {
                operando = doperandoAnterior - operando;
            } else if(operandoAnterior.equals("X")) {
                operando = doperandoAnterior * operando;
            } else if(operandoAnterior.equals("÷")) {
                if(operando !=0) {
                    operando = doperandoAnterior / operando;
                }
            }
        }
    }

    public void realizarOperacao(String op){
        if(op.equals("%")) {
            Double x = doperandoAnterior;
            if(operandoAnterior.equals("+")) {
                operando = x + (doperandoAnterior * operando)/100;
            } else if(operandoAnterior.equals("-")) {
                operando = x - (doperandoAnterior * operando)/100;
            } else {
                operando = (doperandoAnterior * operando)/100;
            }

        } else if (op.equals("+/-")) {
            operando = -operando;

        }  else if(op.equals("C")) {
            operando = 0;
            memoria = 0;
            operandoAnterior = "";
        } else {
            realizarOperacaoAnterior();
            operandoAnterior = op;
            doperandoAnterior = operando;
        }
    }

    public void realizarOperacaoMemoria(String opm){
        if(opm.equals("Mc")) {
            memoria = 0;
        } else if(opm.equals("M+")) {
            memoria += operando;
        } else if(opm.equals("M-")) {
            memoria -= operando;
        } else if(opm.equals("Mr")) {
            operando = memoria;
        }
    }


}
