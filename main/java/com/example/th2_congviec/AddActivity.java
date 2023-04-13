package com.example.th2_congviec;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.th2_congviec.database.SQLiteHelper;
import com.example.th2_congviec.model.CongViec;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edTen, edNoiDung, edNgay;
    private Spinner spTinhTrang,spCongTac;
    private Button btnUpdate, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        edNgay.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void initView() {
        edTen=findViewById(R.id.edTen);
        edNoiDung=findViewById(R.id.edNoiDung);
        edNgay=findViewById(R.id.edNgay);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);
        spTinhTrang=findViewById(R.id.spinnerTinhTrang);
        spCongTac=findViewById(R.id.spinnerCongTac);
        spTinhTrang.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.TinhTrang)));
        spCongTac.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.CongTac)));
    }

    @Override
    public void onClick(View view) {
        if(view==edNgay){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String mm="",dd="";
                    if (m<10){
                        mm="0"+(m+1);
                    }
                    else mm=(m+1)+"";
                    if (d<10){
                        dd="0"+d;
                    }
                    else dd=d+"";
                    edNgay.setText(dd+"/"+mm+"/"+y);
                }
            },year, month, day);
            datePickerDialog.show();
        }
        if (view==btnCancel){
            finish();
        }
        if (view==btnUpdate){
            String ten = edTen.getText().toString();
            String noidung = edNoiDung.getText().toString();
            String ngay = edNgay.getText().toString();
            String tinhtrang = spTinhTrang.getSelectedItem().toString();
            String congtac = spCongTac.getSelectedItem().toString();
            CongViec congViecAdd = new CongViec(ten,noidung,ngay,tinhtrang,congtac);
            SQLiteHelper db = new SQLiteHelper(this);
            db.addCongViec(congViecAdd);
            Intent intent = new Intent(AddActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}