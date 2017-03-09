package cosodulieu;

import java.util.ArrayList;

import QuanlyNhanSu.nhanvien;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import phongban.phongban;

public class cosodulieunhanvien extends SQLiteOpenHelper{
	SQLiteDatabase myDb;
	Context context;
	final static String DB_NAME = "quanlynhanvien.db";
	final static int DB_VERSION = 1;
	final static String DB_TABLE_NHANVIEN_1 = "nhanvien";
	final static String DB_TABLE_NHANVIEN_3 = "phongban";
	final static String DB_TABLE_NHANVIEN_4 = "chungminhnhandan";
	
	final static String DB_NHANVIEN_MANHANVIEN = "manhanvien";
	final static String DB_NHANVIEN_TEN = "tennhanvien";
	final static String DB_NHANVIEN_GIOITINH = "gioitinh";
	final static String DB_NHANVIEN_GIADINH = "giadinh";
	final static String DB_NHANVIEN_NGAYSINH = "ngaysinh";
	final static String DB_NHANVIEN_EMAIL = "email";
	final static String DB_NHANVIEN_HINHANH = "hinhanh";
	final static String DB_NHANVIEN_DIENTHOAI = "dienthoai";
	
	final static String DB_NHANVIEN_SOCMND = "socmnd";
	final static String DB_NHANVIEN_NGAYCAPCMND = "ngaycap";
	final static String DB_NHANVIEN_NOICAPCMND = "noicap";
	final static String DB_NHANVIEN_QUEQUAN = "quequan";
	
	final static String DB_NHANVIEN_MAPHONGBAN = "maphongban";
	final static String DB_NHANVIEN_TENPHONGBAN = "tenphongban";
	final static String DB_NHANVIEN_SOLUONGNHANVIENPHONGBAN = "soluophongban";
	
	public cosodulieunhanvien(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		this.context = context;
		myDb = this.getWritableDatabase();
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
//		db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NHANVIEN_3+"");
//		db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NHANVIEN_4+"");
//		db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_NHANVIEN_1+"");
	
		String phongban = "CREATE TABLE "+DB_TABLE_NHANVIEN_3+" ("+DB_NHANVIEN_MAPHONGBAN+" NVARCHAR(20) PRIMARY KEY NOT NULL"
				+ ", "+DB_NHANVIEN_TENPHONGBAN+" NVARCHAR(100) NOT NULL"
				+ ", "+DB_NHANVIEN_SOLUONGNHANVIENPHONGBAN+" INTEGER)";
		String chungminhnhandan = "CREATE TABLE "+DB_TABLE_NHANVIEN_4+" ("+DB_NHANVIEN_SOCMND+" NVARCHAR(12) PRIMARY KEY NOT NULL"
				+ ", "+DB_NHANVIEN_NGAYCAPCMND+" NVARCHAR(50)"
				+ ", "+DB_NHANVIEN_NOICAPCMND+" NVARCHAR(50)"
				+ ", "+DB_NHANVIEN_QUEQUAN+" TEXT)";
		String nhanvien = "CREATE TABLE "+DB_TABLE_NHANVIEN_1+" ("+DB_NHANVIEN_MANHANVIEN+" NVARCHAR(20) PRIMARY KEY NOT NULL"
				+ ", "+DB_NHANVIEN_MAPHONGBAN+" NVARCHAR(20) NOT NULL"
				+ ", "+DB_NHANVIEN_TEN+" NVARCHAR(50) NOT NULL"
				+ ", "+DB_NHANVIEN_QUEQUAN+" TEXT"
				+ ", "+DB_NHANVIEN_GIOITINH+" NVARCHAR(5) NOT NULL"
				+ ", "+DB_NHANVIEN_GIADINH+" NVARCHAR(5)"
				+ ", "+DB_NHANVIEN_DIENTHOAI+" NVARCHAR(12)"
				+ ", "+DB_NHANVIEN_NGAYSINH+" NVARCHAR(50) NOT NULL"
				+ ", "+DB_NHANVIEN_EMAIL+" NVARCHAR(100) NOT NULL UNIQUE"
				+ ", "+DB_NHANVIEN_SOCMND+" NVARCHAR(12) NOT NULL"
				+ ", "+DB_NHANVIEN_NOICAPCMND+" NVARCHAR(50)"
				+ ", "+DB_NHANVIEN_HINHANH+" BLOB"
				+ ",  FOREIGN KEY ("+DB_NHANVIEN_MAPHONGBAN+") REFERENCES "+DB_TABLE_NHANVIEN_3+"("+DB_NHANVIEN_MAPHONGBAN+")"
				+ ",  FOREIGN KEY ("+DB_NHANVIEN_SOCMND+") REFERENCES "+DB_TABLE_NHANVIEN_4+"("+DB_NHANVIEN_SOCMND+"))";
		db.execSQL(chungminhnhandan);
		db.execSQL(phongban);
		db.execSQL(nhanvien);
	}

	public boolean themnhanvien(String manhanvien, String maphongban, String ten, String quequan
			, String gioitinh, String giadinh, String dienthoai, String ngaysinh
			, String email, String socmnd, String noicap){
		ContentValues values = new  ContentValues();
		values.put(DB_NHANVIEN_MANHANVIEN,manhanvien);
		values.put(DB_NHANVIEN_MAPHONGBAN,maphongban);
		values.put(DB_NHANVIEN_TEN,ten);
		values.put(DB_NHANVIEN_QUEQUAN,quequan);
		values.put(DB_NHANVIEN_GIOITINH,gioitinh);
		values.put(DB_NHANVIEN_GIADINH,giadinh);
		values.put(DB_NHANVIEN_DIENTHOAI,dienthoai);
		values.put(DB_NHANVIEN_NGAYSINH,ngaysinh );
		values.put(DB_NHANVIEN_EMAIL,email);
		values.put(DB_NHANVIEN_SOCMND,socmnd);
		values.put(DB_NHANVIEN_NOICAPCMND,noicap);
		
		long kq = myDb.insert(DB_TABLE_NHANVIEN_1, null, values);
		if (kq == -1)
			return false;
		return true;
	}
	
	public boolean capnhatnhanvien(String manhanvien, String maphongban, String ten, String quequan
			, String gioitinh, String giadinh, String dienthoai, String ngaysinh
			, String email, String socmnd, String noicap){
		ContentValues values = new  ContentValues();
		values.put(DB_NHANVIEN_MAPHONGBAN,maphongban);
		values.put(DB_NHANVIEN_TEN,ten);
		values.put(DB_NHANVIEN_QUEQUAN,quequan);
		values.put(DB_NHANVIEN_GIOITINH,gioitinh);
		values.put(DB_NHANVIEN_GIADINH,giadinh);
		values.put(DB_NHANVIEN_DIENTHOAI,dienthoai);
		values.put(DB_NHANVIEN_NGAYSINH,ngaysinh );
		values.put(DB_NHANVIEN_EMAIL,email);
		values.put(DB_NHANVIEN_SOCMND,socmnd);
		values.put(DB_NHANVIEN_NOICAPCMND,noicap);
		
		int kq = myDb.update(DB_TABLE_NHANVIEN_1, values, DB_NHANVIEN_MANHANVIEN + "=?" , new String[]{manhanvien});
		return true;
	}
	
	public ArrayList<nhanvien> listNhanVien(){
		ArrayList<nhanvien> nv = new ArrayList<>();
		Cursor cursor = myDb.rawQuery("SELECT * FROM "+DB_TABLE_NHANVIEN_1+"", null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			String manhanvien = cursor.getString(0);
			String maphongban = cursor.getString(1);
			String ten = cursor.getString(2);
			String quequan = cursor.getString(3);
			String gioitinh = cursor.getString(4);
			String giadinh = cursor.getString(5);
			String dienthoai = cursor.getString(6);
			String ngaysinh = cursor.getString(7);
			String email = cursor.getString(8);
			String socmnd = cursor.getString(9);
			String noicap = cursor.getString(10);
			nv.add(new nhanvien(manhanvien, maphongban, ten, quequan, gioitinh, giadinh, dienthoai, ngaysinh, email, socmnd, noicap));
			cursor.moveToNext();
		}
		return nv;
	}
	public boolean xoanhanvien(String manhanvien){
		int row = myDb.delete(DB_TABLE_NHANVIEN_1, DB_NHANVIEN_MANHANVIEN + "=?", new String[]{manhanvien});
		if(row > 0)
			return true;
		return false;
	}
	
	public boolean themPhongBan(String maphong, String tenphong){
		ContentValues values = new ContentValues();
		values.put(DB_NHANVIEN_MAPHONGBAN, maphong);
		values.put(DB_NHANVIEN_TENPHONGBAN, tenphong);
		myDb.insert(DB_TABLE_NHANVIEN_3, null, values);
		return true;
	}
	public boolean capNhatPhongBan(String maphong, String tenphong){
		ContentValues values = new ContentValues();
		values.put(DB_NHANVIEN_TENPHONGBAN, tenphong);
		myDb.update(DB_TABLE_NHANVIEN_3, values, DB_NHANVIEN_MAPHONGBAN + "=?" , new String[]{maphong});
		return true;
	}
	
	public boolean xoaPhongBan(String maphong){
		int kq = myDb.delete(DB_TABLE_NHANVIEN_3, DB_NHANVIEN_MAPHONGBAN + "=?" , new String[]{maphong});
		if (kq > 0)
			return true;
		return false;
	}
	public ArrayList<phongban> listPhongBan(){
		ArrayList<phongban> phong = new ArrayList<phongban>();
		Cursor cursor = myDb.rawQuery("SELECT * FROM "+DB_TABLE_NHANVIEN_3+"", null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast())
		{
			String maphong = cursor.getString(0);
			String tenphong = cursor.getString(1);
			phong.add(new phongban(maphong, tenphong));
			cursor.moveToNext();
		}
		return phong;
	}
	
	public ArrayList<String> listCodeRoom(){
		ArrayList<String> codeRoom = new ArrayList<String>();
		Cursor cursor = myDb.rawQuery("SELECT "+DB_NHANVIEN_MAPHONGBAN+" FROM "+DB_TABLE_NHANVIEN_3+"", null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()){
			String code = cursor.getString(0);
			codeRoom.add(code);
			cursor.moveToNext();
		}
		cursor.close();
		return codeRoom;
	}
	public boolean chungminhnhandan(String socmnd, String quequan, String noicap,String ngaycap)
	{
		ContentValues values = new  ContentValues();
		values.put(DB_NHANVIEN_SOCMND,socmnd);
		values.put(DB_NHANVIEN_NOICAPCMND,noicap);
		values.put(DB_NHANVIEN_QUEQUAN,quequan);
		values.put(DB_NHANVIEN_NGAYCAPCMND,ngaycap);
		
		long kq = myDb.insert(DB_TABLE_NHANVIEN_4, null, values);
		if (kq == -1)
			return false;
		return true;
	}
	
	public boolean capnhatchungminhnhandan(String socmnd, String quequan, String noicap,String ngaycap)
	{
		ContentValues values = new  ContentValues();
		values.put(DB_NHANVIEN_NOICAPCMND,noicap);
		values.put(DB_NHANVIEN_QUEQUAN,quequan);
		values.put(DB_NHANVIEN_NGAYCAPCMND,ngaycap);
		
		myDb.update(DB_TABLE_NHANVIEN_4, values, DB_NHANVIEN_SOCMND + "=?", new String[]{socmnd});
		return true;
	}
	
	public boolean xoachungminhnhandan(String socmnd)
	{
		int kq = myDb.delete(DB_TABLE_NHANVIEN_4, DB_NHANVIEN_SOCMND + "=?" , new String[]{socmnd});
		if (kq > 0)
			return true;
		return false;
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
