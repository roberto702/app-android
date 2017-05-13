package cl.estfel.siac;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;



public class SuccessActivity extends AppCompatActivity {



        @Override
        protected  void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_success);



        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu){
            return MenuSuperior.instancia.onCreateOptionsMenu(menu, this);

        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            return MenuSuperior.instancia.onOptionsItemSelected(item, this);
        }
}

