package com.example.fragmentcommunicationbidirectionnel.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.fragmentcommunicationbidirectionnel.R;


public class FirstFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private EditText editText;

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    /**
     * Pour envoyer de l'information au fragment, une simple fonction est requise.
     * Simplement assigner le String msg à la variable de classe message pour pouvoir l'utiliser
     * dans le fragment.
     * @param msg String servant a afficher dans un TextView.
     *
     * Ici je passe un String mais ça pourrait être un autre type de variable ou objet.
     */
    public void setDisplayText(String msg) {
        // Assigner le msg au EditText
        editText.setText( msg );
    }

    /**
     *
     * @param listener
     */
    public void setListener(OnFragmentInteractionListener listener){
        mListener = listener;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    }


    /**
     *
     * Pour pouvoir ajouter des composantes dans le fragment, de légères modifications doivent
     * être effectuées. À l'origine la fonction ressemble plus à ceci.
     *
     *
     *     public View onCreateView(LayoutInflater inflater, ViewGroup container,
     *                              Bundle savedInstanceState) {
     *         // Inflate the layout for this fragment
     *         return inflater.inflate( R.layout.fragment_first, container, false );
     *     }
     *
     * Par contre nus devons créer une View pour faire afficher le message passé par l'Activity.
     *
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_first, container, false );

        /* Le TextView est maintenant un EditText pour permettre de modifier le texte passé
        * par l'Activity pour le renvoyer à L'Activity */
        editText = view.findViewById( R.id.editText2 );
        Button btn = view.findViewById( R.id.button2 );

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonPressed( editText.getText().toString() );
            }
        } );

        return view;
    }

    private void onButtonPressed(String msg) {
        if (mListener != null) {
            mListener.onFragmentInteraction( msg );
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        // Si vous optez pour la 2e façon, le if else doit être retiré
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException( context.toString()
                    + " must implement OnFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * Nous allons nous servir de cette Interface pour communiquer avec l'Activity.
     * Nous devons remplacer le type de variable que nous allons passer. À l'origine
     * c'est de type Uri, mais nous allons passer un String. Modifiez aussi les types
     * dans la fonction onButtonPressed().
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String msg);
    }
}

