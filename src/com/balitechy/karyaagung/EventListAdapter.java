package com.balitechy.karyaagung;

import java.util.List;

import com.balitechy.karyaagung.R.color;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventListAdapter extends BaseAdapter{
	
	private List<Event> eventList;
	private Context context;

	public EventListAdapter(List<Event> eventList, Context context) {
		this.eventList = eventList;
		this.context = context;
	}

	@Override
	public int getCount() {
		return eventList.size();
	}

	@Override
	public Object getItem(int index) {
		return eventList.get(index);
	}

	@Override
	public long getItemId(int index) {
		return index;
	}

	@Override
	public View getView(int index, View convertView, ViewGroup parent) {
		if(convertView == null){
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.event_list_item, parent, false);
		}
		
		TextView tanggalText = (TextView) convertView.findViewById(R.id.tanggal);
		tanggalText.setText(eventList.get(index).getTanggal());
		
		TextView rahinaText = (TextView) convertView.findViewById(R.id.rahina);
		rahinaText.setText(eventList.get(index).getRahina());
		
		TextView galahText = (TextView) convertView.findViewById(R.id.galah);
		galahText.setText(eventList.get(index).getEarliestGalah());
		
		TextView tagsText = (TextView) convertView.findViewById(R.id.tags);
		tagsText.setText(eventList.get(index).getTagsText());
		
		if(eventList.get(index).isToday()){
			convertView.setBackgroundResource(R.drawable.bg_list_item_today);
		}else if(eventList.get(index).isPast()){
			convertView.setBackgroundResource(R.drawable.bg_list_item_expired);
		}else{
			convertView.setBackgroundResource(R.drawable.bg_list_item);
		}
		
		return convertView;
	}

}
