package ru.dvfu.mrcpk.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Объявление переменных
    //Переменная для инициализации DB
    DBHelper sqlHelper;
    //Для управления DB - query(),insert(),delete(),update(), execSQL()
    SQLiteDatabase sqLiteDatabase;
    //Объект для управления выгруженными данными
    Cursor cursor;
    //Переменная для адаптера
    SimpleCursorAdapter simpleCursorAdapter;

    ArrayList<String> arrayList=new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;

    Button buttonEdit;
    Button buttonAdd;
    Button buttonDel;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Экземпляр класса DB
        sqlHelper = new DBHelper(this);

        //объект класса для получения доступа к управлению с поддержкой записи данных
        sqLiteDatabase = sqlHelper.getWritableDatabase();

        // Выборка всех данных их таблицы
        cursor = sqLiteDatabase.query(DBHelper.TABLE_MAIN,null,null,null,null,null,null);

        // Заполнение массива данных для ArrayAdapter
        if(cursor.moveToFirst()){
            do{
                arrayList.add(cursor.getString(1) + " " + cursor.getString(2));
            }while (cursor.moveToNext());
        }

        //Массивы сопоставлений
        String[] from = {DBHelper.TABLE_MAIN_FIRSTNAME, DBHelper.TABLE_MAIN_LASTNAME, DBHelper.TABLE_MAIN_PHONENUM, DBHelper.TABLE_MAIN_EMAIL};
        int[] to=new int[]{R.id.itemFirstname,R.id.itemLastname,R.id.itemPhone,R.id.itemEmail};

        // Создание объекта адаптера
        simpleCursorAdapter = new SimpleCursorAdapter(this,R.layout.item,cursor,from,to,1);
//        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);

        // Создание объекта списка
        listView = (ListView) findViewById(R.id.listView);

        // Инициализация списка
        listView.setAdapter(simpleCursorAdapter);
    }
}
