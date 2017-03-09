package QuanlyNhanSu;

import java.util.ArrayList;
import java.util.List;

import com.example.quanlynhansu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class adapterEmployee extends ArrayAdapter<nhanvien>{
	Context context;
	int resource;
	ArrayList<nhanvien> items;
	TextView txtMaNhanVien, txtmaPhongBan, txtTenNhanVien, txtQueQuan
	, txtGioiTinh, txtGiaDinh, txtDienThoai, txtNgaySinh, txtEmail
	, txtSoCmnd, txtNoiCap; 
	public adapterEmployee(Context context, int resource, ArrayList<nhanvien> items) {
		super(context, resource, items);
		this.context = context;
		this.resource = resource;
		this.items = items;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View v = convertView;
		if (v == null){
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = layoutInflater.inflate(resource, parent, false);
		}
		nhanvien nv = items.get(position);
		if (nv != null)
		{
			txtMaNhanVien  = (TextView) v.findViewById(R.id.txtMaNhanVien);
			txtMaNhanVien.setText("Mã nhân viên: " + nv.getManhanvien());
			txtmaPhongBan  = (TextView) v.findViewById(R.id.txtmaPhongBan);
			txtmaPhongBan.setText("Mã phòng ban: " + nv.getMaphongban());
			txtTenNhanVien = (TextView) v.findViewById(R.id.txtTenNhanVien);
			txtTenNhanVien.setText("Tên nhân viên: " + nv.getTennhanvien());
			txtQueQuan     = (TextView) v.findViewById(R.id.txtQueQuan);
			txtQueQuan.setText("Quê quán: " + nv.getQuequan());
			txtGioiTinh    = (TextView) v.findViewById(R.id.txtGioiTinh);
			txtGioiTinh.setText("Giới tính: " + nv.getGioitinh());
			txtGiaDinh 	   = (TextView) v.findViewById(R.id.txtGiaDinh);
			txtGiaDinh.setText("Tình trạng gia đình: " + nv.getGiadinh());
			txtDienThoai   = (TextView) v.findViewById(R.id.txtDienThoai);
			txtDienThoai.setText("Điện thoại: " + nv.getDienthoai());
			txtNgaySinh    = (TextView) v.findViewById(R.id.txtNgaySinh);
			txtNgaySinh.setText("Ngày sinh: " + nv.getNgaysinh());
			txtEmail       = (TextView) v.findViewById(R.id.txtEmail);
			txtEmail.setText("Email: " + nv.getEmail());
			txtSoCmnd      = (TextView) v.findViewById(R.id.txtSoCmnd);
			txtSoCmnd.setText("Chứng minh nhân dân: " + nv.getCmnd());
			txtNoiCap      = (TextView) v.findViewById(R.id.txtNoiCap);
			txtNoiCap.setText("Nơi cấp: " + nv.getNoicap());
		}
		return v;
	}

}
