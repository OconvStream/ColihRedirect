package app.notofficial.jw.colihredirect;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import app.notofficial.jw.colihredirect.model.Phone;

@Dao
public interface PhoneDao {

    @Query("SELECT * FROM Phone")
    List<Phone> getAll();

    @Insert
    void insert(Phone user);

    @Delete
    void delete(Phone user);
}

