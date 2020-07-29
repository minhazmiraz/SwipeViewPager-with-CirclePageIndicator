package com.example.swipeviewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Model> models;
    private Adapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Integer[] colors;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        models = new ArrayList<>();
        models.add(new Model(R.drawable.brochure, "Brochure", "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"));
        models.add(new Model(R.drawable.sticker, "Sticker", "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"));
        models.add(new Model(R.drawable.poster, "Poster", "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"));
        models.add(new Model(R.drawable.namecard, "NameCard", "Lorem ipsum Lorem ipsum Lorem ipsum Lorem ipsum"));

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        tabLayout = findViewById(R.id.pageindicator);
        tabLayout.setupWithViewPager(viewPager, true);

        colors = new Integer[]{
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
        };

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < adapter.getCount() -1 && position < colors.length - 1){
                    viewPager.setBackgroundColor(
                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    colors[position],
                                    colors[position+1]
                            )
                    );
                } else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}