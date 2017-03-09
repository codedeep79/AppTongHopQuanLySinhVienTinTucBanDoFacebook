package phongban;

import java.util.ArrayList;
import java.util.List;

import com.example.quanlynhansu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class adapterPhongBan extends ArrayAdapter<phongban>{
	Context context;
	ArrayList<phongban> phongban;
	static int resource;
	
	public adapterPhongBan(Context context, int resource, ArrayList<phongban> phongban) {
		super(context, resource, phongban);
		this.context  = context;
		this.phongban = phongban;
		this.resource = resource;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
		{
			LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = layoutInflater.inflate(resource, parent, false);
		}
		phongban phong = phongban.get(position);
		if (phong != null)
		{
			TextView txtMaPhong = (TextView) v.findViewById(R.id.txtMaPhong);
			txtMaPhong.setText("Mã phòng: " + phong.getMaPhong());
			TextView txtTenPhong = (TextView) v.findViewById(R.id.txtTenPhong);
			txtTenPhong.setText("Tên phòng: " + phong.getTenPhong());
			TextView txtSoLuongNhanVien = (TextView) v.findViewById(R.id.txtSoLuongNhanVien);
			txtSoLuongNhanVien.setText("Số lượng nhân viên: chưa rõ" );
		}
		return v;
	}

}
