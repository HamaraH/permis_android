package com.example.permis_de_conduire;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.nio.channels.Channel;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.permis);  //On affiche le XML du permis

    }

    public void cacherSignature(View view) {  //Fonction permettant de cacher la signature en cochant la CheckBox

        ImageView signature = findViewById(R.id.signature);

        if(signature.getVisibility() == View.VISIBLE) {  //Si la signature est déjà visible, on la rend invisible

            signature.setVisibility(View.INVISIBLE);
            Log.i("DIM","Signature passée en : cachée");

        }

        else{  //Sinon, on fait l'inverse

            signature.setVisibility(View.VISIBLE);
            Log.i("DIM","Signature passée en : affichée");

        }

    }

    public void changerSexe(View view) {  //Fonction permettant de changer le sexe affiché sur le permis

         Switch switch_sexe = findViewById(R.id.switch_sexe);
         TextView sexe = findViewById(R.id.sexe2);

         String valeur;

         if (switch_sexe.isChecked()){  //Si le switch est sur ON, on affiche la valeur F

             valeur = (String) switch_sexe.getTextOn();
             Log.i("DIM", "Sexe changé en : F");

         }

         else{  //Sinon on affiche M

             valeur = (String) switch_sexe.getTextOff();
             Log.i("DIM", "Sexe changé en : H");

         }

         sexe.setText(valeur);
    }

    public void maj_informations(View view) { //Fonction qui permet de changer le nom et le prénom

        EditText e_nom = findViewById(R.id.edit_nom);
        EditText e_prenom = findViewById(R.id.edit_prenom);

        TextView t_nom = findViewById(R.id.nom);
        TextView t_prenom = findViewById(R.id.prenom);

        String nom = e_nom.getText().toString();
        String prenom = e_prenom.getText().toString();

        t_nom.setText(nom);
        t_prenom.setText(prenom);

        e_nom.setText("");
        e_prenom.setText("");

        Log.i("DIM","Nom changé en : " + nom);
        Log.i("DIM","Prenom changé en : " + prenom);

    }

    public void sendToast(View view){  //Fonction qui permet d'afficher un toast

        Toast toast = Toast.makeText(MainActivity.this,"Ceci est un magnifique toast",Toast.LENGTH_SHORT);
        toast.show();
    }

    public void sendNotification(View view){  //Fonction qi permet d'envoyer une notification à l'utilisateur

        Log.i("DIM","Envoi d'une notification");
        NotificationCompat.Builder notif  = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.quebec)
                .setContentTitle("Voici une notification")  //On définit les attributs de la notification
                .setContentText("Magnifique notification, n'est-ce-pas ?")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        Intent intent= new Intent(this, MainActivity.class);
        PendingIntent content= PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_UPDATE_CURRENT);
        notif.setContentIntent(content);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0,notif.build());  //On l'affiche

    }

    @Override
    protected void onSaveInstanceState(Bundle outState){  //Fonction qui permet de sauvegarder les informations de l'utilisateur grâce au Bundle

        super.onSaveInstanceState(outState);
        Log.i("DIM","sauvegarde du nom,prénom, sexe et de la visibilité de la signature");

        TextView nom = findViewById(R.id.nom);
        TextView prenom = findViewById(R.id.prenom);
        TextView sexe = findViewById(R.id.sexe2);
        CheckBox signature = findViewById(R.id.signature_visible);
        ImageView image = findViewById(R.id.signature);

        outState.putCharSequence("nom",nom.getText());
        outState.putCharSequence("prenom",prenom.getText());   //On inscrit dans le Bundle les différentes informations
        outState.putCharSequence("sexe",sexe.getText());

        int visible = image.getVisibility();
        outState.putInt("signature",visible);
        System.out.println(visible);


    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){  //Fonction qui permet de restaurer les informations précédentes

        super.onRestoreInstanceState(saveInstanceState);

        Log.i("DIM", "chargement des informations précédemment enregistrées");

        TextView nom = findViewById(R.id.nom);
        TextView prenom = findViewById(R.id.prenom);
        TextView sexe = findViewById(R.id.sexe2);
        ImageView signature = findViewById(R.id.signature);
        CheckBox check = findViewById(R.id.signature_visible);

        nom.setText(saveInstanceState.getCharSequence("nom"));
        prenom.setText(saveInstanceState.getCharSequence("prenom"));  //On récupère les informations enregistrées lors de la sauvegarde du Bundle
        sexe.setText(saveInstanceState.getCharSequence("sexe")); //et on les affiche dans les bons composants

            if (saveInstanceState.getInt("signature") == 4) {  //Si la signature est invisible

                signature.setVisibility(View.INVISIBLE);  //On la remet invisible

                if (check != null)  //Si on est en mode paysage, on coche la CheckBox qui correspond (elle n'est pas définie en mode portrait)

                    check.setChecked(true);

            }

            else if(saveInstanceState.getInt("signature") == 0){  //Si la signature est visible

                signature.setVisibility(View.VISIBLE);  //On la remet visible

                if(check != null)  //Et si on est en mode paysage

                    check.setChecked(false);  //On décoche la CheckBox

            }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){  //Fonction qui permet d'afficher les options dans l'ActionBar afin d'afficher un menu

        Log.i("DIM","Création du menu");
        getMenuInflater().inflate(R.menu.menu_projet,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){  //Fonctions qui définit les différentes actions à effectuer lors d'un clic sur un item du menu

        switch(item.getItemId()) {

            case R.id.item1:  //Item 1 : on affiche un message

                Log.i("DIM", "Un log drôle");
                return true;

            case R.id.item2: //Item 2 : on affiche un autre message

                Log.i("DIM", "Un log encore plus drôle");
                return true;

            default:  //Sinon, on appelle la fonction parent

                return super.onOptionsItemSelected(item);
        }

    }

    public void ouvrirPageWeb(View view) {  //Fonction qui permet d'ouvrir une page Web, lancée depuis un bouton

        Log.i("DIM","Ouverture de la page d'accueil de Moodle");
        Uri pageweb = Uri.parse("https://moodle.uqac.ca/");
        Intent webIntent = new Intent(Intent.ACTION_VIEW,pageweb);
        startActivity(webIntent);

    }

    public void openActivity(View view) {

        Log.i("DIM","Lancement de la seconde activité");
        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        EditText message = findViewById(R.id.text_message);
        intent.putExtra("message",message.getText().toString());
        message.setText("");
        startActivityForResult(intent,974);

    }

    @Override
    public void onActivityResult(int request_code, int result_code, Intent intent) {

        Log.i("DIM", "Retour à l'activité principale");
        super.onActivityResult(request_code, result_code, intent);

        if (request_code == 974 && result_code == SecondActivity.RESULT_OK)

            Toast.makeText(MainActivity.this, intent.getStringExtra("resultat"), Toast.LENGTH_SHORT).show();

    }

}


