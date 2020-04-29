package cn.edu.sdwu.android02.classroom.sn170507180128;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Ch14Activity extends AppCompatActivity {
   private MyOpenHelper myOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ch14_1);
        myOpenHelper=new MyOpenHelper(this);

    }
//插入
    public void instert(View view){
        //以可写方式打开数据库(如果数据库不存在，自动创建数据库)
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务
            ContentValues contentValues=new ContentValues();
            contentValues.put("stuname","Tom");
            contentValues.put("stutel","13374558595");
            sqLiteDatabase.insert("student",null,contentValues);


            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用setTransactionSuccessful()方法，才会将数据保存到数据库中

        }catch (Exception e){
            ;
            Log.i(Ch10Activity1.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕将数据库关闭
            sqLiteDatabase.close();
        }

    }
    //查询
    public void query(View view){
        //以可写方法打开数据库(如果数据库不存在，自动创建数据库)
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getReadableDatabase();//只读方式打开
        try{
            Cursor cursor= sqLiteDatabase.rawQuery("select * from student where stuname=?",new  String[]{"Tom"});
            while (cursor.moveToNext()){
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String stuname=cursor.getString(cursor.getColumnIndex("stuname"));
                String stutel=cursor.getString(cursor.getColumnIndex("stutel"));
                Log.i(Ch14Activity.class.toString(),"id:"+id+",stuname:"+stuname+",stutel:"+stutel);

            }
            cursor.close();




        }catch (Exception e){
            Log.e(Ch14Activity.class.toString(),e.toString());
        }finally {
            //使用完毕，将数据库关闭
            sqLiteDatabase.close();
        }


    }
//删除
    public void delete(View view){
        //以可写方式打开数据库(如果数据库不存在，自动创建数据库)
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务

            sqLiteDatabase.delete("student","id=?",new String[]{ "1"});

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用setTransactionSuccessful()方法，才会将数据保存到数据库中

        }catch (Exception e){
            ;
            Log.i(Ch14Activity.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕将数据库关闭
            sqLiteDatabase.close();
        }

    }

//修改编辑
    public void modify(View view){
        //以可写方式打开数据库(如果数据库不存在，自动创建数据库)
        SQLiteDatabase sqLiteDatabase=myOpenHelper.getWritableDatabase();
        try{
            //将插入的数据放置在ContentValues中
            //事务的处理
            sqLiteDatabase.beginTransaction();//开启事务
            ContentValues contentValues=new ContentValues();

            contentValues.put("stutel","13355555555");

            sqLiteDatabase.update("student",contentValues,"id=?",new String[]{"2"});

            sqLiteDatabase.setTransactionSuccessful();//所有操作结束后，调用setTransactionSuccessful()方法，才会将数据保存到数据库中

        }catch (Exception e){
            ;
            Log.i(Ch14Activity.class.toString(),e.toString());
        }finally {
            sqLiteDatabase.endTransaction();//结束事务
            //使用完毕将数据库关闭
            sqLiteDatabase.close();
        }

    }
}
