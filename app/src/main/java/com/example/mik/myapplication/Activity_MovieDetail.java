package com.example.mik.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_MovieDetail extends AppCompatActivity
{
    private static final String TAG = "POK";

    private TextView f_textView_title;
    private TextView f_textView_year;
    private TextView f_textView_type;
    private TextView f_textView_poster;

    private TextView f_textView_rated;
    private TextView f_textView_released;
    private TextView f_textView_runtime;
    private TextView f_textView_genre;
    private TextView f_textView_director;
    private TextView f_textView_writer;
    private TextView f_textView_plot;
    private TextView f_textView_country;
    private ImageView f_imageView_imageView;

    private RequestQueue f_requestQueue;
    private Model_Movie f_model_movie;
    private String f_string_imdbID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__movie_detail);
        Log.d(TAG, "Activity_MovieDetail.java: onCreate called");

        f_requestQueue = Volley.newRequestQueue(this);
        f_model_movie = (Model_Movie) getIntent().getSerializableExtra("lv_intent.putExtra");
        f_string_imdbID = f_model_movie.getString_imdbID();

        Log.d(TAG, "Activity_MovieDetail.java: onCreate: imdbID = " + f_string_imdbID);

        setUpWidgets();
        getMovieDetails(f_string_imdbID);
    }

    private void setUpWidgets()
    {
        Log.d(TAG, "Activity_MovieDetail.java: setUpWidgets called");

        f_imageView_imageView = findViewById(R.id.id_activity_movie_detail_imageView);
        f_textView_title = findViewById(R.id.id_activity_movie_detail_textView1);
        f_textView_year = findViewById(R.id.id_activity_movie_detail_textView2);
        f_textView_type = findViewById(R.id.id_activity_movie_detail_textView3);
        f_textView_rated = findViewById(R.id.id_activity_movie_detail_textView4);
        f_textView_released = findViewById(R.id.id_activity_movie_detail_textView5);
        f_textView_runtime = findViewById(R.id.id_activity_movie_detail_textView6);
        f_textView_genre = findViewById(R.id.id_activity_movie_detail_textView7);
        f_textView_director = findViewById(R.id.id_activity_movie_detail_textView8);
        f_textView_writer = findViewById(R.id.id_activity_movie_detail_textView9);
        f_textView_plot = findViewById(R.id.id_activity_movie_detail_textView10);
        f_textView_country = findViewById(R.id.id_activity_movie_detail_textView11);
    }

    public void getMovieDetails(String p_string_id)
    {
        Log.d(TAG, "Activity_MovieDetail.java: getMovieDetails called");

        JsonObjectRequest lv_jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Util_Constants.URL_START_DETAIL + p_string_id, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d(TAG, "Activity_MovieDetail.java: getMovieDetails: onResponse called");

                try
                {
                    f_textView_title.setText("Title: " + response.getString("Title"));
                    f_textView_year.setText("Year: " + response.getString("Year"));
                    f_textView_type.setText("Type: " + response.getString("Type"));
                    f_textView_rated.setText("Rated: " + response.getString("Rated"));
                    f_textView_released.setText("Released: " + response.getString("Released"));
                    f_textView_runtime.setText("Runtime: " + response.getString("Runtime"));
                    f_textView_genre.setText("Genre: " + response.getString("Genre"));
                    f_textView_director.setText("Director: " + response.getString("Director"));
                    f_textView_writer.setText("Writer: " + response.getString("Writer"));
                    f_textView_plot.setText("Plot: " + response.getString("Plot"));
                    f_textView_country.setText("Country: " + response.getString("Country"));
                    Picasso.get().load(response.getString("Poster")).into(f_imageView_imageView);
                }
                catch (JSONException p_e)
                {
                    p_e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.d(TAG, "Activity_MovieDetail.java: getMoviesDetails: onErrorResponse called");
            }
        });

        f_requestQueue.add(lv_jsonObjectRequest);
    }
}



