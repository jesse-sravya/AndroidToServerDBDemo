package com.iceheart.www.serverconnection;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    String serverUrl = "http://192.168.1.3/";
    String link = serverUrl + "androidToPhp/index.php";
    private TextView mTextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextField = (TextView) findViewById(R.id.tf);

        String results = "";

        try {
            results = new FetchRowCount().execute(link).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        mTextField.setText(mTextField.getText().toString() + "\n" + results);

        mTextField.setText(mTextField.getText().toString() + "\n" + "Done connection");


        mTextField.setText(mTextField.getText().toString() + "\n" + "Bye Bye");

    }

    class FetchRowCount extends AsyncTask<String, String, String> {

        String rowCount;

        @Override
        protected String doInBackground(String... link) {

            try {
                URL connectURL = new URL(link[0]);

                String data  = URLEncoder.encode("message", "UTF-8")
                        + "=" + URLEncoder.encode("Input message", "UTF-8");

                URLConnection conn = connectURL.openConnection();

                conn.setDoOutput(true);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write( data );
                wr.flush();

                BufferedReader reader = new BufferedReader(new
                        InputStreamReader(conn.getInputStream()));

                return org.apache.commons.io.IOUtils.toString(reader);


            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

    }

}
