package cn.edu.swufe.worldnum2;

import android.app.ListActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForNumActivity extends ListActivity implements Runnable {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sp = getSharedPreferences("myrate", Context.MODE_PRIVATE);
        Thread t = new Thread(this);
        t.start();

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 7) {
                    List<String> list2 = (List<String>) msg.obj;
                    ListAdapter adapter = new ArrayAdapter<String>(ForNumActivity.this, android.R.layout.simple_list_item_1, list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }

        };

    }

    @Override
    public void run() {
        List<String> retList = new ArrayList<String>();
        Document doc;
        try {
            Thread.sleep(2000);
            doc = Jsoup.connect("http://www.51zzl.com/rcsh/guoji.asp").get();
            Elements tables = doc.getElementsByTag("table");
            List<NumItem> numList = new ArrayList<NumItem>();
            for (int i = 6; i < tables.size(); i ++) {
                Element table1 = tables.get(i);
                Elements tds = table1.getElementsByTag("td");
                for (int n=4;n<tds.size();n+=2){
                    Element td1 = tds.get(n);
                    Element td2 = tds.get(n+ 1);

                    String str1 = td1.text();
                    String str2 = td2.text();

                    retList.add(str1 + "==>"+ str2);
                    numList.add(new NumItem(str1,str2));}
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Message msg = handler.obtainMessage(7);
        msg.obj = retList;
        handler.sendMessage(msg);

    }
}



