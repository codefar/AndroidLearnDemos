/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.listview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.wangyonghua.androidlearndemos.R;

/**
 * This sample demonstrates how to create custom single- or multi-choice
 * {@link ListView} UIs. The most interesting bits are in
 * the <code>res/layout/</code> directory of this sample.
 */
public class MainActivity extends AppCompatActivity {

    int pos = 3;
    private ListView mListView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListView = (ListView) findViewById(android.R.id.list);
        //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_single_choice, Cheeses.CHEESES));
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "onItemSelected " + position + " " + id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "onItemClick " + position + " " + id);

            }
        });
        mListView.setItemChecked(pos, true);
        mListView.addHeaderView(View.inflate(this, R.layout.header, null));
        //getListView().setSelector();
        //
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A simple array adapter that creates a list of cheeses.
     */
    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return Cheeses.CHEESES.length;
        }

        @Override
        public String getItem(int position) {
            return Cheeses.CHEESES[position];
        }

        @Override
        public long getItemId(int position) {
            return Cheeses.CHEESES[position].hashCode();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
            }

            ((TextView) convertView.findViewById(android.R.id.text1))
                    .setText(getItem(position));
            return convertView;
        }
    }
}
