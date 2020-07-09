package com.example.designtask_sca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private OnboardingAdapter mOnboardingAdapter;
    private LinearLayout layoutOnBoardingIndicator;
    private ViewPager2 mOnBoardingViewPager;
    private MaterialButton nextButton;
    private MaterialButton iconButton;
    private TextView skipText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setOnBoardingItems();

        mOnBoardingViewPager = findViewById(R.id.onBoardingViewPager);
        layoutOnBoardingIndicator = findViewById(R.id.layoutOnBoardingIndicators);
        mOnBoardingViewPager.setAdapter(mOnboardingAdapter);
        nextButton = findViewById(R.id.nextButton);
        iconButton = findViewById(R.id.icon_button);

        skipText = findViewById(R.id.skip_text);

        setOnBoardingIndicators();
        setCurrentOnBoardingIndicator(0);

        mOnBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnBoardingIndicator(position);
            }
        });

    }

    private void setOnBoardingItems(){
        List<OnboardingItem> onboardingItems = new ArrayList<>();

        OnboardingItem screenOne = new OnboardingItem();
        screenOne.setTitle(getString(R.string.screen_one_title));
        screenOne.setDescription(getString(R.string.screen_one_description));
        screenOne.setImage(R.drawable.hugo1);

        OnboardingItem screenTwo = new OnboardingItem();
        screenTwo.setTitle(getString(R.string.screen_two_title));
        screenTwo.setDescription(getString(R.string.screen_two_description));
        screenTwo.setImage(R.drawable.hugo2);

        OnboardingItem screenThree = new OnboardingItem();
        screenThree.setTitle(getString(R.string.screen_three_title));
        screenThree.setDescription(getString(R.string.screen_three_description));
        screenThree.setImage(R.drawable.hugo3);

        onboardingItems.add(screenOne);
        onboardingItems.add(screenTwo);
        onboardingItems.add(screenThree);

        mOnboardingAdapter = new OnboardingAdapter(onboardingItems);
    }

    private void setOnBoardingIndicators(){
        ImageView [] indicators = new ImageView[mOnboardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,0,0,0);
        for(int i = 0; i < indicators.length; i++){
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicator.addView(indicators[i]);
        }

    }

    private void setCurrentOnBoardingIndicator(int index){
        int childCount = layoutOnBoardingIndicator.getChildCount();
        for(int i = 0 ; i < childCount; i++){
            ImageView imageView = (ImageView) layoutOnBoardingIndicator.getChildAt(i);
            if(i == index){
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_active)
                );
            }else{
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.onboarding_indicator_inactive));
            }
        }

        if(index == mOnboardingAdapter.getItemCount() - 1){
            nextButton.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.button_colour));
            nextButton.setText(R.string.get_started_text);
            nextButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            nextButton.setTextSize(15);
            nextButton.setTextColor(Color.WHITE);

            iconButton.setVisibility(View.INVISIBLE);

            skipText.setVisibility(View.INVISIBLE);
        }
    }

    public void buttonClick(View view){
        if(mOnBoardingViewPager.getCurrentItem() + 1 < mOnboardingAdapter.getItemCount()) {
            mOnBoardingViewPager.setCurrentItem(mOnBoardingViewPager.getCurrentItem() + 1);
        }else {
            nextButton.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }

    }



}