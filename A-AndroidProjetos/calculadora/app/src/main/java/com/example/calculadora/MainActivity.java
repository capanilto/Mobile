package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Calculatec calc;
    private boolean usuarioEstaDigitandoUmNumero;
    private boolean separadorDecimalFoiDigitado;
    private TextView txtVisor;
    private String separador;
    private char separadorChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calc = new Calculatec();
        usuarioEstaDigitandoUmNumero = false;
        separadorDecimalFoiDigitado = false;

        txtVisor = (TextView) findViewById(R.id.txtVisor);
        txtVisor .setText("0");

        // Separador decimal
        Locale localizacao = getResources().getConfiguration().locale;
        NumberFormat formatador = NumberFormat.getInstance(localizacao);
        separadorChar = ',';
        if(formatador instanceof DecimalFormat) {
            DecimalFormatSymbols simbolo = ((DecimalFormat)formatador).getDecimalFormatSymbols();
            separadorChar = simbolo.getDecimalSeparator();
        }
        separador = String.valueOf(separadorChar);
        Button btnSeparador = (Button)findViewById(R.id.button18);
        btnSeparador.setText(separador);
    }
    public void onClickQualquerCoisa(View v) {

    }
    public void onClickNumeros(View v) {
        Button botaoTocado = (Button) v;
        String digito = botaoTocado.getText().toString();
        String textoNoVisor = txtVisor.getText().toString();
        if(!usuarioEstaDigitandoUmNumero || textoNoVisor.equals("0")) {
            txtVisor .setText(digito);
            if(!digito.equals("0")) {
                usuarioEstaDigitandoUmNumero = true;
            }
        } else {
            txtVisor.setText(textoNoVisor + digito);
        }
    }

    public void onClickOperacoes(View v) {
        Button botaoTocado = (Button) v;
        String operacao = botaoTocado.getText().toString();
        if(operacao.equals(separador) && !separadorDecimalFoiDigitado) {
            separadorDecimalFoiDigitado = true;
            if(!usuarioEstaDigitandoUmNumero) {
                txtVisor.setText("0"+separador);
            } else {
                txtVisor.setText(txtVisor.getText().toString() + separador);
            }
            usuarioEstaDigitandoUmNumero = true;
        } else if(!operacao.equals(separador)) {
            String valorSemVirgula = txtVisor.getText().toString().replace(separadorChar,'.');
            calc.setOperando(Double.parseDouble(valorSemVirgula));
            calc.realizarOperacao(operacao);
            atualizarVisor();

            usuarioEstaDigitandoUmNumero = false;
            separadorDecimalFoiDigitado = false;
        }

    }

    public void onClickMemoria(View v) {
        Button botaoTocado = (Button) v;
        String operacaoMemoria = botaoTocado.getText().toString();
        if(operacaoMemoria.equals("«")) {

            String textoResultado = txtVisor.getText().toString();
            if(textoResultado.length() <= 1) {
                txtVisor.setText("0");
            } else {
                textoResultado = textoResultado.substring(0, textoResultado.length() - 1);
                txtVisor.setText(textoResultado);
            }

        } else {
            String valorSemVirgula = txtVisor.getText().toString().replace(separadorChar, '.');
            calc.setOperando(Double.parseDouble(valorSemVirgula));
            calc.realizarOperacaoMemoria(operacaoMemoria);
            atualizarVisor();
            usuarioEstaDigitandoUmNumero = false;
        }

    }

    public void atualizarVisor() {
        String textoResultado = String.valueOf(calc.getOperando());
        if(textoResultado.endsWith(".0")) {
            textoResultado = textoResultado.substring(0, textoResultado.length() - 2);
        }
        txtVisor.setText(textoResultado.replace('.',separadorChar));
    }

}