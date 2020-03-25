package com.neuman.brutus.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.neuman.brutus.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private View view;

    private ImageButton search, filter;
    private LinearLayout searchview;
    private AppBarLayout appbar;
    private boolean isopen = false;
    private boolean isfilter = false;
    private ImageView close;
    private LinearLayout sortandfilterview;
    private Spinner sortby;
    private EditText value1,value2;
    private Button calculate;

    private void init(){
        search = view.findViewById(R.id.search);
        search.setOnClickListener(v -> setSearch());
        filter = view.findViewById(R.id.filter);
        filter.setOnClickListener(v -> setFilter());
        close = view.findViewById(R.id.close);
        close.setOnClickListener(v -> setClose());
        searchview = view.findViewById(R.id.searchview);
        appbar = view.findViewById(R.id.appbar);

        sortandfilterview = view.findViewById(R.id.sortandfilterview);
        sortby = view.findViewById(R.id.sortby);
        value1 = view.findViewById(R.id.value1);
        value2 = view.findViewById(R.id.value2);
        calculate = view.findViewById(R.id.calculate);

        // static data
        ArrayList<String> filters = new ArrayList<>();
        filters.add("Price");
        filters.add("Rank");
        filters.add("Date");

        ArrayAdapter adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, R.id.citySpinnerText, filters);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        sortby.setAdapter(adapter);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


    }

    private void setSearch() {

        if (!isopen) {
            int x = appbar.getRight();
            int y = appbar.getBottom();

            int startRadius = 0;
            int endRadius = (int) Math.hypot(appbar.getWidth(), appbar.getHeight());
            searchview.setVisibility(View.VISIBLE);
            appbar.setVisibility(View.INVISIBLE);

            Animator anim = ViewAnimationUtils.createCircularReveal(searchview, x, y, startRadius, endRadius);
            anim.start();
            isopen = true;

        }

    }

    private void setClose(){
        if(isopen){
            int x = searchview.getRight();
            int y = searchview.getBottom();

            int startRadius = Math.max(appbar.getWidth(), appbar.getHeight());
            int endRadius = 0;

            Animator anim = ViewAnimationUtils.createCircularReveal(searchview, x, y, startRadius, endRadius);
            anim.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    searchview.setVisibility(View.INVISIBLE);
                    appbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            anim.start();
            isopen = false;
        }
    }

    private void setFilter(){
        if(!isfilter){
            //sortandfilterview.setVisibility(View.VISIBLE);
            sortandfilterview.animate().translationX(-0f).setDuration(200).start();
            isfilter = true;
        }else{
            //sortandfilterview.setVisibility(View.GONE);
            sortandfilterview.animate().translationX(2000f).setDuration(200).start();
            isfilter = false;
        }
    }
}
