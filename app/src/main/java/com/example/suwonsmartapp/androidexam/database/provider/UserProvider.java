package com.example.suwonsmartapp.androidexam.database.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.example.suwonsmartapp.androidexam.database.contract.UserContract;
import com.example.suwonsmartapp.androidexam.database.helper.UserDbHelper;

// http://developer.android.com/intl/ko/guide/topics/providers/content-provider-creating.html
public class UserProvider extends ContentProvider {

    // 권한
    private static final String AUTHORITY = "com.example.suwonsmartapp.androidexam.provider";

    // 외부에서 이 프로바이더를 사용하기 위한 URI 정의
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" +
            UserContract.UserEntry.TABLE_NAME);

    // Query 기능을 여러가지로 제공하기 위해
    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    private static final int ALL = 1;       // 모든 데이타 제공
    private static final int SINGLE = 2;    // single 데이타 제공

    static {
        sUriMatcher.addURI(AUTHORITY, UserContract.UserEntry.TABLE_NAME, ALL);
        sUriMatcher.addURI(AUTHORITY, UserContract.UserEntry.TABLE_NAME + "/#", SINGLE);
    }

    // 이 프로바이더를 통해서 제공할 DB 가 정의되어 있는 Helper 선언
    private UserDbHelper mHelper;

    public UserProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        mHelper = new UserDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder qBuilder = new SQLiteQueryBuilder();
        qBuilder.setTables(UserContract.UserEntry.TABLE_NAME);

        switch (sUriMatcher.match(uri)) {
            case ALL:
                // sortOrder 가 지정되어 있지 않을 때, _ID를 ASC 로 정렬 한다

                // Null 체크, String == "" 인지 체크
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = UserContract.UserEntry._ID + " ASC";    // ASC or DESC
                }
                break;
            case SINGLE:
                // content://com.example.suwonsmartapp.androidexam.provider/User/#20
                selection = selection + "_ID = " + uri.getLastPathSegment();

                qBuilder.appendWhere(selection);
                break;
        }

        Cursor cursor = qBuilder.query(mHelper.getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
