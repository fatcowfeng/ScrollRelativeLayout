
package com.fatcowfeng.android;

import com.fatcowfeng.android.scrollrelatelayout.HeadScrollListener;
import com.fatcowfeng.android.scrollrelatelayout.ScrollRelativeLayout;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private ScrollRelativeLayout headLayOut;

    private ListView list;

    private HeadScrollListener scrollListener = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headLayOut = (ScrollRelativeLayout) findViewById(R.id.head);
        View text = headLayOut.findViewById(R.id.head_text);
        if (text != null) {
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "text", Toast.LENGTH_SHORT).show();
                }
            });
        }
        list = (ListView) findViewById(R.id.list);
        scrollListener = new HeadScrollListener(headLayOut);
        View v = new View(this);
        LayoutParams lp = new LayoutParams(LayoutParams.FILL_PARENT, (int) getResources()
                .getDisplayMetrics().density * 300);
        v.setLayoutParams(lp);
        list.addHeaderView(v);
        list.setOnScrollListener(scrollListener);
        list.setAdapter(new BaseAdapter() {

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {

                return getLayoutInflater().inflate(R.layout.listitem, null);
            }

            @Override
            public long getItemId(int position) {
                // TODO Auto-generated method stub
                return 0;
            }

            @Override
            public Object getItem(int position) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return 100;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
