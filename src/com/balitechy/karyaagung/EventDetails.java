package com.balitechy.karyaagung;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import de.greenrobot.event.EventBus;


public class EventDetails extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Event event = (Event) EventBus.getDefault().removeStickyEvent(Event.class);
		setTitle(event.getTanggal());
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
