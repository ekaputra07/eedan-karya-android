package com.balitechy.karyaagung;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SubEvent {
	private JSONObject subeventJson;
	private String galah;
	private List<String> pemargi = new ArrayList<String>();
	private List<String> saneMuput = new ArrayList<String>();
	private List<String> genah = new ArrayList<String>();
	private List<String> wewalian = new ArrayList<String>();
	
	public SubEvent(JSONObject subeventJson) {
		this.subeventJson = subeventJson;
		parseSubEvent();
	}
	
	public String getGalah(){
		return galah;
	}
	
	public List<String> getPemargi(){
		return pemargi;
	}
	
	public List<String> getSaneMuput(){
		return saneMuput;
	}
	
	public List<String> getGenah(){
		return genah;
	}
	
	public List<String> getWewalian(){
		return wewalian;
	}
	
	public void parseSubEvent(){
		try {
			// galah
			galah = subeventJson.getString("galah");

			// pemargi
			JSONArray pemargiJA = subeventJson.getJSONArray("pemargi");
			for(int i=0; i < pemargiJA.length(); i++){
				pemargi.add(pemargiJA.getString(i));
			}
			
			// sane muput
			JSONArray saneMuputJA = subeventJson.getJSONArray("sane_muput");
			for(int i=0; i < saneMuputJA.length(); i++){
				saneMuput.add(saneMuputJA.getString(i));
			}
			
			// genah
			JSONArray genahJA = subeventJson.getJSONArray("genah");
			for(int i=0; i < genahJA.length(); i++){
				genah.add(genahJA.getString(i));
			}
			
			// wewalian
			JSONArray wewalianJA = subeventJson.getJSONArray("genah");
			for(int i=0; i < wewalianJA.length(); i++){
				wewalian.add(wewalianJA.getString(i));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
