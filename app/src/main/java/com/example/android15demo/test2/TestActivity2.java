package com.example.android15demo.test2;

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
 */
public class TestActivity2 extends AppCompatActivity {

    private RelativeLayout rlytTest2;
    private EditText edtvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EdgeToEdge.enable(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        rlytTest2 = findViewById(R.id.rlyt_test2);
        edtvTest = findViewById(R.id.et2);


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        ViewGroup content = (ViewGroup) findViewById(android.R.id.content);
        if (content.getChildCount() > 0) {
            View rootView = content.getChildAt(0);
            ViewCompat.setOnApplyWindowInsetsListener(rootView, new OnApplyWindowInsetsListener() {
                @NonNull
                @Override
                public WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets navigationBars = insets.getInsets(WindowInsetsCompat.Type.navigationBars());
                    rlytTest2.setPadding(0, 0, 0, navigationBars.bottom);

                    int navigationBarHeight = insets.getInsets(WindowInsetsCompat.Type.navigationBars()).bottom;
                    int imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom;
                    if(edtvTest != null) {
                        RelativeLayout.LayoutParams p = (RelativeLayout.LayoutParams)edtvTest.getLayoutParams();
                        if(p != null) {
                            if (imeHeight == 0) {
                                p.bottomMargin = 0;

                            } else {
                                p.bottomMargin = imeHeight - navigationBarHeight;

                            }
                            edtvTest.setLayoutParams(p);
                        }
                    }
                    return insets;
                }
            });
        }
    }

    public void onTest1(View v) {
        SoftInputUtils.showSoftInput(edtvTest);
    }

    public void onTest2(View v) {
        SoftInputUtils.hideSoftInput(edtvTest);
    }

}