package com.example.android15demo.test3;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.android15demo.R;
import com.example.android15demo.utils.SoftInputUtils;


/**
 * 测试softInputMode
 * SOFT_INPUT_ADJUST_UNSPECIFIED
 * 不指定调整方式，系统自行决定使用哪种调整方式
 *
 * SOFT_INPUT_ADJUST_RESIZE
 * 调整方式为布局需要重新计算大小适配当前可见区域
 *
 * SOFT_INPUT_ADJUST_PAN
 * 调整方式为布局需要整体移动
 *
 * SOFT_INPUT_ADJUST_NOTHING
 * 不做任何操作
 *
 * 在布局文件中不能使用下面的属性，使用了会导致偏离底部的距离不准确，会过高
 *     android:layout_gravity="center_vertical"
 */
public class TestActivity3 extends AppCompatActivity {

    private RelativeLayout rlytTest2;
    private EditText edtvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        rlytTest2 = findViewById(R.id.rlyt_test2);
        edtvTest = findViewById(R.id.et2);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initEdgeToEdgePadding();
    }

    private void initEdgeToEdgePadding() {
        //将APP内容调整为底部导航栏或者底部白色横条之上，避免底部内容被导航条遮挡
        //app原先的BaseTitleView或者statusbar控件已处理顶部状态栏间距，此处不再做处理
        ViewCompat.setOnApplyWindowInsetsListener(((ViewGroup) findViewById(android.R.id.content)).getChildAt(0), new OnApplyWindowInsetsListener() {
            @NonNull
            @Override
            public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat windowInsets) {

                Insets insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars());
                Insets insets1 = windowInsets.getInsets(WindowInsetsCompat.Type.navigationBars());

                Insets imeInsets = windowInsets.getInsets(WindowInsetsCompat.Type.ime());
                int bottom = Math.max(insets.bottom, imeInsets.bottom);
                if (needBottomPadding()) {


                    // Apply the insets as a margin to the view. This solution sets
                    // only the bottom, left, and right dimensions, but you can apply whichever
                    // insets are appropriate to your layout. You can also update the view padding
                    // if that's more appropriate.
//                    v.updateLayoutParams<ViewGroup.MarginLayoutParams> {
//                        //leftMargin = insets.left
//                        bottomMargin = bottom
//                        //rightMargin = insets.right
//                    }
                    ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams)v.getLayoutParams();
                    p.bottomMargin = bottom;
                    v.setLayoutParams(p);
//                    v.setPadding(0, 0, 0, bottom);
                }
//                if (-bottom != bottomPadding.value) {
//                    bottomPadding.postValue(-bottom)
//                }
//
//                imeHeight.postValue(imeInsets.bottom)
                // Return CONSUMED if you don't want want the window insets to keep passing
                // down to descendant views.
                return WindowInsetsCompat.CONSUMED;
            }
        });
    }

    private boolean needBottomPadding() {
        return true;
    }

    public void onTest1(View v) {
        SoftInputUtils.showSoftInput(edtvTest);
    }

    public void onTest2(View v) {
        SoftInputUtils.hideSoftInput(edtvTest);
    }

}

