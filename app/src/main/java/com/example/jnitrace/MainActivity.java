package com.example.jnitrace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {
    protected Button btnTestHook;

    public native void startJnitrace(String soname);

    public native void startXhook(String soname);

    static {
        System.loadLibrary("jnitrace");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), "64bit：" + String.valueOf(android.os.Process.is64Bit()), Toast.LENGTH_LONG).show();

        btnTestHook = (Button) findViewById(R.id.btn_test_hook);
        btnTestHook.setOnClickListener(testHookClickListener);

        startJnitrace("tracetest");
        startXhook("tracetest");

        System.loadLibrary("tracetest");
    }

    private View.OnClickListener testHookClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            traceTest.testHook("1234");
        }
    };
}