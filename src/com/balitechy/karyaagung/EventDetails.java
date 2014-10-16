package com.balitechy.karyaagung;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import de.greenrobot.event.EventBus;


public class EventDetails extends FragmentActivity {
	
	private TextView rahinaText, pakelingText;
	private ListView subeventListView;
	private SubEventListAdapter subeventlistAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		//Display event details information
		Event event = (Event) EventBus.getDefault().getStickyEvent(Event.class);
		setTitle(event.getTanggal());
		
		rahinaText = (TextView) findViewById(R.id.detail_rahina);
		rahinaText.setText(event.getRahina());
		
		pakelingText = (TextView) findViewById(R.id.detail_pakeling);
		pakelingText.setText(event.getPakeling());
		if(event.getPakeling() == "null"){
			pakelingText.setVisibility(View.GONE);
		}
		
		subeventListView = (ListView) findViewById(R.id.detail_subevents_list);
		
		subeventlistAdapter = new SubEventListAdapter(event.getSubEvents(), this);
		subeventListView.setAdapter(subeventlistAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.event_details, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    	case R.id.action_settings:
		    	DialogFragment about = new About();
		    	about.show(getSupportFragmentManager(), "about");
		    	break;
		    	
		    // Respond to the action bar's Up/Home button
		    case android.R.id.home:
		    	finish();
	    }
	    return super.onOptionsItemSelected(item);
	}
}
