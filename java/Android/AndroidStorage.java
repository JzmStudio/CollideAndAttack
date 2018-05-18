package Android;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 存储的位置由此类决定,此类输出为字符流
 */
public class AndroidStorage {
    private AndroidMain main;
    private File path;
    private Resources resources;
    private String packageName;

    public AndroidStorage(AndroidMain main)
    {
        this.main=main;
        path=main.getFilesDir();
        Log.d("debug",path.getPath());
        resources=main.getResources();
        packageName=main.getPackageName();
    }

    public Bitmap getBitmapFromAssets(int id, Bitmap.Config config)
    {
        Bitmap bitmap;
        InputStream input=resources.openRawResource(id);
        if(config!=null)
        {
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inPreferredConfig=config;
            bitmap=BitmapFactory.decodeStream(input,null,options);
        }
        else
        {
            bitmap=BitmapFactory.decodeStream(input);
        }
        if(bitmap==null)
            Log.e("decode","error");
        return bitmap;
    }

    /**
     *
     * @param name
     * @param deftype 资源在哪个类型中,如"drawable"等
     * @param config
     * @return
     */
    public Bitmap getBitmapFromAssets(String name,String deftype, Bitmap.Config config)
    {
        Bitmap bitmap;
        InputStream input=resources.openRawResource(resources.getIdentifier(name,deftype,packageName));
        if(config!=null)
        {
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inPreferredConfig=config;
            bitmap=BitmapFactory.decodeStream(input,null,options);
        }
        else
        {
            bitmap=BitmapFactory.decodeStream(input);
        }
        if(bitmap==null)
            Log.e("decode","error");
        return bitmap;
    }

    /**
     * 若文件以存在则清除全部数据
     * @param filename
     * @return
     */
    public PrintWriter newFileToWrite(String filename)
    {
        File file=new File(path.getPath()+'/'+filename);
        if(!file.exists()){
            try {
                PrintWriter printWriter=new PrintWriter(file);
                return printWriter;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("file","file create error"+filename);
                return null;
            }
        }
        else{
            try{
                PrintWriter printWriter=new PrintWriter(file);
                return printWriter;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Log.e("file","file create error"+filename);
                return null;
            }
        }
    }

    public void logFiles()
    {
        String[] strings=path.list();
        for(String s:strings){
            Log.d("filelist",s);
        }
    }

    public void deleteFile(String filename)
    {
        File file=new File(path.getPath()+'/'+filename);
        if(file.exists())
        {
            file.delete();
            Log.d("file","delete "+file+" success");
        }
    }

    public Scanner openFileToRead(String filename)
    {
        File file=new File(path.getPath()+'/'+filename);
        if(file.exists()){
            try {
                Scanner scanner=new Scanner(file);
                return scanner;
            } catch (FileNotFoundException e) {
                Log.e("file","Open error");
                e.printStackTrace();
                return null;
            }
        }
        else {
            Log.e("file","Open a not existing file");
            return null;
        }
    }

    /**
     * 若文件存在则加在文件末尾,否则新建文件
     * @param filename
     * @return
     */
    public FileWriter appendAFile(String filename)
    {
        File file=new File(path.getPath()+'/'+filename);
        try{
            FileWriter writer=new FileWriter(file,true);
            return writer;
        } catch (IOException e) {
            Log.e("file","open "+filename+"error");
            e.printStackTrace();
            return null;
        }
    }

    public void mkdir(String dirname)
    {
        File file=new File(path.getPath()+'/'+dirname);
        file.mkdirs();
    }
}