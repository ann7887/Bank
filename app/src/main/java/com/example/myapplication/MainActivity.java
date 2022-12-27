package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView text;
    double summa = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        MyThread plus=  new MyThread("+");
        plus.start();
        MyThread minus = new MyThread("-");
        minus.start();
    }

    class MyThread extends Thread{
        private String mess;

        public MyThread(String mess) {
            this.mess = mess;
        }
        @Override
        public void run(){
            double max = 100;
            for (int i = 0; i < max; i++) {
                if (Objects.equals(mess, "+")) {
                    summa += (1000*Math.random());
                }
                if (Objects.equals(mess, "-")) {
                    summa -= (1000*Math.random());
                }
                if (summa < 0) {
                    summa = 0;
                    message();
                }
                else {
                    messageok();
                }
                Log.d("RRR", String.valueOf(summa));
                try{
                    Thread.sleep(500);
                } catch(InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        public void messageok() {
            text.setText("balance = " + summa + "$");
        }
        public void message() {
            text.setText("Недостаточно средств");
        }
    }
}