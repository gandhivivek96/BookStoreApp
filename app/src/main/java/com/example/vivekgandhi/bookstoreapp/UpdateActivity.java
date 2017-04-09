package com.example.vivekgandhi.bookstoreapp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateActivity extends AppCompatActivity {
    EditText name,author,price,stock,uid;
    private static final String TAG = "msg";
    String n,a,p,s,u;
    int pr,st;
    private TextView mResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
    }

    public void submitData(View view)
    { Log.i(TAG,"inside submitdata button");
        mResult = (TextView) findViewById(R.id.result);
        name = (EditText) findViewById(R.id.editText1);
        author = (EditText) findViewById(R.id.editText2);
        price = (EditText) findViewById(R.id.editText3);
        stock= (EditText) findViewById(R.id.editText4);
        uid = (EditText) findViewById(R.id.updateid);

        u = uid.getText().toString();
        n = name.getText().toString().trim();
        a = author.getText().toString().trim();
        p = price.getText().toString().trim();
        s = stock.getText().toString().trim();

        pr = Integer.parseInt(p);
        st = Integer.parseInt(s);
        Log.i(TAG,"b4 calling posttask");
        String ex = "http://192.168.2.5:1001/api/status/"+u;
        //make PUT request
        new PutDataTask().execute(ex);

    }

    class PutDataTask extends AsyncTask<String, Void, String> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(UpdateActivity.this);
            progressDialog.setMessage("Updating data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                return putData(params[0]);
            } catch (IOException ex) {
                return "Network error !";
            } catch (JSONException ex) {
                return "Data invalid !";
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            mResult.setText(result);

            if (progressDialog != null) {
                progressDialog.dismiss();
            }
        }

        private String putData(String urlPath) throws IOException, JSONException {

            BufferedWriter bufferedWriter = null;
            String result = null;

            try {


                //Create data to send to server
                JSONObject dataToSend = new JSONObject();
                dataToSend.put("bookname", n);
                dataToSend.put("author", a);
                dataToSend.put("price", pr);
                dataToSend.put("stock", st);

                //Initialize and config request, then connect to server
                URL url = new URL(urlPath);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000 /* milliseconds */);
                urlConnection.setConnectTimeout(10000 /* milliseconds */);
                urlConnection.setRequestMethod("PUT");
                urlConnection.setDoOutput(true);  //enable output (body data)
                urlConnection.setRequestProperty("Content-Type", "application/json");// set header
                urlConnection.connect();

                //Write data into server
                OutputStream outputStream = urlConnection.getOutputStream();
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
                bufferedWriter.write(dataToSend.toString());
                bufferedWriter.flush();

                //Check update successful or not
                if (urlConnection.getResponseCode() == 200) {
                    return "Update successfully !";
                } else {
                    return "Update failed !";
                }
            } finally {
                if(bufferedWriter != null) {
                    bufferedWriter.close();
                }
            }
        }
    }

}
