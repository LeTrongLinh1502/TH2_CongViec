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

public class UpdateDeleteActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edTen, edNoiDung, edNgay;
    private Spinner spTinhTrang,spCongTac;
    private Button btnUpdate, btnCancel,btnDelete;
    private CongViec congViec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        intitView();
        Intent intent=getIntent();
        congViec=(CongViec) intent.getSerializableExtra("congviec");
        edTen.setText(congViec.getTen());
        edNoiDung.setText(congViec.getNoidung());
        edNgay.setText(congViec.getNgayHT());
        for(int i=0;i<spTinhTrang.getCount();i++){
            if(congViec.getTinhtrang().equals(spTinhTrang.getItemAtPosition(i))){
                spTinhTrang.setSelection(i);
                break;
            }
        }
        for(int i=0;i<spCongTac.getCount();i++){
            if(congViec.getCongtac().equals(spCongTac.getItemAtPosition(i))){
                spCongTac.setSelection(i);
                break;
            }
        }

        edNgay.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    private void intitView() {
        edTen=findViewById(R.id.edTen);
        edNoiDung=findViewById(R.id.edNoiDung);
        edNgay=findViewById(R.id.edNgay);
        btnUpdate=findViewById(R.id.btnUpdate);
        btnCancel=findViewById(R.id.btnCancel);
        btnDelete=findViewById(R.id.btnDelete);
        spTinhTrang=findViewById(R.id.spinnerTinhTrang);
        spCongTac=findViewById(R.id.spinnerCongTac);
        spTinhTrang.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.TinhTrang)));
        spCongTac.setAdapter(new ArrayAdapter<String>(this,R.layout.item_spinner,
                getResources().getStringArray(R.array.CongTac)));
    }

    @Override
    public void onClick(View view) {
        if(view==btnCancel){
            finish();
        }
        if (view==btnDelete){
            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.deleteCongViec(congViec.getId());
            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        }
        if (view==btnUpdate){
            String ten = edTen.getText().toString();
            String noidung = edNoiDung.getText().toString();
            String ngay = edNgay.getText().toString();
            String tinhtrang = spTinhTrang.getSelectedItem().toString();
            String congtac = spCongTac.getSelectedItem().toString();
            CongViec congviecUpdate = new CongViec(congViec.getId(),ten,noidung,ngay,tinhtrang,congtac);

            SQLiteHelper sqLiteHelper = new SQLiteHelper(UpdateDeleteActivity.this);
            sqLiteHelper.updateCongViec(congviecUpdate);

            Intent intentToMainActivity = new Intent(UpdateDeleteActivity.this, MainActivity.class);
            startActivity(intentToMainActivity);
        }
        if(view==edNgay){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
    }
}