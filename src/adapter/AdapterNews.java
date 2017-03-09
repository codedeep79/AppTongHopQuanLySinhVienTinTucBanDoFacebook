package adapter;

import java.util.ArrayList;

import com.example.entity.news;
import com.example.quanlynhansu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import async.Async_Image;

public class AdapterNews extends ArrayAdapter<news>{
	Context context;
	int resource;
	ArrayList<news> items;
	public AdapterNews(Context context, int resource, ArrayList<news> items) {
		super(context, resource, items);
		this.context  = context;
		this.resource = resource;
		this.items 	  = items;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null)
		{
			LayoutInflater layoutInflater 
				= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v   = layoutInflater.inflate(resource, parent, false);
		}
		news banTin = items.get(position);
		if (banTin != null)
		{
			TextView txtTitle 		= (TextView) v.findViewById(R.id.txtTitle);
			txtTitle.setText(banTin.getTitle());
			TextView txtPubDate 	= (TextView) v.findViewById(R.id.txtPubDate);
			txtPubDate.setText(banTin.getPubDate());
			TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);
			txtDescription.setText(banTin.getDescription());
			
			ImageView img = (ImageView) v.findViewById(R.id.img);
			new Async_Image(img).execute(banTin.getLinkImage());
		}
		return v;
	}

}
