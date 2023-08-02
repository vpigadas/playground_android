package app.printec.myapplication.storage;

import android.os.AsyncTask;

import androidx.annotation.Nullable;

import java.util.List;

public class MyAsyncTask extends AsyncTask<UserEntity, Void, List<UserEntity>> {

    interface Listener {
        void onSuccess(List<UserEntity> users);

        void onFailed(Exception exception);
    }

    private MyDatabaseInstance database;
    private Listener callback;

    @Nullable
    private Exception exception = null;

    public MyAsyncTask(MyDatabaseInstance database, Listener callback) {
        this.database = database;
        this.callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<UserEntity> doInBackground(UserEntity... userEntities) {
        try {
            for (UserEntity user : userEntities) {
                try {
                    database.getUserDao().save(user);
                } catch (Exception e) {
                    exception = e;
                    return null;
                }
            }

            List<UserEntity> data = database.getUserDao().read();
            return data;
        } catch (Exception exception) {
            this.exception = exception;
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<UserEntity> userEntities) {
        super.onPostExecute(userEntities);

        if (userEntities == null) {
            if (exception != null) {
                callback.onFailed(exception);
            } else {
                callback.onFailed(new Exception("unknown exception"));
            }
        } else {
            callback.onSuccess(userEntities);
        }
    }
}
