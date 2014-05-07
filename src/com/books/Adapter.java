package com.books;

//	Importieren der Bibliotheken
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

// Adapter wird ben�tigt um mit dem HTTP Aufruf umgehen zu k�nnen

public class Adapter {

	//	Variablen deklarieren
	static String response = null;
	public final static int GET = 1;
	public final static int POST = 2;

	//	Aufrufen des Adapters
	public Adapter() {
	}

	/*	Service Aufrufen
		@url f�r einen URL request
		@method f�r einen HTTP request	*/
	public String makeServiceCall(String url, int method) {
		return this.makeServiceCall(url, method, null);
	}

	/*	Service Aufrufen
		@url f�r einen URL request
		@method f�r einen HTTP request
		@params f�r HTTP request Parameter	*/
	public String makeServiceCall(String url, int method,
			List<NameValuePair> params) {
		try {
			//	HTTP Client initialisieren
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpEntity httpEntity = null;
			HttpResponse httpResponse = null;

			// Als Methoden sind nur POST und GET m�glich

			//	HTTP request Methode pr�fen
			if (method == POST) {
				HttpPost httpPost = new HttpPost(url);

				//	Post Parameter hinzuf�gen
				if (params != null) {
					httpPost.setEntity(new UrlEncodedFormEntity(params));
				}

				httpResponse = httpClient.execute(httpPost);

			} else if (method == GET) {
				//	Hinzuf�gen von Parametern zur URL
				if (params != null) {
					String paramString = URLEncodedUtils.format(params, "utf-8");
					url += "?" + paramString;
				}

				HttpGet httpGet = new HttpGet(url);
				httpResponse = httpClient.execute(httpGet);
			}

			httpEntity = httpResponse.getEntity();
			response = EntityUtils.toString(httpEntity);

		//	Abfangen von Fehlern
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//	R�ckgabe des HTTP Links
		return response;
	}
}
