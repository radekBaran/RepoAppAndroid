package com.example.radek.repositories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


// Ta klasa odpowiada dla widoku listy (RecyclerView) na pytania :
// - ile elementów (getItemCount)
// - jak mają wyglądać (onCreateViewHolder) ?
// - jakie dane mają zawierać (onBindViewHolder) ?
// Jej metody nie są wołane bezpośrednio przez nas, tylko przez komponenty systemu !
public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder> {
    // Zmianna w ktorej trzymamy zbiór obiektów, które chcemy wyświetlić na ekranie w postaci listy.
    private List<GithubRepository> mData = Collections.emptyList();
    private RepositoryClickAction mClickListener;

    public void setmClickListener(RepositoryClickAction mClickListener) {
        this.mClickListener = mClickListener;
    }

    // W związku z tym, że mData jest prywatne - dodaliśmy metodę setData, pozwalającą na ustawienie
    // danych do wyświetlenia.
    public void setmData(List<GithubRepository> data) {
        mData = data;
        notifyDataSetChanged();
    }

    // Ta funkcja ma za zadanie stworzyć obiekt widoku pojedyńczego wiersza, czyli odpowiedzieć
    // dla RecyclerView na pytanie jak mają wyglądać jego elementy !
    // Pytanie : jak wygląda ?
    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // LayoutInflater - komponent do tworzenia obiektów View na podstawie plików XML (R.layout.XXX)
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        // Metoda inflate tworzy obiekt View na podstawie podanego pliku XML
        // Drugi jej parametr to konterner względem którego ma wymiarować nowo tworzony widok
        // Trzeci parametr mówi czy chcemy nowo tworzony widok dodać od razu do parent.
        View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new RepositoryViewHolder(rowView);
    }

    // Funkcja ma za zadanie wypełnić pojedyńczy utworzony wcześniej wiersz na liście
    // danymi na podstawie parametru (int position).
    // Pytanie : jakie dane ?
    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        // 1. Pobierz dane z zadanej pozycji (parametr position)
        GithubRepository repository = mData.get(position);

        // 2. Uzupełnij widok wiersza (parametr holder) danymi
        holder.mLabel.setText(repository.getName());
        holder.mRepository = repository;
    }

    // Mówi dla RecyclerView ile elementów ma zostać wyświetlone dla użytkownika na ekranie.
    // Pytanie : ile elementów / wierszy ?
    @Override
    public int getItemCount() {
        return mData.size();
    }

    // ViewHolder zapewnia nam możliwość wyszukiwania elementów wiersza na liście, tylko raz
    // podczas tworzenia widoku tego wiersza (onCreateViewHolder), tak żebyśmy nie musieli robić tego
    // za każdym razem w funkcji onBindViewHolder.
    public class RepositoryViewHolder extends RecyclerView.ViewHolder {

        @BindView(android.R.id.text1)
        TextView mLabel;

        // ViewHolder w zmiennej repository przechowuje informacje o biezacym wyswietlanym obiekcie GithubRepository
        GithubRepository mRepository;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        // Podpinamy się pod klikniecie danego wiersza (widoku) na liscie / na ekranie, w celu
        // poinformowanie mClickListenera o zdarzeniu.
        @OnClick
        protected void onViewClick() {
            // Wywołujemy zewnętrzny obiekt implementujący interfejs RepositoryClickAction
            // podając mu obiekt GithubRepository którey jest aktualnie wyswietlany w kliknietym wierszu tabeli
            mClickListener.onClick(mRepository);
        }
    }

    // Ten interfejs definiuje nam sposob powiadamiania zainteresowanych z zewnatrz o kliknieciach na wiersze
    // reprezentujace konkretne obiekty GithubRepository
    public interface RepositoryClickAction {
        void onClick(GithubRepository repository);
    }
}