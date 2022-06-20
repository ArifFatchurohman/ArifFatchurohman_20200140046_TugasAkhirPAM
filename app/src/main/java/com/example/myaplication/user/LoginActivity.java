package com.example.myaplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myaplication.MainActivity;
import com.example.myaplication.R;

public class LoginActivity extends AppCompatActivity {

    EditText TxEmail, TxPassword;
    Button BtnLogin;
    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TxEmail = (EditText) findViewById(R.id.txEmail);
        TxPassword = (EditText) findViewById(R.id.txPassword);
        BtnLogin = (Button) findViewById(R.id.btnLogin);

        mainActivity = new MainActivity(this);

        TextView tvCreateAccount = (TextView) findViewById(R.id.tvCreateAccount);

        tvCreateAccount.setText("Belum punya akun?.");
        tvCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = TxEmail.getText().toString().trim();
                String password = TxPassword.getText().toString().trim();

                Boolean res = mainActivity.checkUser(email, password);
                Boolean resEmail = mainActivity.checkemail(email);
                Boolean resPasswd = mainActivity.checkPasswd(password);
                if (res == true) {
                    Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, AddActivity.class));
                } else {
                    if (TxEmail.getText().toString().isEmpty()) {
                        TxEmail.setError("Email Tidak Boleh Kosong!");
                    } else if (resEmail == false) {
                        TxEmail.setError("Harap Masukkan Email Yang Valid!");
                    }
                    if (TxPassword.getText().toString().isEmpty()) {
                        TxPassword.setError("Password Tidak Boleh Kosong!");
                    } else if (TxPassword.length() < 6) {
                        TxPassword.setError("Password Harus 6 Karakter atau Lebih!");
                    } else if (resPasswd == false) {
                        TxPassword.setError("Password Salah");
                    } else {
                        Toast.makeText(LoginActivity.this, "Login gagal, Silahkan coba kembali!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public static Spanned fromHtml(String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}