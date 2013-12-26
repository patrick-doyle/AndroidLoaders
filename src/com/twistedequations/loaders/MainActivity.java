package com.twistedequations.loaders;

import java.util.ArrayList;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements LoaderCallbacks<ArrayList<String>> {

	TextView textView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textview);
		this.getLoaderManager().initLoader(5, null, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void reloadStrings(View v){
		Intent intent = new Intent();
		intent.setAction(RandomStringLoader.StringLoader_RELOAD);
		sendBroadcast(intent);
	}

	
	@Override
	public Loader<ArrayList<String>> onCreateLoader(int id, Bundle bundle) {
		RandomStringLoader loader = new RandomStringLoader(this);
		return loader;
	}

	@Override
	public void onLoadFinished(Loader<ArrayList<String>> loader,ArrayList<String> data) {
		textView.setText("");
		for(String text: data){
			textView.setText(textView.getText()+text);
		}
		
	}

	@Override
	public void onLoaderReset(Loader<ArrayList<String>> loader) {
		
		
	}

}
