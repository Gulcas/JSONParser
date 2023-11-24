package com.rafal.jsonparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String strJSON = "{ \"Android\":[{\"song_name\":\"piosenka 1\",\"song_id\":\"67\",\"artist_name\":\"artysta 1\"},{\"song_name\":\"piosenka 2\",\"song_id\":\"88\",\"artist_name\":\"artysta 2\"}]}";
        final TextView output = (TextView) findViewById(R.id.textView);
        final Button bparserjson = (Button) findViewById(R.id.button);

        String danedoParsowania = "Kliknij na przycisk do parsowania danych JSON\n\nDane w formacie JSON:\n" + strJSON;
        output.setText(danedoParsowania);

        bparserjson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String outputData = "";
                JSONObject jsonResponse;

                try {
                    jsonResponse = new JSONObject(strJSON);
                    JSONArray jsonMainNode = jsonResponse.optJSONArray("Android");
                    int lenghtJSONArr = jsonMainNode.length();

                    for (int i = 0; i < lenghtJSONArr; i++) {
                        JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                        int song_id = Integer.parseInt(jsonChildNode.optString("song_id"));
                        String song_name = jsonChildNode.optString("song_name").toString();
                        String artist_name = jsonChildNode.optString("artist_name").toString();

                        outputData += "Wiersz " + i + ":\n\n" + song_id + " | " + song_name + " | " + artist_name + " |\n\n";
                    }
                    output.setText(outputData);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}