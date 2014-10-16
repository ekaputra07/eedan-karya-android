package com.balitechy.karyaagung;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Event{
	private String tanggal;
	private String rahina;
	private String pakeling;
	private boolean isPast = false;
	private boolean isToday = false;
	private List<SubEvent> subevents = new ArrayList<SubEvent>();
	private JSONObject eventJson;
	
	public Event(JSONObject eventJson) {
		this.eventJson = eventJson;
		parseSubEvents(eventJson);
	}
	
	public String getTanggal(){
		try {
			tanggal = eventJson.getString("tanggal");
			parseTanggal();
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return tanggal;
	}
	
	private void parseTanggal(){
		String newDateString = null;
		Date eventDate, currentDate;
		
		Map<String, String> months = new HashMap<String, String>();
		months.put("Agustus", "Aug");
		months.put("Oktober", "Oct");
		months.put("November", "Nov");
		months.put("Desember", "Dec");
		months.put("Januari", "Jan");
		
		for(Map.Entry<String, String> entry: months.entrySet()){
			if(tanggal.indexOf(entry.getKey()) > -1){
				newDateString = tanggal.replace(entry.getKey(), entry.getValue());
				break;
			}
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		try {
			eventDate = formatter.parse(newDateString);
			currentDate = new Date();
			long delta = eventDate.getTime() - currentDate.getTime();
			if(delta < 0){
				isPast = true;
			}
			
			if(delta/(1000*60*60*24) == 0){
				isToday = true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isPast(){
		return isPast;
	}
	
	public boolean isToday(){
		return isToday;
	}
	
	public String getRahina(){
		try {
			rahina = eventJson.getString("rahina");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return rahina;
	}
	
	public String getPakeling(){
		try {
			pakeling = eventJson.getString("pakeling");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return pakeling;
	}
	
	public String getEarliestGalah(){
		SubEvent evt = subevents.get(0);
		return evt.getGalah();
	}
	
	public String getTagsText(){
		StringBuilder builder = new StringBuilder();
		try {
			JSONArray tagsJA = eventJson.getJSONArray("tags");
			for(int i=0; i<tagsJA.length(); i++){
				//dont append comma for the last tag.
				if(i == tagsJA.length()-1){
					builder.append(tagsJA.getString(i));
				}else{
					builder.append(tagsJA.getString(i) + ", ");
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return builder.toString();
	}
	
	public List<SubEvent> getSubEvents(){
		return subevents;
	}
	private void parseSubEvents(JSONObject eventJson){
		try {
			JSONArray subeventsJA = eventJson.getJSONArray("subevents");
			for(int i=0; i < subeventsJA.length(); i++){
				subevents.add(new SubEvent(subeventsJA.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}