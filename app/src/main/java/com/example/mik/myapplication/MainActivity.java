package com.example.mik.myapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "POK";

    // För "dialog box" när man påbörjar sökandet efter en specifik titel.

    private AlertDialog f_alertDialog;
    private AlertDialog.Builder f_builder;

    private RecyclerView f_recyclerView;
    private RequestQueue f_requestQueue;
    private List<Model_Movie> f_model_movieList;
    private RecyclerViewAdapter_Movie f_recyclerViewAdapter_movie;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "MainActivity.java: onCreate called");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Log.d(TAG, "MainActivity.java: onCreate: onClick called");
            }
        });

        f_requestQueue = Volley.newRequestQueue(this);
        f_model_movieList = new ArrayList<>();
        f_recyclerView = findViewById(R.id.id_content_main_recyclerView);
        f_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        f_recyclerView.setHasFixedSize(true);

        Util_SharedPreferences lv_util_sharedPreferences = new Util_SharedPreferences(this);
        lv_util_sharedPreferences.setSharedPreferences("Blade Runner");
        String lv_string_searchText = lv_util_sharedPreferences.getSharedPreferences("KEY");
        f_model_movieList = getAllMovies(lv_string_searchText);

        f_recyclerViewAdapter_movie = new RecyclerViewAdapter_Movie(this, f_model_movieList);
        f_recyclerView.setAdapter(f_recyclerViewAdapter_movie);
    }

    public List<Model_Movie> getAllMovies(String p_string)
    {
        Log.d(TAG, "MainActivity.java: getAllMovies called");

        JsonObjectRequest lv_jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Util_Constants.URL_START + p_string, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                Log.d(TAG, "MainActivity.java: getAllMovies: onResponse called");

                try
                {
                    JSONArray lv_jsonArray = response.getJSONArray("Search");

                    for (int i = 0; i < lv_jsonArray.length(); i++)
                    {
                        JSONObject lv_jsonObject = lv_jsonArray.getJSONObject(i);
                        Model_Movie lv_model_movie = new Model_Movie();
                        lv_model_movie.setString_title(lv_jsonObject.getString("Title"));
                        lv_model_movie.setString_year(lv_jsonObject.getString("Year"));
                        lv_model_movie.setString_type(lv_jsonObject.getString("Type"));
                        lv_model_movie.setString_poster(lv_jsonObject.getString("Poster"));
                        lv_model_movie.setString_imdbID(lv_jsonObject.getString("imdbID"));

                        f_model_movieList.add(lv_model_movie);

                        Log.d(TAG, "MainActivity.java: getAllMovies: onResponse: FOR LOOP --> " + lv_jsonObject.getString("Title"));
                    }

                    // Utan denna så uppdateras inte RecyclerView uppenbarligen?
                    f_recyclerViewAdapter_movie.notifyDataSetChanged();
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
                Log.d(TAG, "MainActivity.java: getAllMovies: onErrorResponse called");


            }
        });

        f_requestQueue.add(lv_jsonObjectRequest);
        return f_model_movieList;
    }

    public void dialog_searchBox()
    {
        Log.d(TAG, "MainActivity.java: dialog_searchBox called");

        f_builder = new AlertDialog.Builder(this);
        View lv_view = getLayoutInflater().inflate(R.layout.dialogbox_layout_view, null);
        final EditText lv_editText = lv_view.findViewById(R.id.id_dialogbox_layout_view_editText1);
        Button lv_button = lv_view.findViewById(R.id.id_dialogbox_layout_view_button1);
        f_builder.setView(lv_view);
        f_alertDialog = f_builder.create();
        f_alertDialog.show();

        lv_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Log.d(TAG, "MainActivity.java: dialog_searchBox: onClick called");

                Util_SharedPreferences lv_util_sharedPreferences = new Util_SharedPreferences(MainActivity.this);
                if (lv_editText.getText().toString().equals(""))
                {

                }
                else
                {
                    String lv_string = lv_editText.getText().toString();
                    lv_util_sharedPreferences.setSharedPreferences(lv_string);

                    // Ser till att listan raderas efter var uppdatering. Annars hamnar alla sökningar efter varandra, blir smetigt.
                    f_model_movieList.clear();

                    getAllMovies(lv_string);

                    // Ser till att vi kan återställa och lägga in objekt i RecyclerView igen
                    f_recyclerViewAdapter_movie.notifyDataSetChanged();
                }
                f_alertDialog.dismiss();
            }
        });














    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Log.d(TAG, "MainActivity.java: onCreateOptionsMenu called");

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        Log.d(TAG, "MainActivity.java: onOptionsItemSelected called");

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.id_menu_main_searchField)
        {
            dialog_searchBox();
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
