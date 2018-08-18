package app.notofficial.jw.colihredirect.repository;

import android.app.Application;
import android.arch.persistence.room.Database;

import app.notofficial.jw.colihredirect.PhoneDao;
import app.notofficial.jw.colihredirect.PhoneDatabase;

public class PhoneRepository {

    private PhoneDao mPhoneDao;
    private List<Phone> mAllPhones;

    public PhoneRepository(Application application) {
        PhoneDatabase db = PhoneDatabase.getDatabase(application);
        mPhoneDao = db.phoneDao();
        mAllPhones = mPhoneDao.getAll();
    }

    public List<User> getAllUsers() {
        return mAllUsers;
    }

    public void insert (Phone phone) {
        new insertAsyncTask(mPhoneDao).execute(phone);
    }

    private static class insertAsyncTask extends AsyncTask<Phone, Void, Void> {

        private PhoneDao mAsyncTaskDao;

        insertAsyncTask(PhoneDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Phone... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
