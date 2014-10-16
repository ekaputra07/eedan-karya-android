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
	
	public String getPemargiFormatted(){
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i < pemargi.size(); i++){
			if(i == pemargi.size()-1){
				builder.append("- " + pemargi.get(i));
			}else{
				builder.append("- " + pemargi.get(i) + "\n");
			}
		}
		return builder.toString();
	}
	
	public List<String> getSaneMuput(){
		return saneMuput;
	}
	
	public String getSaneMuputFormatted(){
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i < saneMuput.size(); i++){
			if(i == saneMuput.size()-1){
				builder.append("- " + saneMuput.get(i));
			}else{
				builder.append("- " + saneMuput.get(i) + "\n");
			}
		}
		return builder.toString();
	}
	
	public List<String> getGenah(){
		return genah;
	}
	
	public String getGenahFormatted(){
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i < genah.size(); i++){
			if(i == genah.size()-1){
				builder.append("- " + genah.get(i));
			}else{
				builder.append("- " + genah.get(i) + "\n");
			}
		}
		return builder.toString();
	}
	
	public List<String> getWewalian(){
		return wewalian;
	}
	
	public String getWewalianFormatted(){
		StringBuilder builder = new StringBuilder();
		
		for(int i=0; i < wewalian.size(); i++){
			if(i == wewalian.size()-1){
				builder.append("- " + wewalian.get(i));
			}else{
				builder.append("- " + wewalian.get(i) + "\n");
			}
		}
		return builder.toString();
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
			JSONArray wewalianJA = subeventJson.getJSONArray("wewalian");
			for(int i=0; i < wewalianJA.length(); i++){
				wewalian.add(wewalianJA.getString(i));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
