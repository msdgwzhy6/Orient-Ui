package com.orient.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


import com.orient.me.rv.TwoSideLayoutManager;
import com.orient.ui.R;
import com.orient.ui.adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<String> values = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private Button btnAddOne;
    private Button btnDeleteOne;
    private Button btnUpdateOne;
    private Button btnUpdateAll;
    private Button btnReset;
    private RecyclerViewAdapter mAdapter;

    public static void show(Context context){
        Intent intent = new Intent(context,RecyclerViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initWidget();
        initListener();
    }

    private void initWidget() {
        mRecyclerView = findViewById(R.id.rv_content);
        btnAddOne = findViewById(R.id.btn_add);
        btnDeleteOne = findViewById(R.id.btn_remove);
        btnUpdateOne = findViewById(R.id.btn_update);
        btnUpdateAll = findViewById(R.id.btn_update_all);
        btnReset = findViewById(R.id.btn_reset);

        values.add("Java");
        values.add("Android");
        values.add("Kotlin");
        values.add("Python");
        values.add("Vue");
        values.add("Flutter");

        mRecyclerView.setLayoutManager(new TwoSideLayoutManager(this));
        List<String> params = new ArrayList<>();
        params.addAll(values);
        mAdapter = new RecyclerViewAdapter(params,this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initListener() {
        btnAddOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> strings = mAdapter.getValues();
                strings.add("TeaOf");
                mAdapter.notifyItemInserted(mAdapter.getItemCount() - 1);
            }
        });

        btnDeleteOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> strings = mAdapter.getValues();
                if(strings.size() == 0)
                    return;
                strings.remove(0);
                mAdapter.notifyItemRemoved(0);
            }
        });

        btnUpdateOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> strings = mAdapter.getValues();
                if(strings.size()  == 0)
                    return;

                strings.set(0,"React");
                mAdapter.notifyItemChanged(0);
            }
        });

        btnUpdateAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> strings = mAdapter.getValues();
                strings.clear();

                strings.add("a");
                strings.add("b");
                strings.add("c");
                strings.add("d");
                strings.add("e");
                strings.add("f");
                mAdapter.notifyDataSetChanged();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> strings = mAdapter.getValues();
                strings.clear();

                strings.addAll(values);
                mAdapter.notifyDataSetChanged();
            }
        });
    }


}
