package com.twistedequations.loaders;

import java.util.ArrayList;
import java.util.Random;

import android.content.AsyncTaskLoader;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;


public class RandomStringLoader extends AsyncTaskLoader<ArrayList<String>> {

	Receiver receiver;
	public static final String StringLoader_RELOAD ="RandomStringLoader.RELOAD";
	ArrayList<String> oldData = new ArrayList<String>();
	
	public RandomStringLoader(Context context) {
		super(context);
	}
	
	@Override
	protected void onStartLoading() {
		receiver = new Receiver(this);
		IntentFilter filter = new IntentFilter();
		filter.addAction(StringLoader_RELOAD);
		getContext().registerReceiver(receiver, filter);
		if(!oldData.isEmpty()){
			super.deliverResult(oldData);
		}
		
		forceLoad();
		super.onStartLoading();
	}

	@Override
	public ArrayList<String> loadInBackground() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> array = new ArrayList<String>();
		char[] chars = "ty438oyt458yt89o4c2yg8495ynt845yt934ty784ty4389ntg439ru438y5".toCharArray();
		Random random = new Random();
		for(int i =0; i<20; i++){
			
			String randomString = "";
			for(int o =0; o<20; o++){
				randomString = randomString+chars[random.nextInt(chars.length)];
			}
			randomString+="\n";
			array.add(randomString);
		}
		
		return array;
	}
	
	@Override
	public void deliverResult(ArrayList<String> data) {
		
		oldData = data;
		
		if(isStarted()){
			super.deliverResult(data);
		}
		
	}
	
	@Override
	protected void onReset() {
		getContext().unregisterReceiver(receiver);
		this.stopLoading();
		super.onReset();
	}
	
	public class Receiver extends BroadcastReceiver{

		RandomStringLoader loader;
		
		public Receiver(RandomStringLoader loader) {
			this.loader = loader;
		}
		
		@Override
		public void onReceive(Context context, Intent intent) {
			loader.onContentChanged();
		}
		
	};

}
