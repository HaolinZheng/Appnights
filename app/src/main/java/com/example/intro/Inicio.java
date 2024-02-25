package com.example.intro;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.intro.databinding.FragmentInicioBinding;

public class Inicio extends Fragment {

    TextView text1, text2, text3, text4;
    Button button1, button2;
    NavController navController;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        text1 = view.findViewById(R.id.text1);
        text2 = view.findViewById(R.id.text2);
        text3 = view.findViewById(R.id.text3);
        text4 = view.findViewById(R.id.text4);
        button1 = view.findViewById(R.id.boton1);
        button2 = view.findViewById(R.id.boton2);

        escribirTexto(text1, "Bienvenido/a", 0);
        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.iniciodesesion);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.registrarme);
            }
        });
    }
    private void escribirTexto(final TextView textView, final String textoCompleto, final int indice) {
        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (indice <= textoCompleto.length()) {
                textView.setText(textoCompleto.substring(0, indice));
                escribirTexto(textView, textoCompleto, indice + 1);
            } else {
                switch (textoCompleto) {
                    case "Bienvenido/a":
                        escribirTexto(text2, "Doctor.........", 0);
                        break;
                    case "Doctor.........":
                        escribirTexto(text3, "Espera,", 0);
                        break;
                    case "Espera,":
                        escribirTexto(text4, "usted quien era?", 0);
                        break;
                    case "usted quien era?":
                        button1.setVisibility(View.VISIBLE);
                        button1.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.aparecion));
                        button2.setVisibility(View.VISIBLE);
                        button2.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.aparecion));
                        break;
                }
            }
        }, 50);
    }

}