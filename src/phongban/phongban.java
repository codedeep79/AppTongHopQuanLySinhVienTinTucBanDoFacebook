package phongban;

import java.io.Serializable;

public class phongban implements Serializable{
	String maPhong, tenPhong;
	int[] soLuong;
	
	
	
	public phongban(String maPhong, String tenPhong, int... soLuong) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.soLuong = soLuong;
	}
	public String getMaPhong() {
		return maPhong;
	}
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	public String getTenPhong() {
		return tenPhong;
	}
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	public int[] getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int[] soLuong) {
		this.soLuong = soLuong;
	}
	
}
