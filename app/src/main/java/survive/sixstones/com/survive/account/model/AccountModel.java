package survive.sixstones.com.survive.account.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AccountModel extends SQLiteOpenHelper implements IAccount {

    private static final String DBNAME = "account.db";

    private static final int DBVERSION = 1;

    private static final String CREATESQL = "CREATE TABLE t_account(accountId INTEGER PRIMARY KEY AUTOINCREMENT,amount REAL NOT NULL,occur_time TEXT NOT NULL)";


    public AccountModel(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATESQL);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
