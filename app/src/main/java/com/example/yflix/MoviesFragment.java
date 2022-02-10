package com.example.yflix;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Movie> listMovies;
    AdapterMovies adapterMovies;
    private ProgressBar loadingPB;
    private NestedScrollView nestedSV;
    Endpoints endpoints;
    int i = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_peliculas, container, false);

        loadingPB = view.findViewById(R.id.idPBLoading);
        recyclerView = view.findViewById(R.id.recyclerPelicula);
        nestedSV = view.findViewById(R.id.idNestedSV);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listMovies = new ArrayList<>();
        endpoints = new Endpoints();
        adapterLoad();

        viewImage(i);

        nestedSV.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                i++;
                loadingPB.setVisibility(View.VISIBLE);
                if (i <= 928){
                    viewImage(i);
                }
            }
        });
    }

    private void viewImage(int s){
        Call<Pages> call = endpoints.getApiService().getMovies(s);
        call.enqueue(new Callback<Pages>() {
            @Override
            public void onResponse(Call<Pages> call, Response<Pages> response) {
                if (response.isSuccessful()){
                    Pages pages = response.body();
                    if (pages != null){
                        ArrayList<Movie> movies = pages.getResults();
                        if (movies.size() > 0){
                            listMovies.addAll(movies);
                            adapterMovies.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Pages> call, Throwable t) {

            }
        });
    }

    private void adapterLoad(){
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        adapterMovies = new AdapterMovies(listMovies, getContext());
        recyclerView.setAdapter(adapterMovies);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
    }
}