package com.example.radek.repositories;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RepositoriesAdapter.RepositoryClickAction {
    @BindView(R.id.activity_main)
    protected RecyclerView mRepoList;

    private RepositoriesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Ustawiamy co ma pokazać się na ekranie na tym oknie (Activity)
        ButterKnife.bind(this);

        // Tworzymy obiekt adaptera, żeby uzupełnić go za chwilę danymi i przekazać do RecyclerView
        // w celu wyświetlenia listy
        mAdapter = new RepositoriesAdapter();

        // Mówimy Adapterowi, ze biezacy obiekt (this) reaguje na zdarzenia klikniecia.
        mAdapter.setmClickListener(this);

        // Mówimy dla RecyclerView w jakis sposób mają być umieszczone elementy na liście :
        // tutaj używamy klas z Androida, nie musimy implementować własnych
        // (najczęściej LinearLayoutManager - pionowy układ)
        mRepoList.setLayoutManager(new LinearLayoutManager(this));
        // Ustawiamy Adapter na RecyclerView, żeby wiedział co ma wyświetlić.
        mRepoList.setAdapter(mAdapter);

        GithubApi api = GithubApiFactory.getApi();
        api.listRepositories("radekBaran").enqueue(new Callback<List<GithubRepository>>() {
            @Override
            public void onResponse(Call<List<GithubRepository>> call, Response<List<GithubRepository>> response) {
                List<GithubRepository> repos = response.body();
                mAdapter.setmData(repos);
            }

            @Override
            public void onFailure(Call<List<GithubRepository>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(GithubRepository repository) {
        Intent websiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repository.getHtmlUrl()));
        startActivity(websiteIntent);
    }
}