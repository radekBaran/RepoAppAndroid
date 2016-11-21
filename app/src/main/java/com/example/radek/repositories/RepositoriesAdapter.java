package com.example.radek.repositories;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


// Ta klasa odpowiada dla widoku listy (RecyclerView) na pytania :
// - ile elementów (getItemCount)
// - jak mają wyglądać (onCreateViewHolder) ?
// - jakie dane mają zawierać (onBindViewHolder) ?
// Jej metody nie są wołane bezpośrednio przez nas, tylko przez komponenty systemu !
public class RepositoriesAdapter extends RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder> {
    // Zmianna w ktorej trzymamy zbiór obiektów, które chcemy wyświetlić na ekranie w postaci listy.
    private List<GithubRepository> mData;

    // W związku z tym, że mData jest prywatne - dodaliśmy metodę setData, pozwalającą na ustawienie
    // danych do wyświetlenia.
    public void setmData(List<GithubRepository> data) {
        mData = data;
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
        TextView mLabel;

        public RepositoryViewHolder(View itemView) {
            super(itemView);
            mLabel = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}