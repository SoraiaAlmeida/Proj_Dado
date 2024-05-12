package br.edu.fateczl.dado;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RadioButton rbDado1;
    private RadioButton rbDado2;
    private RadioButton rbDado3;
    private Spinner spQtdNum;
    private Button btnGerar;
    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rbDado1 = findViewById(R.id.rbDado1);
        rbDado1.setChecked(true);
        rbDado2 = findViewById(R.id.rbDado2);
        rbDado3 = findViewById(R.id.rbDado3);
        spQtdNum = findViewById(R.id.spQtdNum);
        btnGerar = findViewById(R.id.btnGerar);
        tvRes = findViewById(R.id.tvRes);

        preencheSpinner();
        btnGerar.setOnClickListener(op -> gerar());
    }
    private void gerar() {
        StringBuffer buffer = new StringBuffer();
        Integer selectedFaces =  (Integer) spQtdNum.getSelectedItem();

        try {
            int faces = Integer.parseInt(String.valueOf(selectedFaces));

            int dados = 0;
            if (rbDado1.isChecked()) {
                dados = 1;
            } else if (rbDado2.isChecked()) {
                dados = 2;
            } else if (rbDado3.isChecked()) {
                dados = 3;
            }

            Random random = new Random();
            StringBuffer resultado = new StringBuffer();
            for (int i = 0; i < dados; i++) {
                int valor = random.nextInt(faces) + 1;
                resultado.append("Dado ").append(i + 1).append(": ").append(valor).append("\n");
            }

            tvRes.setText(resultado.toString());
        } catch (NumberFormatException e) {
                              }
    }

    private void preencheSpinner () {
        List<Integer> lista = new ArrayList<>();
        lista.add(2);
        lista.add(4);
        lista.add(8);
        lista.add(10);
        lista.add(12);
        lista.add(20);
        lista.add(100);

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, lista);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spQtdNum.setAdapter(adapter);

    }

}
