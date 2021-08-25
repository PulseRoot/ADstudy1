package com.example.mydatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView textView;
EditText editText;
Button button;

EditText editText2;
Button button2;
EditText editText3;
EditText editText4;
EditText editText5;
Button button3;
Button button4;


SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.textView);
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        editText2 = (EditText)findViewById(R.id.editText2);
        button2 = (Button)findViewById(R.id.button2);

        editText3 = (EditText)findViewById(R.id.editText3);
        editText4 = (EditText)findViewById(R.id.editText4);
        editText5 = (EditText)findViewById(R.id.editText5);
        button3 = (Button)findViewById(R.id.button3);
        button4 = (Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText.getText().toString();
                openDatabase(databaseName);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText3.getText().toString().trim(); //.trim은 혹시 모르는 공백을 없애주는 역할을 함
                String ageStr = editText4.getText().toString().trim();
                String mobile = editText5.getText().toString().trim();

                int age = 0;
                try{
                    age = Integer.parseInt(ageStr); //자료형 변환
                }catch (Exception e){}

                insertData(name, age, mobile);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableName = editText2.getText().toString();
                selectData(tableName);
            }
        });
    }
    public void openDatabase(String databaseName){
        println("openDatabase() 호출됨.");

//        database  = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
//     if(database != null) {
//         println("데이터베이스 오픈됨.");
//     } 데이터 베이스 여는 법 1 (헬퍼 미사용)
        DatabaseHelper helper = new DatabaseHelper(this, databaseName, null, 2); //1로하면 테이블 삭제 안되고 1이상이면 삭제됨.
        database = helper.getWritableDatabase(); //쓰기 권한 부여
    }
    public void createTable(String tableName){
        println("createTable 호출됨.");

        if(database != null){   //open형 데이터베이스는 시작할 때마다 실행을 시켜주지 않으면 연결이 되지 않기 때문에 확인을 하고 불러와야한다.
            String sql = "CREATE TABLE " + tableName + "(" + "_id integer primary key autoincrement," + "name text," + "age integer," + "mobile text);";
            //create table+띄어쓰기 필수 명령어, 테이블 이름, 칼럼과 자료형 선언     //_id integer, PRIMARE KEY autoincrement은 내부적으로 생성되는 식별자 값임
           database.execSQL(sql);
           println("테이블 생성됨.");

        }else{
            println("먼저 데이터베이스를 오픈하세요.");
        }
    }
    public void insertData(String name, int age, String mobile){
        println("insertData() 호출됨.");

        if(database !=null){
            String sql = "insert into customer(name, age, mobile) values(?, ?, ?)";
            Object[] params = {name, age, mobile};


            database.execSQL(sql, params);

            println("데이터 추가함.");
        }else{
            println("먼저 데이터베이스를 오픈하세요.");
        }


    }
    public void selectData(String tableName){
        println("selectData() 호출됨.");

        if(database != null){
            String sql = "select name, age, mobile from " + tableName; //조회할 칼럼 (순서대로 인덱스) + 출처
            Cursor cursor =  database.rawQuery(sql, null); //Cursor로 리턴받음

            println("조회된 데이터 갯수 :" + cursor.getCount());

            for(int i = 0; i < cursor.getCount() ; i++){
                cursor.moveToNext();
                String name = cursor.getString(0);
                int age = cursor.getInt(1);
                String mobile = cursor.getString(2);

                println("#" + i + " -> " + name + ", " + age + ", " + mobile);
            }
            cursor.close(); //자원아끼기
        }
    }

    public void println(String data){
        textView.append(data + "\n");
    }

    class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context,  String name,  SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            println("onCreate 호출됨.");
            String tableName = "customer";
              //open형 데이터베이스는 시작할 때마다 실행을 시켜주지 않으면 연결이 되지 않기 때문에 확인을 하고 불러와야한다.
                String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + "_id integer primary key autoincrement," + "name text," + "age integer," + "mobile text);";
                //create table+띄어쓰기 필수 명령어, 테이블 이름, 칼럼과 자료형 선언     //_id integer, PRIMARE KEY autoincrement은 내부적으로 생성되는 식별자 값임
                db.execSQL(sql);
                println("테이블 생성됨.");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
           println("OnUpgrade() 호출됨." + oldVersion + "," + newVersion);

           if(newVersion > 1){
               String tableName = "customer";
               db.execSQL("drop table if exists " + tableName);
               println("테이블 삭제됨.");

               String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(" + "_id integer primary key autoincrement," + "name text," + "age integer," + "mobile text);";
               //create table+띄어쓰기 필수 명령어, 테이블 이름, 칼럼과 자료형 선언     //_id integer, PRIMARE KEY autoincrement은 내부적으로 생성되는 식별자 값임
               db.execSQL(sql);
               println("테이블 생성됨."); //재생성
           }

        }
    }
}