
package com.example.suwonsmartapp.androidexam.database.contract;

import android.provider.BaseColumns;

/**
 * Created by junsuk on 15. 9. 18..
 */
public final class UserContract {
    public UserContract() {
    }

    public static abstract class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "User";
        public static final String COLUMN_NAME_NICKNAME = "nickname";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PASSWORD = "password";
    }
}
