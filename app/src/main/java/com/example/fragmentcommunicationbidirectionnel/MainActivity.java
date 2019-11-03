package com.example.fragmentcommunicationbidirectionnel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.fragmentcommunicationbidirectionnel.fragment.FirstFragment;


/**
 * Comme mentionné dans le PowerPoint, il y a deux façon d'implémenter. Choisissez la façon que
 * vous prérérez!
 *
 * 1- L'Activity implements l'interface du fragment.
 *
 * 2- Un peu comme le onClick() d’un bouton, le Delegate (sera en commentaire)
 *
 */
public class MainActivity extends AppCompatActivity implements FirstFragment.OnFragmentInteractionListener  {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textView = findViewById( R.id.textView );

        Button btn = findViewById( R.id.button );

        btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById( R.id.editText );
                FirstFragment fragment = (FirstFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
                // Utilisation de la fonction créé pour communiquer avec le fragment.
                assert fragment != null;
                fragment.setDisplayText( editText.getText().toString() );


//                fragment.setListener( new FirstFragment.OnFragmentInteractionListener() {
//                    @Override
//                    public void onFragmentInteraction(String msg) {
//                        textView.setText( msg );
//                    }
//                } );

            }
        } );
    }


    /**
     * Si vous optez pour la 2e façon cette fonction doit être supprimée et le
     * implements FirstFragment.OnFragmentInteractionListener .
     *
     * @param msg String le message à afficher dans le textview
     */
    @Override
    public void onFragmentInteraction(String msg) {
        textView.setText( msg );
    }
}
