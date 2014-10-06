package com.balitechy.karyaagung;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import de.greenrobot.event.EventBus;

public class KaryaAgung extends Activity implements OnItemClickListener{
	
	private int dbVersion;
	private ListView listView;
	private String jsonEvents;
	private List<Event> events = new ArrayList<Event>();
	private EventListAdapter listAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_list);
		
		listView = (ListView) findViewById(R.id.list);
		listView.setOnItemClickListener(this);
		
		listAdapter = new EventListAdapter(events, this);
		listView.setAdapter(listAdapter);
		
		jsonEvents = this.loadData("events.json");
		this.parseJsonEvents(jsonEvents);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.karya_agung, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int index, long arg3) {
		Intent detailIntent = new Intent(this, EventDetails.class);
		EventBus.getDefault().postSticky(events.get(index));
		startActivity(detailIntent);
	}
	
	public String loadData(String file){
		String json = null;
		try {
			InputStream stream = getAssets().open(file);
			int size = stream.available();
			byte[] buffer = new byte[size];
			stream.read(buffer);
			stream.close();
			json = new String(buffer, "UTF-8");
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return json;
	}
	
	public void parseJsonEvents(String json){
		try {
			JSONObject db = new JSONObject(json);
			
			dbVersion = db.getInt("version");
			JSONArray eventsJson = db.getJSONArray("events");
			
			for(int i=0; i < eventsJson.length(); i++){
				JSONObject eventJson = eventsJson.getJSONObject(i);
				events.add(new Event(eventJson));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
