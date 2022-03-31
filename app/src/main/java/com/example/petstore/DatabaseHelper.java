package com.example.petstore;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.icu.text.IDNA;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper
{
    static String name="PetStore";
    static int version=1;

    String CreateUserTableSql="CREATE TABLE if not exists 'users' (\n"+
            "\t'id'\tINTEGER PRIMARY KEY AUTOINCREMENT,\n"+
            "\t'username'\tTEXT,\n"+
            "\t'password'\tTEXT,\n"+
            "\t'email'\tTEXT,\n"+
            "\t'address'\tTEXT,\n"+
            "\t'phone'\tTEXT,\n"+
            "\t'gender'\tTEXT,\n"+
            "\t'image'\tBLOB\n"+

            ")";
    String CreateAnimalTableSql="CREATE TABLE if not exists 'animals' (\n"+
            "\t'animal_id'\tINTEGER PRIMARY KEY AUTOINCREMENT,\n"+
            "\t'animal_name'\tTEXT,\n"+
            "\t'animal_price'\tTEXT,\n"+
            "\t'animal_breed'\tTEXT,\n"+
            "\t'animal_color'\tTEXT,\n"+
            "\t'animal_description'\tTEXT,\n"+
            "\t'animal_image'\tBLOB\n"+

            ")";
    String CreateWishListTableSql = "CREATE TABLE if not exists \"wishlist\" (\n" +
            "\t\"wishlist_id\"\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"id\"\tINTEGER,\n" +
            "\t\"animal_id\"\tINTEGER,\n" +
            "\tFOREIGN KEY(\"id\") REFERENCES \"users\"(\"id\"),\n" +
            "\tFOREIGN KEY(\"animal_id\") REFERENCES \"animals\"(\"animal_id\")\n" +
            ")";

    public DatabaseHelper(@Nullable Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(CreateUserTableSql);
        getWritableDatabase().execSQL(CreateAnimalTableSql);
        getWritableDatabase().execSQL(CreateWishListTableSql);

    }


    public void insertUser(ContentValues contentValues)
    {
        getWritableDatabase().insert("users","",contentValues);
    }


    public  void updateUser(String id, ContentValues contentValues )
    {
        getWritableDatabase().update("users",contentValues,"id=" +id,null);
        //getWritableDatabase().update("users",contentValues,"id=?",new String[]{id});
    }


    public void deleteUser(String id)
    {
        //  getWritableDatabase().delete("users","id="+id,null);
        getWritableDatabase().delete("users","id=?",new String[]{id});
    }


    @SuppressLint("Range")
    public String isLoginSuccessful(String username, String password)
    {
        String sql="Select count(*) from users where username='"+username+"' and password='"+password+"'";
        String id = "0";
        SQLiteStatement statement=getReadableDatabase().compileStatement(sql);
        long l=statement.simpleQueryForLong();
        if(l==1)
        {
            sql="Select id from users where username='"+username+"' and password='"+password+"'";

            Cursor cursor= getReadableDatabase().rawQuery(sql,null);

            while(cursor.moveToNext())
            {
                id = cursor.getString(cursor.getColumnIndex("id"));
                Log.i("arrrr",id);
            }
            cursor.close();
        }
        Log.i("abc",id);

        return id;
    }

    @SuppressLint("Range")
    public ArrayList<UserInfo> getUserList()
    {
        String sql="Select * from users";

        Cursor cursor= getReadableDatabase().rawQuery(sql,null);
        ArrayList<UserInfo>list=new ArrayList<>();


        while(cursor.moveToNext())
        {
            UserInfo info=new UserInfo();
            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            info.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            info.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            info.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            info.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            info.setGender(cursor.getString(cursor.getColumnIndex("gender")));
            list.add(info);
        }
        cursor.close();
        return list;
    }

    @SuppressLint("Range")
    public UserInfo getUserInfo(String id)
    {

        String sql="Select * from users where id="+id;

        Cursor cursor= getReadableDatabase().rawQuery(sql,null);

        UserInfo info=new UserInfo();

        while(cursor.moveToNext())
        {

            info.setId(cursor.getString(cursor.getColumnIndex("id")));
            info.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            info.setAddress(cursor.getString(cursor.getColumnIndex("address")));
            info.setEmail(cursor.getString(cursor.getColumnIndex("email")));
            info.setPassword(cursor.getString(cursor.getColumnIndex("password")));
            info.setPhone(cursor.getString(cursor.getColumnIndex("phone")));
            info.setGender(cursor.getString(cursor.getColumnIndex("gender")));

        }
        cursor.close();
        return info;
    }




    public void insertAnimal(ContentValues contentValues)
    {
        getWritableDatabase().insert("animals","",contentValues);
    }

    public void updateAnimal(String animal_id, ContentValues contentValues )
    {
        getWritableDatabase().update("animals",contentValues,"animal_id=" +animal_id,null);

    }
    public void deleteAnimal(String animal_id)
    {
        //  getWritableDatabase().delete("animals","animal_id="+id,null);
        getWritableDatabase().delete("animals","animal_id=?",new String[]{animal_id});
    }




    @SuppressLint("Range")
    public ArrayList<AnimalInfo> getAnimalList()
    {
        String sql="Select * from animals";

        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        ArrayList<AnimalInfo>animallist=new ArrayList<>();


        while (cursor.moveToNext())
        {
            AnimalInfo Info=new AnimalInfo();
            Info.setAnimal_id(cursor.getString(cursor.getColumnIndex("animal_id")));
            Info.setAnimal_name(cursor.getString(cursor.getColumnIndex("animal_name")));
            Info.setAnimal_price(cursor.getString(cursor.getColumnIndex("animal_price")));
            Info.setAnimal_breed(cursor.getString(cursor.getColumnIndex("animal_breed")));
            Info.setAnimal_color(cursor.getString(cursor.getColumnIndex("animal_color")));
            Info.setAnimal_description(cursor.getString(cursor.getColumnIndex("animal_description")));
            animallist.add(Info);
        }
        cursor.close();
        Log.i("test", "fetch: "+animallist.size());

        return animallist;
    }


    @SuppressLint("Range")
    public AnimalInfo getAnimalInfo(String animal_id)
    {
        String sql="Select * from animals where animal_id="+animal_id;

        Cursor cursor=getReadableDatabase().rawQuery(sql,null);


        AnimalInfo Info=new AnimalInfo();
        while (cursor.moveToNext())
        {

            Info.setAnimal_id(cursor.getString(cursor.getColumnIndex("animal_id")));
            Info.setAnimal_name(cursor.getString(cursor.getColumnIndex("animal_name")));
            Info.setAnimal_price(cursor.getString(cursor.getColumnIndex("animal_price")));
            Info.setAnimal_breed(cursor.getString(cursor.getColumnIndex("animal_breed")));
            Info.setAnimal_color(cursor.getString(cursor.getColumnIndex("animal_color")));
            Info.setAnimal_description(cursor.getString(cursor.getColumnIndex("animal_description")));

        }
        cursor.close();
        return Info;
    }


    //for wishlist

    public void insertWishlist(ContentValues contentValues)
    {
        getWritableDatabase().insert("wishlist","",contentValues);
    }

    public void deleteWishList(String wishlist_id)
    {
        //  getWritableDatabase().delete("animals","animal_id="+id,null);
        getWritableDatabase().delete("wishlist","wishlist_id=?",new String[]{wishlist_id});
    }

//    @SuppressLint("Range")
//    public ArrayList<WishListInfo> getWishListData()
//    {
//        String sql="Select * from wishlist where id=;
//
//        Cursor cursor= getReadableDatabase().rawQuery(sql,null);
//        ArrayList<WishListInfo>list=new ArrayList<>();
//
//
//        while(cursor.moveToNext())
//        {
//            WishListInfo info=new WishListInfo();
//
//            info.setWishlist_id(cursor.getString(cursor.getColumnIndex("wishlist_id")));
//            info.setId(cursor.getString(cursor.getColumnIndex("id")));
//            info.setAnimal_id(cursor.getString(cursor.getColumnIndex("animal_id")));
//
//            list.add(info);
//        }
//        cursor.close();
//        return list;
//    }

    @SuppressLint("Range")
    public  ArrayList<WishListInfo> getWishListData( String id )
    {

        String sql="SELECT users.username , animals.animal_name ,wishlist_id FROM wishlist INNER JOIN users ON wishlist.id = users.id INNER JOIN animals ON wishlist.animal_id = animals.animal_id where users.id="+id;
            Log.i("this",sql);
        Cursor cursor= getReadableDatabase().rawQuery(sql,null);
        ArrayList<WishListInfo>list=new ArrayList<>();


        while(cursor.moveToNext())
        {
            WishListInfo info=new WishListInfo();
            info.setWishlist_id(cursor.getString(cursor.getColumnIndex("wishlist_id")));
            info.setUsername(cursor.getString(cursor.getColumnIndex("username")));
            info.setAnimal_name(cursor.getString(cursor.getColumnIndex("animal_name")));
            list.add(info);
        }
        cursor.close();
        Log.i("wishlist", "getWishListData: "+sql);
        Log.i("wishlist", "getWishListData: "+list.size());
        return list;
    }





    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CreateUserTableSql);
        sqLiteDatabase.execSQL(CreateAnimalTableSql);
        sqLiteDatabase.execSQL(CreateWishListTableSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void concept()
    {


        ArrayList<UserInfo>list=new ArrayList<>();
        UserInfo info=new UserInfo();


        ContentValues contentValues=new ContentValues();


    }



}
