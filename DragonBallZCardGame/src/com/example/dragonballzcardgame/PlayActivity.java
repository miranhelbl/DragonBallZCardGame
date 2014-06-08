package com.example.dragonballzcardgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dragonballzcardgame.R.drawable;
import com.example.dragonballzcardgame.data.Karte;
import com.example.dragonballzcardgame.data.KarteList;
import com.example.dragonballzcardgame.util.DataMock;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

public class PlayActivity extends ActionBarActivity {
	ImageView image1;
	ImageView image2;
	Button btn1Ki;
	Button btn2Ki;
	Button btn1Force;
	Button btn2Force;
	Button btn1Intelligence;
	Button btn2Intelligence;
	Button btn1Ability;
	Button btn2Ability;
	TextView tw1Name;
	TextView tw2Name;
	int currentIndex;
	ArrayList<Karte> list;
	Button btnNewGame;
	boolean computersTurn = false;
	int playerWins;
	int computerWins;
	int rounds;
	TextView twPlayerWins;
	TextView twComputerWins;
	TextView twRounds;
	EditText etUsername;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_play);
		
		image1 = (ImageView) findViewById(R.id.imageView1);
		tw1Name = (TextView) findViewById(R.id.textView1);
		btn1Ki = (Button) findViewById(R.id.button1);
		btn1Force = (Button) findViewById(R.id.button2);
		btn1Intelligence = (Button) findViewById(R.id.button3);
		btn1Ability = (Button) findViewById(R.id.button4);
		image2 = (ImageView) findViewById(R.id.imageView2);
		tw2Name = (TextView) findViewById(R.id.textView2);
		btn2Ki = (Button) findViewById(R.id.button5);
		btn2Force = (Button) findViewById(R.id.button6);
		btn2Intelligence = (Button) findViewById(R.id.button7);
		btn2Ability = (Button) findViewById(R.id.button8);
		list = getKarte().getList();
		btnNewGame = (Button) findViewById(R.id.button9);
		twPlayerWins = (TextView) findViewById(R.id.textView3);
		twComputerWins = (TextView) findViewById(R.id.textView4);
		twRounds = (TextView) findViewById(R.id.textView5);
		etUsername = (EditText) findViewById(R.id.editText1);
		
		
		resetFields();
		
		btnNewGame.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String sUsername = etUsername.getText().toString();
			    if(TextUtils.isEmpty(sUsername)){
			    	showNoUsernameAlertDialog();
			    }
			    else{
			    	newGame();
			    }
			}
		});
        
        btn1Ki.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = getRand(0, 59);
				loadKarte2(index);
				primerjaj(1, index);
				obarvaj(1);
			}
		});
        
        btn1Force.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = getRand(0, 59);
				loadKarte2(index);
				primerjaj(2, index);
				obarvaj(2);
			}
		});
        
        btn1Intelligence.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = getRand(0, 59);
				loadKarte2(index);
				primerjaj(3, index);
				obarvaj(3);
			}
		});
        
        btn1Ability.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index = getRand(0, 59);
				loadKarte2(index);
				primerjaj(4, index);	
				obarvaj(4);
			}
		});
        
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	
    private class HttpGetter extends AsyncTask<URI, Void, Void> {

		@Override
		protected Void doInBackground(URI... urls) {
			// TODO Auto-generated method stub
			StringBuilder builder = new StringBuilder();
			HttpClient client = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(urls[0]);
			
			try {
				HttpResponse response = client.execute(httpGet);
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode();
				if (statusCode == 200) {
					HttpEntity entity = response.getEntity();
					InputStream content = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(content));
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					Log.v("Getter", "Your data: " + builder.toString()); //response data
				} else {
					Log.e("Getter", "Failed to download file");
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}
    }
	
	@SuppressWarnings("deprecation")
	private void showNoUsernameAlertDialog()
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("No username");
		alertDialog.setMessage("You need to enter your username!");
		alertDialog.setButton("Continue", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			//do nothing.
		}
		});
		alertDialog.show();
	}
	
	//some random function to test github
	private void randomcode()
	{
		System.out.println("random code");
	}
	
	private void newGame()
	{
		randomcode();
		btn1Ki.setVisibility(Button.VISIBLE);
		btn1Force.setVisibility(Button.VISIBLE);
		btn1Intelligence.setVisibility(Button.VISIBLE);
		btn1Ability.setVisibility(Button.VISIBLE);
		btn2Ki.setVisibility(Button.VISIBLE);
		btn2Force.setVisibility(Button.VISIBLE);
		btn2Intelligence.setVisibility(Button.VISIBLE);
		btn2Ability.setVisibility(Button.VISIBLE);
		playerWins = 0;
		computerWins = 0;
		rounds = 0;
		updateWins();
		humanPlay();
	}
	
	@SuppressWarnings("deprecation")
	private void checkIfGameOver()
	{
		if(computerWins == 30)
		{
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Game over");
			alertDialog.setMessage("You got beaten by computer!");
			alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					writeToDb();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			}
			});
			alertDialog.show();
		}
		
		if(playerWins == 30)
		{
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Game over");
			alertDialog.setMessage("You won the game!");
			alertDialog.setButton("Exit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					writeToDb();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				finish();
			}
			});
			alertDialog.show();
		}
			
	}
	
	private void writeToDb() throws URISyntaxException
	{
		String link = "http://ferisrv5.uni-mb.si/~mh9091/insert.php?Nickname=" + etUsername.getText().toString() + "&Rounds=" + rounds + "&PlayerWins=" + playerWins + "&ComputerWins=" + computerWins;
		URI url = new URI(link);
		
		HttpGetter get = new HttpGetter();
		get.execute(url);
	}
	
	private void updateWins()
	{
		twPlayerWins.setText("Player: " + playerWins);
		twComputerWins.setText("Computer: " + computerWins);
		twRounds.setText("Rounds: " + rounds);
	}
	
	private void obarvaj(int izbira)
	{
		switch(izbira)
		{
		case 1:
			btn1Ki.setBackgroundColor(Color.BLACK);
			btn1Ki.setTextColor(Color.WHITE);
			btn2Ki.setBackgroundColor(Color.BLACK);
			btn2Ki.setTextColor(Color.WHITE);
			break;
		case 2:
			btn1Force.setBackgroundColor(Color.BLACK);
			btn1Force.setTextColor(Color.WHITE);
			btn2Force.setBackgroundColor(Color.BLACK);
			btn2Force.setTextColor(Color.WHITE);
			break;
		case 3:
			btn1Intelligence.setBackgroundColor(Color.BLACK);
			btn1Intelligence.setTextColor(Color.WHITE);
			btn2Intelligence.setBackgroundColor(Color.BLACK);
			btn2Intelligence.setTextColor(Color.WHITE);
			break;
		case 4:
			btn1Ability.setBackgroundColor(Color.BLACK);
			btn1Ability.setTextColor(Color.WHITE);
			btn2Ability.setBackgroundColor(Color.BLACK);
			btn2Ability.setTextColor(Color.WHITE);
			break;
		}
	}
	
	private void obarvajReset()
	{
		Drawable background = btnNewGame.getBackground();
		
		btn1Ki.setBackground(background);
		btn1Force.setBackground(background);
		btn1Intelligence.setBackground(background);
		btn1Ability.setBackground(background);
		btn2Ki.setBackground(background);
		btn2Force.setBackground(background);
		btn2Intelligence.setBackground(background);
		btn2Ability.setBackground(background);
	}
	
	@SuppressWarnings("deprecation")
	private void youWon()
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Reset...");
		alertDialog.setMessage("You won this round!");
		alertDialog.setButton("Continue", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			computersTurn = false;
			/*btn1Ability.setClickable(true);
			btn1Ki.setClickable(true);
			btn1Intelligence.setClickable(true);
			btn1Force.setClickable(true);*/
			updateWins();
			humanPlay();
		}
		});
		//alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();
	}
	
	@SuppressWarnings("deprecation")
	private void itsAtie()
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Reset...");
		alertDialog.setMessage("Its a tie!");
		alertDialog.setButton("Continue", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			updateWins();
			
			if(computersTurn)
				computerPlay();
			else
				humanPlay();
		}
		});
		//alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();
	}
	
	@SuppressWarnings("deprecation")
	private void youLost()
	{
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Reset...");
		alertDialog.setMessage("You lost this round!");
		alertDialog.setButton("Continue", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			computersTurn = true;
			/*btn1Ability.setClickable(false);
			btn1Ki.setClickable(false);
			btn1Intelligence.setClickable(false);
			btn1Force.setClickable(false);*/
			updateWins();
			computerPlay();
		}
		});
		//alertDialog.setIcon(R.drawable.icon);
		alertDialog.show();
	}
	
	private void humanPlay()
	{
		obarvajReset();
		resetFields();
		image2.setImageResource(drawable.question);
		currentIndex = getRand(0, 59);
		
		loadKarte(currentIndex);
	}
	
	private void computerPlay()
	{
		obarvajReset();
		currentIndex = getRand(0, 59);
		loadKarte(currentIndex);
		
		int index = getRand(0, 59);
		loadKarte2(index);
		
		int izbira = getRand(1, 4);
		obarvaj(izbira);
		primerjaj(izbira, index);
	}
	
	private void primerjaj(int izbira, int index)
	{		
		switch(izbira)
		{
		case 1:
			if(list.get(currentIndex).getKi() > list.get(index).getKi())
			{
				playerWins++;
				youWon();
			}
			
			if(list.get(currentIndex).getKi() < list.get(index).getKi())
			{
				computerWins++;
				youLost();
			}
			
			if(list.get(currentIndex).getKi() == list.get(index).getKi())
			{
				playerWins++;
				computerWins++;
				itsAtie();
			}
			break;
		case 2:
			if(list.get(currentIndex).getForce() > list.get(index).getForce())
			{
				playerWins++;
				youWon();
			}
			
			if(list.get(currentIndex).getForce() < list.get(index).getForce())
			{
				computerWins++;
				youLost();
			}
			
			if(list.get(currentIndex).getForce() == list.get(index).getForce())
			{
				playerWins++;
				computerWins++;
				itsAtie();
			}
			break;
		case 3:
			if(list.get(currentIndex).getIntelligence() > list.get(index).getIntelligence())
			{
				playerWins++;
				youWon();
			}
			
			if(list.get(currentIndex).getIntelligence() < list.get(index).getIntelligence())
			{
				computerWins++;
				youLost();
			}
			
			if(list.get(currentIndex).getIntelligence() == list.get(index).getIntelligence())
			{
				playerWins++;
				computerWins++;
				itsAtie();
			}
			break;
		case 4:
			if(list.get(currentIndex).getAbility() > list.get(index).getAbility())
			{
				playerWins++;
				youWon();
			}
			
			if(list.get(currentIndex).getAbility() < list.get(index).getAbility())
			{
				computerWins++;
				youLost();
			}
			
			if(list.get(currentIndex).getAbility() == list.get(index).getAbility())
			{
				playerWins++;
				computerWins++;
				itsAtie();
			}
			break;
		}
		rounds++;
		checkIfGameOver();
	}
	
	public void resetFields()
	{
		tw1Name.setText("");
		tw2Name.setText("");
		btn1Ki.setText("");
		btn2Ki.setText("");
		btn1Force.setText("");
		btn2Force.setText("");
		btn1Intelligence.setText("");
		btn2Intelligence.setText("");
		btn1Ability.setText("");
		btn2Ability.setText("");
		image1.setImageResource(0);
		image2.setImageResource(0);
		
	}
	
	public int getRand(int min, int max)
	{
		Random r = new Random();
		return r.nextInt(max - min + 1) + min;
	}
	
	public void loadKarte(int index)
	{	
		Resources res = getResources();
		String mDrawableName = list.get(index).getFileName();
		int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
		Drawable drawable = res.getDrawable(resID);
		image1.setImageDrawable(drawable);
		//drawable = null;	
		/*try {
			
		} catch (Exception e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		    System.out.println("Napaka: mdrawablename: " + mDrawableName);
		}*/
		
		tw1Name.setText(list.get(index).getName());
		btn1Ki.setText("KI: " + list.get(index).getKi());
		btn1Force.setText("Force: " + list.get(index).getForce());
		btn1Intelligence.setText("Intelligence: " + list.get(index).getIntelligence());
		btn1Ability.setText("Ability: " + list.get(index).getAbility());
		
	}
	
	public void loadKarte2(int index)
	{	
		Resources res = getResources();
		String mDrawableName = list.get(index).getFileName();
		int resID = res.getIdentifier(mDrawableName , "drawable", getPackageName());
		Drawable drawable = res.getDrawable(resID);
		image2.setImageDrawable(drawable);
		//drawable = null;
		
		/*try {
			
		} catch (Exception e) {
		    System.err.println("Caught IOException: " + e.getMessage());
		    System.out.println("Napaka: mdrawablename: " + mDrawableName);
		}*/
			
		
		tw2Name.setText(list.get(index).getName());
		btn2Ki.setText("KI: " + list.get(index).getKi());
		btn2Force.setText("Force: " + list.get(index).getForce());
		btn2Intelligence.setText("Intelligence: " + list.get(index).getIntelligence());
		btn2Ability.setText("Ability: " + list.get(index).getAbility());
		
	}
	
	public KarteList getKarte() {
		KarteList karte;
		karte = DataMock.getMockKarte();
		return karte;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.play, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_play, container,
					false);
			return rootView;
		}
	}
}
