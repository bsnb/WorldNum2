package cn.edu.swufe.worldnum2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Document;

import java.io.IOException;
import java.util.regex.Pattern;

public class MainFragment extends Fragment{
    EditText numtext;
    private String name = "";
    private String num = "";

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.main_fragment,container,false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        numtext = getView().findViewById(R.id.num);
        getActivity().findViewById(R.id.search_chose_btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View btn) {
                String num = numtext.getText().toString();
                if (num.length() > 0) {
                    if (btn.getId() == R.id.search_chose_btn1) {
                        Intent config1 = new Intent(getActivity(), ConfigActivity.class);
                        config1.putExtra("num", num);
                        startActivity(config1);

                    } else {

                    }
                } else {
                    Toast.makeText(getActivity(), getResources().getString(R.string.root_fail_text), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.num, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.for_num:
                    Intent config = new Intent(getActivity(), ForNumActivity.class);
                    startActivity(config);
                default:
                    return super.onOptionsItemSelected(item);
            }
        }
    }


