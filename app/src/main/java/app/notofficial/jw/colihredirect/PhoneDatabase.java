package app.notofficial.jw.colihredirect;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import app.notofficial.jw.colihredirect.model.Phone;


@Database(entities = {Phone.class}, version = 1, exportSchema = false)
public abstract class PhoneDatabase extends RoomDatabase{

    public abstract PhoneDao phoneDao();
    private static PhoneDatabase INSTANCE;

    public static PhoneDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PhoneDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PhoneDatabase.class, "phone_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}


