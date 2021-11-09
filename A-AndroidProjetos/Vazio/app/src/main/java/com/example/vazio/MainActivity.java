package com.example.vazio;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;

public	class MainActivity extends AppCompatActivity {

    Button mButton = (Button) findViewById(R.id.button);
    NumberPicker mNumberPicker = (NumberPicker) findViewById(R.id.numberPicker);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main1);


	    mNumberPicker.setMinValue(2015);
        mNumberPicker.setMaxValue(2030);
        mNumberPicker.setWrapSelectorWheel(true);
        mNumberPicker.setOnValueChangedListener((picker, oldVal, newVal) -> exibirSaldo(newVal));
    }

        View.OnClickListener mB = (new View.OnClickListener() {
        @Override
        public void onClick(View mB) {
            if	(!mEditTextValor.getText().toString(). isEmpty()) {
                // Recupera o valor digitado e o converte para float
                float valor	=	Float.parseFloat(mEditTextValor. getText().toString());
                // Recupera o ano selecionado
                int ano = mNumberPicker.getValue();
                //	Verifica	qual	RadioButton	está                 selecionado
                // Recuperamos o ID do RadioButton que está selecionado e comparamos com o ID dos  RadioButtons que criamos no layout
                switch (mRadioGroup.getCheckedRadioButtonId()) {
                    // Caso o RadioButton Adicionar esteja                    selecionado
                    case R.id.radioButton2:
                        adicionarValor(ano, valor);
                        break;
                    // Caso o RadioButton Excluir esteja                    selecionado
                    case R.id.radioButton3:
                        excluirValor(ano, valor);
                        break;
                }
                exibirSaldo(ano);
            }

        }
    });


    private void adicionarValor(int ano, float valor) {
        SharedPreferences sharedPreferences = getSharedPreferences("MeusDados", Context. MODE_PRIVATE);
        float valorAtual = sharedPreferences.getFloat(String. valueOf(ano), 0);
        float novoValor = valorAtual + valor;
        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
    }

    private void excluirValor(int ano, float valor) {
        SharedPreferences	sharedPreferences	= getSharedPreferences("MeusDados",	Context.MODE_PRIVATE);
        float valorAtual = sharedPreferences.getFloat(String. valueOf(ano), 0);
        float novoValor = valorAtual - valor;
        if (novoValor < 0) {
            novoValor = 0;
        }
        sharedPreferences.edit().putFloat(String.valueOf(ano), novoValor).apply();
    }

    private void exibirSaldo(int ano) {
        SharedPreferences sharedPreferences = getSharedPreferences("MeusDados", Context. MODE_PRIVATE);
        float saldo = sharedPreferences.getFloat(String. valueOf(ano), 0);
        mTextViewSaldo.setText(String.format("R$ %.2f", saldo));   }
}
