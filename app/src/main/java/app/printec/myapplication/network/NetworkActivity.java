package app.printec.myapplication.network;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import app.printec.myapplication.R;

public class NetworkActivity extends AppCompatActivity {

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        queue = Volley.newRequestQueue(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


// Instantiate the RequestQueue.

        String url = "https://catfact.ninja/fact";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("NETWORK", response);
                        JsonResponse element = new Gson().fromJson(response, JsonResponse.class);

                        String a = element.getAuthor();

                        TextView textView = findViewById(R.id.network_txt);
                        textView.setText(element.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("NETWORK", error.getLocalizedMessage(), error);
                    }
                }
        ) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> data = new HashMap<>();

                return data;
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                JsonElement element = new JsonObject();


                return element.getAsBigInteger().toByteArray();
            }
        };

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://catfact.ninja/fact";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("NETWORK", response);
                        JsonResponse element = new Gson().fromJson(response, JsonResponse.class);

                        TextView textView = findViewById(R.id.network_txt);
                        textView.setText("Resumed" + "\n\n" + element.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("NETWORK", error.getLocalizedMessage(), error);
                    }
                }
        );

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        queue.stop();
        super.onStop();
    }
}