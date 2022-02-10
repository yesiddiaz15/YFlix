package com.example.yflix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovies extends RecyclerView.Adapter<AdapterMovies.ViewHolderMovies> {

    ArrayList<Movie> movies;
    Context mContext;

    public AdapterMovies(ArrayList<Movie> movies, Context context){
        this.movies = movies;
        this.mContext = context;
    }

    @NonNull
    @Override
    public AdapterMovies.ViewHolderMovies onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pelicula, parent, false);

        return new ViewHolderMovies(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMovies holder, int position) {
        holder.assignMovie(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderMovies extends RecyclerView.ViewHolder {

        ImageButton imageMovie;
        TextView nameMovie;

        public ViewHolderMovies(@NonNull View itemView) {
            super(itemView);
            imageMovie = itemView.findViewById(R.id.imageMovie);
            nameMovie = itemView.findViewById(R.id.nameMovie);
        }

        public void assignMovie(Movie movie){
            String build = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
            try{
                Picasso.get().load(build).resize(200,350).centerCrop().into(imageMovie);
                nameMovie.setText(movie.getOriginal_title());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
