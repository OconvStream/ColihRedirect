package app.notofficial.jw.colihredirect.repository;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import app.notofficial.jw.colihredirect.dao.PhoneDao;
import app.notofficial.jw.colihredirect.database.PhoneDatabase;
import app.notofficial.jw.colihredirect.model.Phone;

public class PhoneRepository {

    private PhoneDao mPhoneDao;
    private List<Phone> mAllPhones;

    public PhoneRepository(Application application) {
        PhoneDatabase db = PhoneDatabase.getDatabase(application);
        mPhoneDao = db.phoneDao();
        mAllPhones = mPhoneDao.getAll();
    }

    public List<Phone> getmAllPhones() {
        return mAllPhones;
    }

    public Phone getUser() {
        return mPhoneDao.getNextPhoneToUse();
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
