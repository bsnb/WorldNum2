package cn.edu.swufe.worldnum2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Pattern;

import cn.edu.swufe.worldnum2.R;

public class ConfigActivity extends AppCompatActivity implements Runnable{
    EditText Num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        Thread t = new Thread(this);
        t.start();
    }
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @Override
    public void run() {
        Document doc;
        Intent intent = getIntent();
        String num = intent.getStringExtra("num");
        try {
            doc = Jsoup.connect("http://www.51zzl.com/rcsh/guoji.asp").get();
            Elements tables = doc.getElementsByTag("table");
            for (int i = 6; i < tables.size(); i ++) {
                Element table1 = tables.get(i);
                Elements tds = table1.getElementsByTag("td");
                for (int n = 4; n < tds.size(); n += 2) {
                    Element td1 = tds.get(n);
                    Element td2 = tds.get(n + 1);
                    String str1 = td1.text();
                    String str2 = td2.text();
                    if (isInteger(num)) {
                        if (num.equals(str2)) {
                            Num = findViewById(R.id.result);
                            Num.setText(str1 + "==>" + num);
                            break;
                        }
                    }else{
                        if (num.equals(str1)) {
                            Num = findViewById(R.id.result);
                            Num.setText(num + "==>" + str2);
                            break;
                        }
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}

