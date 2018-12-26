package com.example.jvmori.moviesapp.model.favMovies;

import android.content.Context;
import android.os.AsyncTask;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Movie.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {

    public static MovieDatabase instance;
    public abstract MovieDao movieDao();

    public static synchronized MovieDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), MovieDatabase.class, "movie_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private MovieDao movieDao;

        private PopulateDbAsyncTask(MovieDatabase movieDatabase){
            movieDao = movieDatabase.movieDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            Movie exampleMovie = new Movie(
                    "Call Me by Your Name",
                    "2017",
                    "Drama, Romance",
                    "Luca Guadagnino",
                    "Armie Hammer, Timothée Chalamet, Michael Stuhlbarg, Amira Casar",
                    "Call Me by Your Name is the story of a sudden and powerful romance that blossoms between an adolescent boy and a summer guest at his parents' cliffside mansion on the Italian Riviera. During the restless summer weeks, unrelenting but buried currents of obsession, fascination, and desire intensify their passion as they test the charged ground between them and verge toward the one thing both already fear they may never truly find again: total intimacy.",
                    "95%",
                    "7.9",
                    "3899"
                    );
            movieDao.insert(exampleMovie);
            movieDao.insert(exampleMovie);
            return null;
        }
    }

}
