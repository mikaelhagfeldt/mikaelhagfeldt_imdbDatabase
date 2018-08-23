package com.example.mik.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter_Movie extends RecyclerView.Adapter<RecyclerViewAdapter_Movie.ViewHolder>
{
    private static final String TAG = "POK";
    private Context f_context;
    private List<Model_Movie> f_model_movieList;

    public RecyclerViewAdapter_Movie(Context p_context, List<Model_Movie> p_model_movieList)
    {
        Log.d(TAG, "RecyclerViewAdapter_Movie.java: Constructor called");

        f_context = p_context;
        f_model_movieList = p_model_movieList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.d(TAG, "RecyclerViewAdapter_Movie.java: onCreateViewHolder called");

        View lv_view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(lv_view, f_context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d(TAG, "RecyclerViewAdapter_Movie.java: onBindViewHolder called");

        Model_Movie lv_model_movie = f_model_movieList.get(position);

        holder.f_textView1.setText(lv_model_movie.getString_title());
        holder.f_textView2.setText(lv_model_movie.getString_year());
        holder.f_textView3.setText(lv_model_movie.getString_type());
        Picasso.get().load(lv_model_movie.getString_poster()).into(holder.f_imageView);
    }

    @Override
    public int getItemCount()
    {
        Log.d(TAG, "RecyclerViewAdapter_Movie.java: getItemCount called");

        return f_model_movieList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private static final String TAG = "POK";

        private ImageView f_imageView;
        private TextView f_textView1;
        private TextView f_textView2;
        private TextView f_textView3;

        public ViewHolder(View itemView, final Context p_context)
        {
            super(itemView);
            Log.d(TAG, "RecyclerViewAdapter_Movie.java: ViewHolder.java: Constructor called");
            f_context = p_context;

            f_imageView = itemView.findViewById(R.id.id_movie_row_imageView);
            f_textView1 = itemView.findViewById(R.id.id_movie_row_textView1);
            f_textView2 = itemView.findViewById(R.id.id_movie_row_textView2);
            f_textView3 = itemView.findViewById(R.id.id_movie_row_textView3);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Log.d(TAG, "RecyclerViewAdapter_Movie.java: ViewHolder.java: onClick called");

                    Toast.makeText(f_context, "Tap!", Toast.LENGTH_LONG).show();

                    Model_Movie lv_model_movie = f_model_movieList.get(getAdapterPosition());
                    Intent lv_intent = new Intent(f_context, Activity_MovieDetail.class);
                    lv_intent.putExtra("lv_intent.putExtra", lv_model_movie);
                    p_context.startActivity(lv_intent);
                }
            });
        }
    }
}
