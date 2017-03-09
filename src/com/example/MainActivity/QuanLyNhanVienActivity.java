package com.example.MainActivity;

import com.example.quanlynhansu.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import QuanlyNhanSu.adapterEmployee;
import QuanlyNhanSu.nhanvien;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.provider.CalendarContract.Calendars;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import cosodulieu.cosodulieunhanvien;
import phongban.adapterPhongBan;
import phongban.phongban;

public class QuanLyNhanVienActivity extends Activity implements OnClickListener{
	TabHost tabHost;
	Button btnAddNhanVien, btnDeleteNhanVien, btnUpdateNhanVien, btnExitNhanVien,
		   btnAddPhongBan, btnDeletePhongBan, btnUpdatePhongBan, btnExitPhongBan;
	Button btnThemPhong, btnHuyPhong, btnCapNhatPhong, btnHuyCapNhatPhong;
	Button btnThem, btnHuyBo, btnCapNhat, btnHuyBoCapNhat;
	
	EditText edtCodeEmployee, edtNameEmployee, edtQueQuan
		, edtPhoneEmployee, edtBirthdayEmployee, edtEmailEmployee
		, edtCmnd, edtNoiCap, edtNgayCap;
	EditText edtCodeEmployee_sua, edtNameEmployee_sua, edtQueQuan_sua
		, edtPhoneEmployee_sua, edtBirthdayEmployee_sua, edtEmailEmployee_sua
		, edtCmnd_sua, edtNoiCap_sua, edtNgayCap_sua;
	EditText edtCodeRoom, edtRoomName, edtCodeRoom_sua, edtRoomName_sua;
	
	String manhanvien, maphongban, mahopdong
		, tennhanvien, quequan, gioitinh, giadinh
		, dienthoai , ngaysinh, email, ngaycap, cmnd, noicap;
	
	String maphong, tenphong;
	int soluongnhanvien;
	
	Spinner spinnerMaPhongBan; 
	Spinner spinnerMaPhongBan_sua; 
	ArrayAdapter<String> maPhongBan, maPhongBan_sua;
	adapterEmployee adapterNhanVien;
	adapterPhongBan adapterPhongBan;
	ArrayList<nhanvien> nhanvien;
	ArrayList<phongban> phongban;
	ArrayList<String> maPhong, maPhong_sua;
	ListView listNhanVien, listPhongBan;
	RadioButton rdYes, rdNo, rdNam, rdNu;
	RadioButton rdYes_sua, rdNo_sua, rdNam_sua, rdNu_sua;
	Dialog dialogThem, dialogSua, dialogThemPhong, dialogSuaPhong;
	Dialog dialogThemNhanVien, dialogSuaNhanVien, dialogThemPhongBan, dialogSuaPhongBan;
	
	cosodulieunhanvien cosodulieu;
	int viTriNhanVien = -1, viTriPhongBan = -1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quanlynhanvien);
		createTab("nhanvien", "Nhân viên", R.id.nhanvien);
		createTab("nhanvien", "Phòng ban", R.id.phongban);
		init();
		controlEvent();
		
		nhanvien = cosodulieu.listNhanVien();
		adapterNhanVien = new adapterEmployee(QuanLyNhanVienActivity.this
				, R.layout.customlist_add_employee, nhanvien);
		adapterNhanVien.notifyDataSetChanged();
		listNhanVien.setAdapter(adapterNhanVien);
		
		phongban = cosodulieu.listPhongBan();
		adapterPhongBan = new adapterPhongBan(QuanLyNhanVienActivity.this
				, R.layout.customlist_add_room, phongban);
		listPhongBan.setAdapter(adapterPhongBan);
	}
	public void controlEvent(){
		btnAddPhongBan.setOnClickListener(this);
		btnAddNhanVien.setOnClickListener(this);
		btnDeleteNhanVien.setOnClickListener(this);
		btnDeletePhongBan.setOnClickListener(this);
		btnExitNhanVien.setOnClickListener(this);
		btnExitPhongBan.setOnClickListener(this);
		btnUpdateNhanVien.setOnClickListener(this);
		btnUpdatePhongBan.setOnClickListener(this);
	}
	
	public void init(){
		listNhanVien 		= (ListView) findViewById(R.id.listNhanVien);
		listPhongBan		= (ListView) findViewById(R.id.listPhongBan);
		btnAddPhongBan 		= (Button) findViewById(R.id.btnAddPhongBan);
		btnAddNhanVien 		= (Button) findViewById(R.id.btnAddNhanVien);
		btnDeleteNhanVien 	= (Button) findViewById(R.id.btnDeleteNhanVien);
		btnDeletePhongBan	= (Button) findViewById(R.id.btnDeletePhongBan);
		btnExitNhanVien 	= (Button) findViewById(R.id.btnExitNhanVien);
		btnExitPhongBan 	= (Button) findViewById(R.id.btnExitPhongBan);
		btnUpdateNhanVien 	= (Button) findViewById(R.id.btnUpdateNhanVien);
		btnUpdatePhongBan 	= (Button) findViewById(R.id.btnUpdatePhongBan);
		/*------------Dialog add new employee-----------*/
		dialogThemNhanVien = openDialogThemNhanVien();
		dialogSuaNhanVien  = openDialogSuaNhanVien();
		dialogThemPhongBan = openDialogThemPhong();
		dialogSuaPhongBan  = openDialogSuaPhong();
		cosodulieu = new cosodulieunhanvien(getApplicationContext());
		initDialog();
		
	}
	public void initDialog(){
		/*------------Thêm nhân viên-----------------------*/
		
		spinnerMaPhongBan   = (Spinner) dialogThem.findViewById(R.id.spinnerMaPhongBan);
		edtCodeEmployee     = (EditText)dialogThem.findViewById(R.id.edtCodeEmployee);
		edtNameEmployee     = (EditText)dialogThem.findViewById(R.id.edtNameEmployee);
		edtQueQuan 		    = (EditText)dialogThem.findViewById(R.id.edtQueQuan);
		edtPhoneEmployee    = (EditText)dialogThem.findViewById(R.id.edtPhoneEmployee);
		edtBirthdayEmployee = (EditText)dialogThem.findViewById(R.id.edtBirthdayEmployee);
		edtEmailEmployee    = (EditText)dialogThem.findViewById(R.id.edtEmailEmployee);
		edtCmnd 		    = (EditText)dialogThem.findViewById(R.id.edtCmnd);
		edtNoiCap 		    = (EditText)dialogThem.findViewById(R.id.edtNoiCap);
		edtNgayCap 		    = (EditText)dialogThem.findViewById(R.id.edtNgayCap);
		rdYes 			    = (RadioButton) dialogThem.findViewById(R.id.rdYes);
		rdNo 			    = (RadioButton) dialogThem.findViewById(R.id.rdNo);
		rdNam 			    = (RadioButton) dialogThem.findViewById(R.id.rdNam);
		rdNu 			    = (RadioButton) dialogThem.findViewById(R.id.rdNu);
		btnThem 		    = (Button) dialogThem.findViewById(R.id.btnThem);
		btnHuyBo  		    = (Button) dialogThem.findViewById(R.id.btnHuyBo);
		
		/*--------Datepicker-----*/
//		
//		edtBirthdayEmployee.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Calendar now = Calendar.getInstance();
//				final SimpleDateFormat format = new SimpleDateFormat("dd//mm/yyyy");
//				final DatePickerDialog datePickerDialog = new DatePickerDialog(getApplicationContext(), new DatePickerDialog.OnDateSetListener() {
//					
//					@Override
//					public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//						Calendar calendarStart = Calendar.getInstance();
//						calendarStart.set(year, monthOfYear,dayOfMonth);
//						edtBirthdayEmployee.setText(format.format(calendarStart.getTime()));
//					}
//				}, now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
//				datePickerDialog.show();				
//			}
//		});
		
		/*-------Cap nhap nhân viên------------*/

		spinnerMaPhongBan_sua   = (Spinner) dialogSua.findViewById(R.id.spinnerMaPhongBan);
		edtCodeEmployee_sua     = (EditText)dialogSua.findViewById(R.id.edtCodeEmployee);
		edtNameEmployee_sua     = (EditText)dialogSua.findViewById(R.id.edtNameEmployee);
		edtQueQuan_sua 		    = (EditText)dialogSua.findViewById(R.id.edtQueQuan);
		edtPhoneEmployee_sua    = (EditText)dialogSua.findViewById(R.id.edtPhoneEmployee);
		edtBirthdayEmployee_sua = (EditText)dialogSua.findViewById(R.id.edtBirthdayEmployee);
		edtEmailEmployee_sua    = (EditText)dialogSua.findViewById(R.id.edtEmailEmployee);
		edtCmnd_sua 		    = (EditText)dialogSua.findViewById(R.id.edtCmnd);
		edtNoiCap_sua 		    = (EditText)dialogSua.findViewById(R.id.edtNoiCap);
		edtNgayCap_sua 		    = (EditText)dialogSua.findViewById(R.id.edtNgayCap);
		rdYes_sua 			    = (RadioButton) dialogSua.findViewById(R.id.rdYes);
		rdNo_sua 			    = (RadioButton) dialogSua.findViewById(R.id.rdNo);
		rdNam_sua 			    = (RadioButton) dialogSua.findViewById(R.id.rdNam);
		rdNu_sua 			    = (RadioButton) dialogSua.findViewById(R.id.rdNu);
		btnHuyBoCapNhat  		= (Button) dialogSua.findViewById(R.id.btnHuyBoCapNhat);
		btnCapNhat 				= (Button) dialogSua.findViewById(R.id.btnCapNhat);
		
		/*------------Thêm phòng------------------------*/
		
		edtCodeRoom 	= (EditText) dialogThemPhong.findViewById(R.id.edtCodeRoom);
		edtRoomName 	= (EditText) dialogThemPhong.findViewById(R.id.edtRoomName);
		btnThemPhong	= (Button)	 dialogThemPhong.findViewById(R.id.btnThemPhong);
		btnHuyPhong		= (Button)	 dialogThemPhong.findViewById(R.id.btnHuyPhong);
		
		/*------------Cập nhật phòng------------------------*/
		
		edtCodeRoom_sua = (EditText) dialogSuaPhong.findViewById(R.id.edtCodeRoom);
		edtRoomName_sua = (EditText) dialogSuaPhong.findViewById(R.id.edtRoomName);
		btnCapNhatPhong	= (Button)	 dialogSuaPhong.findViewById(R.id.btnCapNhatPhong);
		btnHuyCapNhatPhong = (Button) dialogSuaPhong.findViewById(R.id.btnHuyCapNhatPhong);
		
		/*---------------------------*/
		
		nhanvien = new ArrayList<nhanvien>();
		
		maPhong = new ArrayList<>();
		// mã phòng trong csdl
		maPhong = cosodulieu.listCodeRoom();
		maPhong.add("PGD");
		maPhong.add("PPGD");
		maPhong.add("PKT-TV");
		maPhong.add("PKH-KD");
		maPhong.add("PKT-TC");
		maPhong.add("PTC-HC");
		maPhongBan = new ArrayAdapter<>(QuanLyNhanVienActivity.this
				, android.R.layout.simple_spinner_item, maPhong);
		maPhongBan.notifyDataSetChanged();
		maPhongBan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMaPhongBan.setAdapter(maPhongBan);
	
		/*---------Vị trí: Xác định vị trí ngay lúc start------------*/
		
		positionClickItem();
	}
	public Dialog openDialogThemNhanVien(){
		dialogThem = new Dialog(QuanLyNhanVienActivity.this);
		dialogThem.setTitle("Thêm nhân viên");
		dialogThem.setContentView(R.layout.add_employee);
		return dialogThem;
	}
	public Dialog openDialogSuaNhanVien(){
		dialogSua = new Dialog(QuanLyNhanVienActivity.this);
		dialogSua.setTitle("Chỉnh sửa nhân viên");
		dialogSua.setContentView(R.layout.edit_employee);
		return dialogSua;
	}
	
	public Dialog openDialogThemPhong(){
		dialogThemPhong = new Dialog(QuanLyNhanVienActivity.this);
		dialogThemPhong.setTitle("Thêm phòng ban");
		dialogThemPhong.setContentView(R.layout.add_room);
		return dialogThemPhong;
	}
	
	public Dialog openDialogSuaPhong(){
		dialogSuaPhong = new Dialog(QuanLyNhanVienActivity.this);
		dialogSuaPhong.setTitle("Cập nhật phòng ban");
		dialogSuaPhong.setContentView(R.layout.edit_room);
		return dialogSuaPhong;
	}
	public void controlEventDialog(){
		/*------------------ Thêm nhân viên -------------*/
		btnHuyBo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogThem.dismiss();
			}
		});
		
		btnThem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				manhanvien = edtCodeEmployee.getText().toString();
				tennhanvien = edtNameEmployee.getText().toString();
				quequan = edtQueQuan.getText().toString();
				dienthoai = edtPhoneEmployee.getText().toString();
				ngaysinh = edtBirthdayEmployee.getText().toString();
				email = edtEmailEmployee.getText().toString();
				cmnd = edtCmnd.getText().toString();
				noicap = edtNoiCap.getText().toString();
				ngaycap = edtNgayCap.getText().toString();
				if (rdNam.isChecked())
					gioitinh = rdNam.getText().toString();
				else if (rdNu.isChecked())
					gioitinh = rdNu.getText().toString();
				if (rdYes.isChecked())
					giadinh = rdYes.getText().toString();
				else if (rdNo.isChecked())
					giadinh = rdNo.getText().toString();
				
				
				if (!manhanvien.matches("[0-9]*") && dienthoai.matches("[0-9]{1,12}") && cmnd.matches("[0-9]{1,12}") && email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")){
					nhanvien.add(new nhanvien(manhanvien, maphongban
							,tennhanvien, quequan, gioitinh, giadinh, dienthoai
								, ngaysinh, email, cmnd, noicap));
					adapterNhanVien.notifyDataSetChanged();
					listNhanVien.setAdapter(adapterNhanVien);
					
					boolean kq = cosodulieu.themnhanvien(manhanvien, maphongban
							, tennhanvien, quequan, gioitinh, giadinh, dienthoai
								, ngaysinh, email, cmnd, noicap);
					cosodulieu.chungminhnhandan(cmnd, quequan, noicap,ngaycap);
					if (kq)
						Toast.makeText(QuanLyNhanVienActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(QuanLyNhanVienActivity.this, "Thêm thất bại. Vui lòng nhập đầy đủ thông tin.\nKhông được nhập trùng email và mã nhân viên", Toast.LENGTH_SHORT).show();
					
					dialogThem.dismiss();
				}
				else
				{
					Toast.makeText(QuanLyNhanVienActivity.this, "Vui lòng nhập đúng định dạng:\nTên không được nhập số\nSố điện thoại chỉ được tối đa 11 số\nSố chứng minh nhân dân tối đa 12 số\n Nhập đúng định dạng email", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		/*---------------Cập nhật nhân viên---------------------*/
		btnHuyBoCapNhat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogSua.dismiss();
			}
		});
		
		btnCapNhat.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				manhanvien = edtCodeEmployee_sua.getText().toString();
				tennhanvien = edtNameEmployee_sua.getText().toString();
				quequan = edtQueQuan_sua.getText().toString();
				dienthoai = edtPhoneEmployee_sua.getText().toString();
				ngaysinh = edtBirthdayEmployee_sua.getText().toString();
				ngaycap = edtNgayCap_sua.getText().toString();
				email = edtEmailEmployee_sua.getText().toString();
				cmnd = edtCmnd_sua.getText().toString();
				noicap = edtNoiCap_sua.getText().toString();
				
				if (rdNam_sua.isChecked())
					gioitinh = rdNam_sua.getText().toString();
				else if(rdNu_sua.isChecked())
					gioitinh = rdNu_sua.getText().toString();
				
				if (rdYes_sua.isChecked())
					giadinh = rdYes_sua.getText().toString();
				else if (rdNo_sua.isChecked())
					giadinh = rdNo_sua.getText().toString();
				
				if (!manhanvien.matches("[0-9]*") && dienthoai.matches("[0-9]{1,12}") 
						&& cmnd.matches("[0-9]{1,12}") 
							&& email.matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}$")){
					nhanvien.remove(viTriNhanVien);
					nhanvien.add(viTriNhanVien
							,new nhanvien(manhanvien, maphongban, tennhanvien, quequan
									, gioitinh, giadinh, dienthoai, ngaysinh, email, cmnd, noicap));
					adapterNhanVien.notifyDataSetChanged();
					
					boolean kq = cosodulieu.capnhatnhanvien(manhanvien, maphongban
							, tennhanvien, quequan, gioitinh, giadinh, dienthoai
								, ngaysinh, email, cmnd, noicap);
					boolean kqcapnhatcmnd = cosodulieu.capnhatchungminhnhandan(cmnd, quequan, noicap, ngaycap);
					if (kq)
						Toast.makeText(QuanLyNhanVienActivity.this
								, "Cap nhat thanh cong"
									, Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(QuanLyNhanVienActivity.this
								, "Cap nhat that bai"
									, Toast.LENGTH_SHORT).show();
					
					listNhanVien.setAdapter(adapterNhanVien);
					dialogSua.dismiss();

				}
				else
				{
					Toast.makeText(QuanLyNhanVienActivity.this, "Vui lòng nhập đúng định dạng:\nTên không được nhập số\nSố điện thoại chỉ được tối đa 11 số\nSố chứng minh nhân dân tối đa 12 số\n Nhập đúng định dạng email", Toast.LENGTH_LONG).show();
				}
				
			}
		});
		/*---------Thêm phòng----------*/
		btnHuyPhong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogThemPhong.dismiss();
			}
		});
		btnThemPhong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				phongban.add(new phongban(edtCodeRoom.getText().toString()
						, edtRoomName.getText().toString()));
				adapterPhongBan.notifyDataSetChanged();
				listPhongBan.setAdapter(adapterPhongBan);
			
				boolean kq = cosodulieu.themPhongBan(edtCodeRoom.getText().toString()
						, edtRoomName.getText().toString());
				if (kq)
					Toast.makeText(QuanLyNhanVienActivity.this
							, "Thêm thành công"
								, Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(QuanLyNhanVienActivity.this
							, "Thêm thất bại. Vui lòng nhập đầy đủ. Mã phòng ban chỉ được tối đa 20 kí tự"
								, Toast.LENGTH_SHORT).show();

				dialogThemPhong.dismiss();
			}
		});
		/*-------------Cập nhật phòng-------------------*/
		btnHuyCapNhatPhong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialogSuaPhong.dismiss();
			}
		});
		
		btnCapNhatPhong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				maphong  = edtCodeRoom_sua.getText().toString();
				tenphong = edtRoomName_sua.getText().toString();
				
				phongban.remove(viTriPhongBan);
				phongban.add(viTriPhongBan, new phongban(maphong, tenphong));
				adapterPhongBan.notifyDataSetChanged();
				listPhongBan.setAdapter(adapterPhongBan);
				
				boolean kq = cosodulieu.capNhatPhongBan(maphong, tenphong);
				if (kq)
					Toast.makeText(QuanLyNhanVienActivity.this
							, "Cập nhật thành công"
								, Toast.LENGTH_SHORT).show();
				else
					Toast.makeText(QuanLyNhanVienActivity.this
							, "Cập nhật thất bại"
								, Toast.LENGTH_SHORT).show();
				
				dialogSuaPhong.dismiss();
			}
		});
		
	}
	
	public void getInfoUpdateEmployee(){

		nhanvien nv = nhanvien.get(viTriNhanVien);
		edtCodeEmployee_sua.setText(nv.getManhanvien());
		edtNameEmployee_sua.setText(nv.getTennhanvien());
		edtQueQuan_sua.setText(nv.getQuequan());
		edtPhoneEmployee_sua.setText(nv.getDienthoai());
		edtBirthdayEmployee_sua.setText(nv.getNgaysinh());
		edtEmailEmployee_sua.setText(nv.getEmail());
		edtCmnd_sua.setText(nv.getCmnd());
		edtNoiCap_sua.setText(nv.getNoicap());
		
		if (rdNam_sua.isChecked())
			rdNam_sua.setChecked(true);
		else if (rdNu_sua.isChecked())
			rdNu_sua.setChecked(true);
		
		if (rdYes_sua.isChecked())
			rdYes_sua.setChecked(true);
		else if (rdNo_sua.isChecked())
			rdNo_sua.setChecked(true);
		
		maPhong_sua = new ArrayList<String>();
		maPhong_sua.add(nv.getMaphongban());
		maPhongBan_sua = new ArrayAdapter<>(QuanLyNhanVienActivity.this
				, android.R.layout.simple_spinner_item
					, maPhong_sua);
		maPhongBan_sua.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerMaPhongBan_sua.setAdapter(maPhongBan_sua);
	}
	
	public void getInfoUpdateRoom(){
		phongban phong = phongban.get(viTriPhongBan);
		edtCodeRoom_sua.setText(phong.getMaPhong());
		edtRoomName_sua.setText(phong.getTenPhong());
	}
	public void createTab(String tag, String tabHostName, int resouce){
		View v = LayoutInflater.from(QuanLyNhanVienActivity.this).inflate(R.layout.custom_tabhost, null);
		TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
		txtTitle.setText(tabHostName);
		
		tabHost = (TabHost) findViewById(R.id.myTabhost);
		tabHost.setup();
		TabHost.TabSpec tab = tabHost.newTabSpec(tag);
		tab.setIndicator(v);
		tab.setContent(resouce);
		tabHost.addTab(tab);
	}

	public void positionClickItem(){
		// Cập nhật vị trí
		listNhanVien.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				viTriNhanVien = position;
			}
		});
		
		listPhongBan.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				viTriPhongBan = position;
			}
		});
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.btnAddPhongBan:
				dialogThemPhongBan.show();
				controlEventDialog();
				break;
			case R.id.btnAddNhanVien:
				dialogThemNhanVien.show();
				spinnerMaPhongBan.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
						// lay ma phong  ban
						maphongban = maPhong.get(position);
					}
					@Override
					public void onNothingSelected(AdapterView<?> parent) {
					}
				});
				controlEventDialog();
				break;
			case R.id.btnDeleteNhanVien:
				if (viTriNhanVien > -1 && nhanvien.size() > 0){
					nhanvien nv = nhanvien.get(viTriNhanVien);
					nhanvien.remove(viTriNhanVien);
					boolean kqXoa = cosodulieu.xoanhanvien(nv.getManhanvien());
					boolean kqXoachungminhnhandan = cosodulieu.xoachungminhnhandan(nv.getCmnd());
					if (kqXoa)
						Toast.makeText(QuanLyNhanVienActivity.this
								, "Xoa thanh cong"
									, Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(QuanLyNhanVienActivity.this
								, "Xoa that bai"
									, Toast.LENGTH_SHORT).show();
					adapterNhanVien.notifyDataSetChanged();
				}
				break;
			case R.id.btnDeletePhongBan:
				if (viTriPhongBan > -1 && phongban.size() > 0){
					boolean kqXoa = cosodulieu.xoaPhongBan(phongban.get(viTriPhongBan).getMaPhong());
					
					phongban.remove(viTriPhongBan);
					adapterPhongBan.notifyDataSetChanged();
					
					if (kqXoa)
						Toast.makeText(QuanLyNhanVienActivity.this
								, "Xoa thanh cong"
									, Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(QuanLyNhanVienActivity.this
								, "Xoa that bai"
									, Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.btnUpdateNhanVien: 
					if (viTriNhanVien > -1 && nhanvien.size() > 0){
						dialogSuaNhanVien.show();
						getInfoUpdateEmployee();
						controlEventDialog();
					}
				break;
			case R.id.btnUpdatePhongBan:
				if (viTriPhongBan > -1 && phongban.size() > 0){
					dialogSuaPhongBan.show();
					getInfoUpdateRoom();
					controlEventDialog();
				}
				break;
			case R.id.btnExitNhanVien:
				finish();
				break;
			case R.id.btnExitPhongBan:
				finish();
				break;
		}
	}
	
	@Override
	public void finish() {
		super.finish();
		overridePendingTransition(R.anim.scale2, R.anim.scale1);
	}
}
