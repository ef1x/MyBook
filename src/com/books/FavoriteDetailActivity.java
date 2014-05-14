package com.books;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class FavoriteDetailActivity extends Activity {

	private TextView title;
	private TextView author;
	private TextView isbn;
	private TextView description;
	private ImageView image;

	private SQLiteDatabase mDatenbank;
	private DatenbankManager mHelper;

	private String readTitle;
	private String readAuthor;
	private String readISBN;
	private Integer readID;
	private String _id;
	private String readDescription;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_favorite_detail);
		mHelper = new DatenbankManager(this);

		Intent startFavoriteDetail = getIntent();
		readTitle = startFavoriteDetail.getExtras().getString("title");
		readAuthor = startFavoriteDetail.getExtras().getString("author");
		readISBN = startFavoriteDetail.getExtras().getString("isbn");
//		id wird ben�tigt um einen Datensatz mit delete zu l�schen		
		readID = startFavoriteDetail.getExtras().getInt("_id"); 
		readDescription = startFavoriteDetail.getExtras().getString("description");
		

		

//		Hier werden TextView mit Inhalten bef�llt
		title = (TextView) findViewById(R.id.FavoriteDetailActivity_Titel); 
		title.setText(readTitle);
		author = (TextView) findViewById(R.id.FavoriteDetailActivity_Autor);
		author.setText(readAuthor);
		isbn = (TextView) findViewById(R.id.FavoriteDetailActivity_ISBN);
		isbn.setText(readISBN);
		description = (TextView)findViewById(R.id.FavoriteDetailActivity_Inhalt);
		description.setText(readDescription);
		description.setMovementMethod(new ScrollingMovementMethod());

		

		// inhalt; inhalt muss per get aus der Books api geladen werden
		// bild; bild muss entweder aus SQL oder aus einem Dateiverzeichnnis
		// geladen werden

		ImageView delete = (ImageView) findViewById(R.id.FavoriteDetailActivity_Loeschen);
		delete.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { // Button um Favoriteneintrag aus der
											// Datenbank zu l�schen
				// TODO Auto-generated method stub
				// L�sche Daten aus Datenbank und schlie�e Activity
				_id = (Integer.toString(readID));
				String where = "_id = ?";
				String[] whereArgs = { _id };

				mDatenbank.delete("book", where, whereArgs);
				Toast.makeText(getApplicationContext(),
						"Eintrag aus Favoriten gel�scht", Toast.LENGTH_SHORT)
						.show();
				finish(); // hier wird die aktuelle Activity geschlossen

			}
		});

		Button buecherei = (Button) findViewById(R.id.FavoriteDetailActivity_Buecherei);
		buecherei.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) { // Butten, um auf die n�chste B�cherei
											// Maps Activity zu kommen
				// TODO Auto-generated method stub
				Intent maps = new Intent(getApplicationContext(),
						MapActivity.class);
				startActivity(maps);

			}
		});

		// if (savedInstanceState == null) { // if statement wird nicht ben�tigt
		// getSupportFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment()).commit();
		// }
	}

	public void onResume() {
		super.onResume();
		mDatenbank = mHelper.getReadableDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.favorite_detail, menu);
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
	// public static class PlaceholderFragment extends Fragment { //
	// PlaceholderFragment wird nicht ben�tigt
	//
	// public PlaceholderFragment() {
	// }
	//
	// @Override
	// public View onCreateView(LayoutInflater inflater, ViewGroup container,
	// Bundle savedInstanceState) {
	// View rootView = inflater.inflate(R.layout.fragment_favorite_detail,
	// container, false);
	// return rootView;
	// }
	// }

}
