package com.example.th2_congviec.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.th2_congviec.model.CongViec;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "CongViecDB.db";
    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE congviecs(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "noidung TEXT," +
                "ngayHT TEXT," +
                "tinhtrang TEXT," +
                "congtac TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    public long addCongViec(CongViec congViec){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congViec.getTen());
        contentValues.put("noidung", congViec.getNoidung());
        contentValues.put("ngayHT", congViec.getNgayHT());
        contentValues.put("tinhtrang", congViec.getTinhtrang());
        contentValues.put("congtac", congViec.getCongtac());
        long result = sqLiteDatabase.insert("congviecs", null,contentValues);
        sqLiteDatabase.close();
        return result;
    }
    public List<CongViec> getAllCongViec(){
        List<CongViec> bookList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("congviecs",null,null,
                null,null,null,null);
        while (cursor!=null && cursor.moveToNext()){
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String noidung = cursor.getString(2);
            String ngayHT = cursor.getString(3);
            String tinhtrang = cursor.getString(4);
            String congtac = cursor.getString(5)+"";
            CongViec congViec = new CongViec(id,ten,noidung,ngayHT,tinhtrang,congtac);
            bookList.add(congViec);
        }
        return bookList;
    }
    public long updateCongViec(CongViec congViec){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ten", congViec.getTen());
        contentValues.put("noidung", congViec.getNoidung());
        contentValues.put("ngayHT", congViec.getNgayHT());
        contentValues.put("tinhtrang", congViec.getTinhtrang());
        contentValues.put("congtac", congViec.getCongtac());
        long result = sqLiteDatabase.update("congviecs",
                contentValues,"_id=?",
                new String[]{congViec.getId()+""});
        sqLiteDatabase.close();
        return result;
    }

    public long deleteCongViec(int congViecId){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.delete("congviecs", "_id=?",
                new String[]{congViecId+""});
        sqLiteDatabase.close();
        return result;
    }
    public List<CongViec> getStatistic(){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        List<CongViec> list = new ArrayList<>();
        Cursor rs = sqLiteDatabase.query("congviecs",new String[]{"_id","ten","noidung","ngayHT","tinhtrang","congtac"},
                null, null,"ngayHT",null,"ngayHT DESC");
        while(rs!=null && rs.moveToNext()){
            int id= rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang  = rs.getString(4);
            String congtac  = rs.getString(5);
            list.add(new CongViec(id,ten,noidung,ngay,tinhtrang,congtac));
        }
        return list;
    }
    public List<CongViec> searchByNoiDung(String xau) {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "noidung like ?";
        String[] whereArgs = {"%"+xau+"%"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                "ngayHT", null, "ngayHT ASC");
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String ten = rs.getString(1);
            String noidung = rs.getString(2);
            String ngay = rs.getString(3);
            String tinhtrang  = rs.getString(4);
            String congtac  = rs.getString(5);
            list.add(new CongViec(id,ten,noidung,ngay,tinhtrang,congtac));
        }
        return list;
    }
    public int demTinhTrang1() {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "tinhtrang like ?";
        String[] whereArgs = {"Chưa thực hiện"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        return rs.getCount();
    }
    public int demTinhTrang2() {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "tinhtrang like ?";
        String[] whereArgs = {"Đang thực hiện"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        return rs.getCount();
    }
    public int demTinhTrang3() {
        List<CongViec> list = new ArrayList<>();
        String whereClause = "tinhtrang like ?";
        String[] whereArgs = {"Hoàn thành"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("congviecs",
                null, whereClause, whereArgs,
                null, null, null);
        return rs.getCount();
    }
}
