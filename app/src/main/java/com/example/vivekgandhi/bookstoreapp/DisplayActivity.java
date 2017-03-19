package com.example.vivekgandhi.bookstoreapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;


public class DisplayActivity extends AppCompatActivity {

     TextView n,a,p,s;
    private TextView mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
    }
    public void displayData(View v) throws JSONException {
            n = (TextView) findViewById(R.id.bkname);
            a = (TextView) findViewById(R.id.author);
        p = (TextView) findViewById(R.id.price);
        s = (TextView) findViewById(R.id.stock);
        mResult = (TextView) findViewById(R.id.result);

        //make GET request
        new GetDataTask().execute("http://192.168.2.4:1001/api/status");
    }

    class GetDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

            progressDialog = new ProgressDialog(DisplayActivity.this);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                return getData(params[0]);
            } catch (IOException ex) {
                return "Network error !";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            //set data response to textView
            mResult.setText("Success");
            //n.setText("onPostExe");
            //JSONObject obj = null;
            JSONArray obj ;
            try {
                obj = new JSONArray(result);
                //obj = new JSONObject(result);
                for (int i = 0; i < obj.length(); i++) {
                    JSONObject jsonobj = obj.getJSONObject(i);

                    // JSONArray obj = new JSONArray(result);
                   // String id = jsonobj.getString("id");
                    String N1 = jsonobj.getString("bookname");
                    String N2 = jsonobj.getString("author");
                    String N3 = jsonobj.getString("price");
                    String N4 = jsonobj.getString("stock");
                    n.setText(N1);
                   // n.setText("hello");
                    a.setText("b4");
                    a.setText(N2);
                   // a.setText("after");
                    p.setText(N3);
                    s.setText(N4);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            //cancel progress dialog
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String getData(String urlPath) throws IOException {
          //  n.setText("GET Data");
            StringBuilder result = new StringBuilder();
            BufferedReader bufferedReader =null;

            try {
                //Initialize and config request, then connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(10000 /* milliseconds */);
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

                //Read data response from server
                InputStream inputStream = urlConnection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {

                    result.append(line).append("\n");

                }
                //JSONParser parser = new JSONParser();

                //JSONObject reader = new JSONObject(result.toString());


               // if(obj!=null)
                {
                   /* String N1  = obj.getString("bookname");
                    String N2  = obj.getString("author");
                    String N3  = obj.getString("price");
                    String N4  = obj.getString("stock");
                    n.setText(N1);
                    n.setText("hello");
                    a.setText("b4");
                    a.setText(N2);
                    a.setText("after");
                    p.setText(N3);
                    s.setText(N4);*/
                }

            } /*catch (JSONException e) {
                e.printStackTrace();
            } */finally {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }

            return result.toString();
        }
    }

}
