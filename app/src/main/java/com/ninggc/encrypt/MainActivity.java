package com.ninggc.encrypt;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static String key;
    ClipboardManager clipboardManager;
    EditText et_key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        et_key = findViewById(R.id.main_et_key);
        findViewById(R.id.main_btn_encrypt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = et_key.getText().toString();
                final String value = MD5Util.GetMD5Code(key);
                final String value_0_16 = value.substring(0, 16);
                final String value_16_32 = value.substring(16, 32);
                if (key == null || "".equals(key)) {
                    Toast.makeText(MainActivity.this, "请输入", Toast.LENGTH_SHORT).show();
                    return;
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("加密后的字符串：\n" + value)
                        .setPositiveButton("复制后十六位   ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                clipboardManager.setPrimaryClip(ClipData.newPlainText("value", value_16_32));
                                Toast.makeText(MainActivity.this, "后十六位复制成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("复制前十六位   ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                clipboardManager.setPrimaryClip(ClipData.newPlainText("value", value_0_16));
                                Toast.makeText(MainActivity.this, "前十六位复制成功", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
