package com.softdesign.devintensive2.ui.behaviors;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;
import com.softdesign.devintensive2.R;
import com.softdesign.devintensive2.utils.UiHelper;

public class UserInfoBehavior<V extends LinearLayout> extends AppBarLayout.ScrollingViewBehavior {
    private final int mMaxAppbarHeight;
    private final int mMinAppbarHeight;
    private final int mMaxUserInfoHeight;
    private final int mMinUserInfoHeight;

    public UserInfoBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.UserInfoBehavior);
        mMinUserInfoHeight = a.getDimensionPixelSize(
                R.styleable.UserInfoBehavior_behavior_min_height, 56);
        a.recycle();
        mMinAppbarHeight = UiHelper.getStatusBarHeight() + UiHelper.getActionBarHeight(); //80dp
        mMaxAppbarHeight = context.getResources().getDimensionPixelSize(R.dimen.profile_image_size_256); //256dp
        mMaxUserInfoHeight = context.getResources().getDimensionPixelSize(R.dimen.user_info_size_112); //112dp
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, final View dependency) {
        float currentFriction =
                UiHelper.currentFriction(mMinAppbarHeight, mMaxAppbarHeight, dependency.getBottom());
        int currentHeight = UiHelper.lerp(mMinUserInfoHeight, mMaxUserInfoHeight, currentFriction);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.height = currentHeight;
        child.setLayoutParams(lp);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }
}
