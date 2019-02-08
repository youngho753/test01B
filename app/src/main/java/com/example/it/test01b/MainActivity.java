package com.example.it.test01b;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.it.test01b.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String gender;
    String blood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edit1 = findViewById(R.id.edt1); //키
        final EditText edit2 = findViewById(R.id.edt2); //몸무게

        final RadioButton rb1 = findViewById(R.id.rb1); // 성별 남
        final RadioButton rb2 = findViewById(R.id.rb2); // 성별 여

        final Spinner spin1 = findViewById(R.id.spin1); //혈액형

        final CheckBox chk1 = findViewById(R.id.chk1); //습관1
        final CheckBox chk2 = findViewById(R.id.chk2); //습관2
        final CheckBox chk3 = findViewById(R.id.chk3); //습관3

        final Gallery ga1 = findViewById(R.id.ga1);

        Button btn1 = findViewById(R.id.btn1); // 결과보기 버튼

        rb1.setOnClickListener(new View.OnClickListener() { //성별 선택 남자
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                gender = rb.getText().toString();
            }
        });
        rb2.setOnClickListener(new View.OnClickListener() { //성별 선택 여자
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) v;
                gender = rb.getText().toString();
            }
        });

        final String blood_type[] = {"A","B","O","AB"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,blood_type);

        spin1.setAdapter(adapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {// 버튼 누름
                blood = spin1.getSelectedItem().toString(); //혈액형

                ArrayList<Integer> habbits = new ArrayList<Integer>();

                TextView tv1 = findViewById(R.id.tv1);
                TextView tv2 = findViewById(R.id.tv2);
                tv1.setText(blood + "형 " + gender +" 입니다!"); //tv1 수정



                // 다이얼로그
                if(edit1.getText().toString().equals("") || edit2.getText().toString().equals("")){
                    tv2.setText("2.신체질량지수는 ??? 입니다!");

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("키와 체중")
                            .setView(getLayoutInflater().inflate(R.layout.custom_dialog,null))
                            .show();
                }else{
                    //tv2 수정
                    double height = Double.parseDouble(edit1.getText().toString()); //키
                    double weight = Double.parseDouble(edit2.getText().toString()); //몸무게
                    double result1 = weight/((height/100)*(height/100)); //
                    String str = String.format("%.2f",result1);
                    tv2.setText("신체 질량 지수는" + str + "입니다");

                    //버릇들
                    if(chk1.isChecked())
                        habbits.add(R.drawable.drinking);
                    if(chk2.isChecked())
                        habbits.add(R.drawable.ciga);
                    if(!chk3.isChecked())
                        habbits.add(R.drawable.running);

                    if(chk1.isChecked() || chk2.isChecked() || !chk3.isChecked()){
                        ga1.setAdapter(new ImageAdapter(MainActivity.this,habbits));
                    }else{
                        ga1.removeAllViewsInLayout();
                    }
                }


            }
        });




    }
}
