package com.evilgeniuses.sistemicpublications;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class JournalsFragment extends Fragment {

    public JournalsFragment() {}

    private FirebaseFirestore db;
    private CollectionReference JournalsCol;
    private FirestoreRecyclerAdapter adapter;

    private RecyclerView    RVJournals;
    private LinearLayoutManager linearLayoutManager;

    private static final String JOURNALS    =   "JOURNALS";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_journals, container, false);

        db              =   FirebaseFirestore.getInstance();
        JournalsCol     =   db.collection(JOURNALS);

        RVJournals      =   view.findViewById(R.id.RVJournals);

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RVJournals.setLayoutManager(linearLayoutManager);

        FirestoreRecyclerOptions<JournalsModel> options = new FirestoreRecyclerOptions.Builder<JournalsModel>()
                .setQuery(JournalsCol, JournalsModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<JournalsModel, JournalHolder>(options) {
            @Override
            public void onBindViewHolder(JournalHolder holder, int position, JournalsModel model) {
                holder.setTitle(model.getTitle());
                holder.setAuthor(model.getAuthor());
                holder.setYear(model.getYear());
            }

            @Override
            public JournalHolder onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.general_cardview, group, false);
                return new JournalHolder(view);
            }
        };

        RVJournals.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
    }

    //ViewHolder for firebaseUI pattern to populate recyclerview
    public static class JournalHolder extends RecyclerView.ViewHolder{

        View mView;

        public JournalHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView TTitle = mView.findViewById(R.id.TTitle);
            TTitle.setText(title);
        }

        public void setAuthor(String author) {
            TextView TAuthor = mView.findViewById(R.id.TAuthor);
            TAuthor.setText(author);
        }

        public void setYear(String year) {
            TextView TYear = mView.findViewById(R.id.TYear);
            TYear.setText(year);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
