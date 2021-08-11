package com.softdesign.devintensive2.ui.behaviors;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.softdesign.devintensive2.R;
import com.softdesign.devintensive2.utils.UiHelper;

public class CustomNestedScrollBehavior extends AppBarLayout.ScrollingViewBehavior {

   private final int mMinAppbarHeight;
   private final int mMaxAppbarHeight;
   private final int mMaxUserInfoHeight;

   public CustomNestedScrollBehavior(Context context, AttributeSet attr){
       super(context,attr);

       mMinAppbarHeight = UiHelper.getStatusBarHeight() + UiHelper.getActionBarHeight();  //80dp
       mMaxAppbarHeight = context.getResources().getDimensionPixelSize(R.dimen.profile_image_size_256); //256dp
       mMaxUserInfoHeight = context.getResources().getDimensionPixelSize(R.dimen.user_info_size_112); //112dp
   }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child,final View dependency) {
        float friction =
                UiHelper.currentFriction(mMinAppbarHeight, mMaxAppbarHeight, dependency.getBottom());
        int offsetY = UiHelper.lerp(mMaxUserInfoHeight/2, mMaxUserInfoHeight, friction);

        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        lp.topMargin = offsetY;
        child.setLayoutParams(lp);
        return super.onDependentViewChanged(parent, child, dependency);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof AppBarLayout;
    }
}
