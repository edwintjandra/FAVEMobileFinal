package com.example.favepertemuan3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.favepertemuan3.adapter.CharacterAdapter;
import com.example.favepertemuan3.model.Characters;
import com.example.favepertemuan3.model.TopCharactersResponse;
import com.example.favepertemuan3.utils.APIInterface;
import com.example.favepertemuan3.utils.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListFragment extends Fragment {
    RecyclerView rvCharacters;
    List<Characters> characterList = new ArrayList<>();
    CharacterAdapter characterAdapter;
    private int CURRENT_PAGE = 1;
    private boolean isLoading = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_list, container, false);

        rvCharacters = view.findViewById(R.id.rv_characters);
         LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        characterAdapter = new CharacterAdapter(characterList, getActivity());
        rvCharacters.setLayoutManager(linearLayoutManager);
        rvCharacters.setAdapter(characterAdapter);

        initScrollListener();
        loadCharacterData();


        return view;
    }
    private void initScrollListener(){
        rvCharacters.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if(!isLoading){
                    if(linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == characterList.size() -1){
                        CURRENT_PAGE++;
                        isLoading = true;
                         loadCharacterData();
                    }
                }
            }
        });
    }

    private void loadCharacterData() {
        APIInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(APIInterface.class);
        Call<TopCharactersResponse> call = apiInterface.getTopCharacters(CURRENT_PAGE);
        call.enqueue(new Callback<TopCharactersResponse>() {
            @Override
            public void onResponse(Call<TopCharactersResponse> call, Response<TopCharactersResponse> response) {
                if (response.isSuccessful()) {
                    TopCharactersResponse topCharacterResponse = response.body();
                    assert topCharacterResponse != null;
                    characterList.addAll(topCharacterResponse.getCharacters());
                    characterAdapter.setCharacterList(characterList);
                } else {
                    Toast.makeText(getActivity(), "Request Error :: " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
                isLoading = false;
            }

            @Override
            public void onFailure(Call<TopCharactersResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "There is error! :: " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}