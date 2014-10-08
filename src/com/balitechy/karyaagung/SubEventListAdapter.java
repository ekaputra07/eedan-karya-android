package com.balitechy.karyaagung;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubEventListAdapter extends BaseAdapter{
	
	private List<SubEvent> subeventList;
	private Context context;

	public SubEventListAdapter(List<SubEvent> subeventList, Context context) {
		this.subeventList = subeventList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return subeventList.size();
	}

	@Override
	public Object getItem(int index) {
		return subeventList.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.subevent_list_item, parent, false);
		}
		
		TextView galahText = (TextView) convertView.findViewById(R.id.detail_galah);
		galahText.setText(subeventList.get(index).getGalah());
		
		TextView genah = (TextView) convertView.findViewById(R.id.detail_genah);
		genah.setText(subeventList.get(index).getGenahFormatted());
		
		TextView pemargi = (TextView) convertView.findViewById(R.id.detail_pemargi);
		pemargi.setText(subeventList.get(index).getPemargiFormatted());
		
		TextView saneMuputLabel = (TextView) convertView.findViewById(R.id.detail_label_pamuput);
		TextView saneMuput = (TextView) convertView.findViewById(R.id.detail_pemuput);
		saneMuput.setText(subeventList.get(index).getSaneMuputFormatted());
		
		if(subeventList.get(index).getSaneMuput().size() == 0){
			saneMuputLabel.setVisibility(View.GONE);
			saneMuput.setVisibility(View.GONE);
		}
		
		TextView wewalianLabel = (TextView) convertView.findViewById(R.id.detail_label_wewalian);
		TextView wewalian = (TextView) convertView.findViewById(R.id.detail_wewalian);
		wewalian.setText(subeventList.get(index).getWewalianFormatted());
		
		if(subeventList.get(index).getWewalian().size() == 0){
			wewalianLabel.setVisibility(View.GONE);
			wewalian.setVisibility(View.GONE);
		}
		
		return convertView;
	}

}
