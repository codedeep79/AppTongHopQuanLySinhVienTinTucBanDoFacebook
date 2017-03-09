package phongban;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import cosodulieu.cosodulieunhanvien;

public class themphongban extends Activity {
	SQLiteDatabase writeData;
	cosodulieunhanvien duLieuNhanVien;
	public themphongban() {
		duLieuNhanVien = new cosodulieunhanvien(this);
		writeData = duLieuNhanVien.getWritableDatabase();
	}
	public void themPhong(){
		
	}
}
