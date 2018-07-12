package com.evilgeniuses.sistemicpublications;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddJournalFragment extends Fragment {

    private FirebaseFirestore   db;
    private CollectionReference AuthorsCol;
    private CollectionReference JournalsCol;

    private EditText                ETitle;
    private EditText                EJournal;
    private EditText                ENumber;
    private EditText                EVolume;
    private EditText                EPages;
    private EditText                EUrl;
    private Spinner                 SColciencias;
    private Spinner                 SSjr;
    private EditText                EYear;
    private AutoCompleteTextView    EAuthor;
    private TextView                TAuthorsList;
    private Button                  BAddAuthor;
    private Button                  BClearList;
    private Button                  BSave;
    private ProgressBar             PBLoad;

    private String          Title;
    private String          Journal;
    private String          Volume;
    private String          Number;
    private String          Pages;
    private String          Url;
    private String          Colciencias;
    private String          Sjr;
    private String          Year;
    private String          Authors;
    private List<String>    AuthorsList;
    private List<String>    ListaAutores;

    private static final String AUTHORS     =   "AUTHORS";
    private static final String JOURNALS    =   "JOURNALS";

    public AddJournalFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_add_journal, container, false);

        db              =   FirebaseFirestore.getInstance();
        AuthorsCol      =   db.collection(AUTHORS);
        JournalsCol     =   db.collection(JOURNALS);

        AuthorsList     =   new ArrayList<String>();
        ListaAutores    =   new ArrayList<String>();

        ETitle          =   view.findViewById(R.id.ETitle);
        EJournal        =   view.findViewById(R.id.EJournal);
        ENumber         =   view.findViewById(R.id.ENumber);
        EVolume         =   view.findViewById(R.id.EVolume);
        EPages          =   view.findViewById(R.id.EPages);
        EUrl            =   view.findViewById(R.id.EUrl);
        SColciencias    =   view.findViewById(R.id.SColciencia);
        SSjr            =   view.findViewById(R.id.SSjr);
        EYear           =   view.findViewById(R.id.EYear);
        EAuthor         =   view.findViewById(R.id.EAuthor);
        TAuthorsList    =   view.findViewById(R.id.TListaAutores);
        BAddAuthor      =   view.findViewById(R.id.BAddAuthor);
        BClearList      =   view.findViewById(R.id.BClearList);
        BSave           =   view.findViewById(R.id.BSave);
        PBLoad          =   view.findViewById(R.id.PBLoad);

        //Obtener todos los autores para poder generar el autocomplete
        AuthorsCol.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        AuthorsList.add(document.getString("name"));
                    }
                } else {
                    Toast.makeText(getContext(),R.string.Problema,Toast.LENGTH_SHORT).show();
                    Intent intent   =   new Intent(getContext(),MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),android.R.layout.select_dialog_item, AuthorsList);
        EAuthor.setAdapter(adapter);

        BAddAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  author  =   EAuthor.getText().toString();
                if (!TextUtils.isEmpty(author)){
                    ListaAutores.add(author);
                    String actual  =   TAuthorsList.getText().toString();
                    String nuevo   =   actual   +   author  +   ", ";
                    TAuthorsList.setText(nuevo);
                    EAuthor.getText().clear();
                }else{
                    Toast.makeText(getContext(),R.string.NoAuthor,Toast.LENGTH_SHORT).show();
                }
            }
        });

        BClearList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListaAutores.clear();
                TAuthorsList.setText("Authors List:");
            }
        });

        BSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PBLoad.setVisibility(View.VISIBLE);
                Title           =   ETitle.getText().toString().trim();
                Journal         =   EJournal.getText().toString().trim();
                Number          =   ENumber.getText().toString().trim();
                Volume          =   EVolume.getText().toString().trim();
                Pages           =   EPages.getText().toString().trim();
                Url             =   EUrl.getText().toString().trim();
                Colciencias     =   SColciencias.getSelectedItem().toString().trim();
                Sjr             =   SSjr.getSelectedItem().toString().trim();
                Year            =   EYear.getText().toString().trim();
                Authors         =   TextUtils.join(", ",ListaAutores);
                if (!TextUtils.isEmpty(Title) && !TextUtils.isEmpty(Journal) && !TextUtils.isEmpty(Number) &&
                    !TextUtils.isEmpty(Volume) && !TextUtils.isEmpty(Pages) && !TextUtils.isEmpty(Url) &&
                    !TextUtils.isEmpty(Colciencias) && !TextUtils.isEmpty(Sjr) && !TextUtils.isEmpty(Year) &&
                    !TextUtils.isEmpty(Authors)){

                    //Validaciones
                    Calendar calendar = Calendar.getInstance();
                    int actualyear = calendar.get(Calendar.YEAR);
                    int year    =   Integer.parseInt(Year);
                    if(year >= 1990 && year <= actualyear){
                        int contador    =   0;
                        for(int i   =   0;  i<ListaAutores.size(); i++){
                            if (!AuthorsList.contains(ListaAutores.get(i))){
                                contador++;
                            }
                        }
                        if (contador==0){
                            JournalsModel journal   =   new JournalsModel(Authors,Colciencias,Journal,Number,Pages,
                                    Sjr,Title,Url,Volume,Year);
                            JournalsCol.add(journal).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if(task.isComplete()){
                                        PBLoad.setVisibility(View.GONE);
                                        ETitle.getText().clear();
                                        EJournal.getText().clear();
                                        ENumber.getText().clear();
                                        EVolume.getText().clear();
                                        EPages.getText().clear();
                                        EUrl.getText().clear();
                                        EYear.getText().clear();
                                        EAuthor.getText().clear();
                                        ListaAutores.clear();
                                        TAuthorsList.setText("Authors List:");
                                        Toast.makeText(getContext(),R.string.SaveSuccess,Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                            for(int i   =   0;  i<ListaAutores.size(); i++){
                                PBLoad.setVisibility(View.VISIBLE);
                                String  author  =   ListaAutores.get(i);
                                AuthorsCol.document(author).collection(JOURNALS).add(journal)
                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentReference> task) {
                                        if(task.isComplete()){
                                            PBLoad.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }

                        }else{
                            Toast.makeText(getContext(),R.string.AuthorProblema,Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getContext(), R.string.YearProblema, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(),R.string.CamposVacios,Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
