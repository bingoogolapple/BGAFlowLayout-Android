package cn.bingoogolapple.flowlayout.demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import cn.bingoogolapple.flowlayout.BGAFlowLayout;

public class MainActivity extends AppCompatActivity {
    private String[] mVals = new String[]{"bingo", "googol", "apple", "bingoogolapple", "helloworld"};
    private BGAFlowLayout mFlowLayout;
    private EditText mTagEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTagEt = (EditText) findViewById(R.id.et_main_tag);
        mFlowLayout = (BGAFlowLayout) findViewById(R.id.flowlayout);
        initData();

        mTagEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO) {
                    onClick(null);
                }
                return true;
            }
        });
    }

    public void initData() {
        for (int i = 0; i < mVals.length; i++) {
            mFlowLayout.addView(getLabel(mVals[i]), mFlowLayout.getChildCount() - 1, new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
        }
    }

    private TextView getLabel(String text) {
        TextView label = new TextView(this);
        label.setTextColor(Color.WHITE);
        label.setBackgroundResource(R.drawable.selector_tag);
        label.setGravity(Gravity.CENTER);
        label.setSingleLine(true);
        label.setEllipsize(TextUtils.TruncateAt.END);
        int padding = BGAFlowLayout.dp2px(this, 5);
        label.setPadding(padding, padding, padding, padding);
        label.setText(text);
        return label;
    }

    public void onClick(View view) {
        String tag = mTagEt.getText().toString().trim();
        if (!TextUtils.isEmpty(tag)) {
            mFlowLayout.addView(getLabel(tag), mFlowLayout.getChildCount() - 1, new ViewGroup.MarginLayoutParams(ViewGroup.MarginLayoutParams.WRAP_CONTENT, ViewGroup.MarginLayoutParams.WRAP_CONTENT));
        }
        mTagEt.setText("");
    }
}
