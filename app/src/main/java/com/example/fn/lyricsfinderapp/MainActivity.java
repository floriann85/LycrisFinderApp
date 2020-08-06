package com.example.fn.lyricsfinderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    // Ui Components
    // globalen EditText anlegen
    private EditText edtArtistName, edtSongName;

    // globalen Button anlegen
    private Button btnGetLyrics;

    // globel TextView anlegen
    TextView txtLyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // den Title für die Activity setzen
        setTitle("Welcome to Lyrics Finder App");

        // initialisieren
        edtArtistName = findViewById(R.id.edtArtistName);
        edtSongName = findViewById(R.id.edtSongName);

        btnGetLyrics = findViewById(R.id.btnGetLyrics);

        txtLyrics = findViewById(R.id.txtLyrics);

        // OnClickListener für die Funktion Button gedrückt
        btnGetLyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Toast anlegen für Informationsausgabe Display und anzeigen
                Toast.makeText(getApplicationContext(), "This button is tapped", Toast.LENGTH_SHORT).show();

                // URL Variable anlegen mit Zuweisung aus Eingabe edt
                String url = "https://api.lyrics.ovh/v1/" + edtArtistName.getText().toString() + "/" + edtSongName.getText().toString();

                // die Leerzeichen leeren
                url.replace("", "20%");

                // RequestQueue anlegen
                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

                // JsonObjectRequest anlegen für Abfrage Server
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            // das Ergebnis dem textView übergeben mit formatierten Text
                            txtLyrics.setText(response.getString("lyrics"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });

                // dem requestQueue den Wert aus jsonObjectRequest hinzufügen
                requestQueue.add(jsonObjectRequest);
            }
        });

    }
}
