package QuanlyNhanSu;

import java.io.Serializable;

import android.graphics.Bitmap;

public class nhanvien implements Serializable{
	private String manhanvien, maphongban
		, tennhanvien, quequan, gioitinh, giadinh
		, dienthoai, ngaysinh, email, cmnd, noicap;
	Bitmap hinhanh;
	public nhanvien(String manhanvien, String maphongban, String tennhanvien, String quequan,
			String gioitinh, String giadinh, String dienthoai, String ngaysinh, String email, String cmnd,
			String noicap,Bitmap... hinhanh) {
		this.manhanvien = manhanvien;
		this.maphongban = maphongban;
		this.tennhanvien = tennhanvien;
		this.quequan = quequan;
		this.gioitinh = gioitinh;
		this.giadinh = giadinh;
		this.dienthoai = dienthoai;
		this.ngaysinh = ngaysinh;
		this.email = email;
		this.cmnd = cmnd;
		this.noicap = noicap;
		this.hinhanh = this.hinhanh;
	}

	public Bitmap getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(Bitmap hinhanh) {
		this.hinhanh = hinhanh;
	}

	public String getManhanvien() {
		return manhanvien;
	}

	public void setManhanvien(String manhanvien) {
		this.manhanvien = manhanvien;
	}

	public String getMaphongban() {
		return maphongban;
	}

	public void setMaphongban(String maphongban) {
		this.maphongban = maphongban;
	}

	public String getTennhanvien() {
		return tennhanvien;
	}

	public void setTennhanvien(String tennhanvien) {
		this.tennhanvien = tennhanvien;
	}

	public String getQuequan() {
		return quequan;
	}

	public void setQuequan(String quequan) {
		this.quequan = quequan;
	}

	public String getGioitinh() {
		return gioitinh;
	}

	public void setGioitinh(String gioitinh) {
		this.gioitinh = gioitinh;
	}

	public String getGiadinh() {
		return giadinh;
	}

	public void setGiadinh(String giadinh) {
		this.giadinh = giadinh;
	}

	public String getDienthoai() {
		return dienthoai;
	}

	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getNoicap() {
		return noicap;
	}

	public void setNoicap(String noicap) {
		this.noicap = noicap;
	}
	
}
