package com.balitechy.karyaagung;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Event{
	private String tanggal;
	private String rahina;
	private String pakeling;
	private List<SubEvent> subevents = new ArrayList<SubEvent>();
	private JSONObject eventJson;
	
	public Event(JSONObject eventJson) {
		this.eventJson = eventJson;
		parseSubEvents(eventJson);
	}
	
	public String getTanggal(){
		try {
			tanggal = eventJson.getString("tanggal");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return tanggal;
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
	
	private List<SubEvent> parseSubEvents(JSONObject eventJson){
		try {
			JSONArray subeventsJA = eventJson.getJSONArray("subevents");
			for(int i=0; i < subeventsJA.length(); i++){
				subevents.add(new SubEvent(subeventsJA.getJSONObject(i)));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return subevents;
	}
}