package com.example.designtask_sca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OnboardingAdapter extends RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder> {

    private List<OnboardingItem> mOnboardingItemList;

    public OnboardingAdapter(List<OnboardingItem> onboardingItemList) {
        mOnboardingItemList = onboardingItemList;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnboardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        holder.setOnBoardingData(mOnboardingItemList.get(position));
    }


    @Override
    public int getItemCount() {
        return mOnboardingItemList.size();
    }

    class OnboardingViewHolder extends RecyclerView.ViewHolder{

         private TextView textTitle;
         private TextView textDescription;
         private ImageView imageOnBoarding;

         public OnboardingViewHolder(@NonNull View itemView) {
             super(itemView);
             textTitle = itemView.findViewById(R.id.title_text);
             textDescription = itemView.findViewById(R.id.description_text);
             imageOnBoarding = itemView.findViewById(R.id.imageView);
         }

         void setOnBoardingData(OnboardingItem onboardingItem){
             textTitle.setText(onboardingItem.getTitle());
             textDescription.setText(onboardingItem.getDescription());
             imageOnBoarding.setImageResource(onboardingItem.getImage());

         }
     }
}
