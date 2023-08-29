package app.printec.myapplication.storage;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.List;

import app.printec.myapplication.R;

public class StorageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        EditText editText = findViewById(R.id.editText);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("EDIT", s.toString());


            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    if (event.getAction() == KeyEvent.ACTION_UP) {
                        getPreferences(MODE_PRIVATE).edit().putString("saved_value", String.valueOf(editText.getText())).apply();
                    }
                    Log.d("EDIT", String.valueOf(keyCode));
                }

                return true;
            }
        });

        UserEntity user = new UserEntity();
        user.setId("100");

        MyDatabaseInstance instance = Room.databaseBuilder(StorageActivity.this, MyDatabaseInstance.class, "database").build();

        new MyAsyncTask(instance, new MyAsyncTask.Listener() {
            @Override
            public void onSuccess(List<UserEntity> users) {
                Log.d("DATABASE", String.valueOf(users));
            }

            @Override
            public void onFailed(Exception exception) {
                Log.d("DATABASE", exception.getLocalizedMessage(), exception);
            }
        }).execute(user);

//        MyDatabaseInstance instance = Room.databaseBuilder(this, MyDatabaseInstance.class, "database").build();
//        try {
//
//            instance.getUserDao().save(user);
//
//            List<UserEntity> data = instance.getUserDao().read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView textView = findViewById(R.id.textView);

        String savedValue = getPreferences(MODE_PRIVATE).getString("saved_value", "");
        textView.setText(savedValue);
    }
}