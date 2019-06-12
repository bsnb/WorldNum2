package cn.edu.swufe.worldnum2;

import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;

public class MainActivity extends AppCompatActivity {

    private BottomBar mBottomBar;
    private MainFragment mMainFragment;
    private TwoFragment twoFragment;
    private ThreeFragment threeFragment;
    private FragmentTransaction transaction;
    private BottomBarBadge unreadMessages;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        mBottomBar = BottomBar.attach(this, savedInstanceState);
        // 千万注意：这个方法要用在 setItemsFromMenu 之前，也就是tab还没有设置之前要先调用，不然会报错。
        mBottomBar.noTabletGoodness();

        //添加初始Fragment
        defaultFragment(null == mMainFragment ? mMainFragment = MainFragment.newInstance() : mMainFragment);
        mBottomBar.setItems(R.menu.bottombar_menu);
        mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            @Override
            public void onMenuTabSelected(@IdRes int menuItemId) {
                //单击事件 menuItemId 是 R.menu.bottombar_menu 中 item 的 id
                switch (menuItemId) {
                    case R.id.bb_menu_main:
                        if (null == mMainFragment) {
                            mMainFragment = MainFragment.newInstance();
                        }
                        replaceFragment(mMainFragment);
                        break;
                    case R.id.bb_menu_chart:
                        if (null == twoFragment) {
                            twoFragment = TwoFragment.newInstance();
                        }
                        replaceFragment(twoFragment);
                        unreadMessages.setVisibility(View.GONE);
                        break;
                    case R.id.bb_menu_timeline:
                        if (null == threeFragment) {
                            threeFragment = ThreeFragment.newInstance();
                        }
                        replaceFragment(threeFragment);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onMenuTabReSelected(@IdRes int menuItemId) {
                //重选事件，当前已经选择了这个，又点了这个tab。微博点击首页刷新页面
            }
        });
        // 当点击不同按钮的时候，设置不同的颜色
        // 可以用以下三种方式来设置颜色.
        mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.colorPrimary));
        mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.colorAccent));
        mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.green));

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存BottomBar的状态
        mBottomBar.onSaveInstanceState(outState);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.replace(R.id.main_fragment_layout, fragment);
//        transaction.hide();
        transaction.commit();

    }

    private void defaultFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        transaction.add(R.id.main_fragment_layout, fragment);
        transaction.commit();
    }

}
