package cn.bingoogolapple.flowlayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/6/24 11:21
 * 描述:流式布局
 */
public class BGAFlowLayout extends ViewGroup {
    private List<Row> mRows = new ArrayList<>();
    private int mHorizontalChildGap;
    private int mVerticalChildGap;
    private boolean mIsDistributionWhiteSpacing = true;

    public BGAFlowLayout(Context context) {
        this(context, null);
    }

    public BGAFlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BGAFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDefaultAttrs(context);
        initCustomAttrs(context, attrs);
    }

    private void initDefaultAttrs(Context context) {
        mHorizontalChildGap = dp2px(context, 10);
        mVerticalChildGap = dp2px(context, 10);
    }

    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BGAFlowLayout);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.BGAFlowLayout_fl_horizontalChildGap) {
            /**
             * getDimension和getDimensionPixelOffset的功能差不多,都是获取某个dimen的值,如果是dp或sp的单位,将其乘以density,如果是px,则不乘;两个函数的区别是一个返回float,一个返回int. getDimensionPixelSize则不管写的是dp还是sp还是px,都会乘以denstiy.
             */
            mHorizontalChildGap = typedArray.getDimensionPixelOffset(attr, mHorizontalChildGap);
        } else if (attr == R.styleable.BGAFlowLayout_fl_verticalChildGap) {
            mVerticalChildGap = typedArray.getDimensionPixelOffset(attr, mVerticalChildGap);
        } else if (attr == R.styleable.BGAFlowLayout_fl_isDistributionWhiteSpacing) {
            mIsDistributionWhiteSpacing = typedArray.getBoolean(attr, mIsDistributionWhiteSpacing);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        /**
         * 1.EXACTLY:100dp,match_parent
         * 2.AT_MOST:wrap_content
         * 3.UNSPCIFIED:子控件想要多大就多大，很少见（ScrollView）
         */
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        mRows.clear();
        Row row = new Row(sizeWidth);

        View child;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (!row.addChild(child)) {
                mRows.add(row);
                row = new Row(sizeWidth);
                row.addChild(child);
            }
        }

        // 添加最后一行
        if (!mRows.contains(row)) {
            mRows.add(row);
        }

        int height = 0;
        int rowCount = mRows.size();
        for (int i = 0; i < rowCount; i++) {
            height += mRows.get(i).mHeight;
            if (i != rowCount - 1) {
                height += mVerticalChildGap;
            }
        }
        setMeasuredDimension(sizeWidth, modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height + getPaddingTop() + getPaddingBottom());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int rowCount = mRows.size();
        int top = getPaddingTop();
        Row row;
        for (int i = 0; i < rowCount; i++) {
            row = mRows.get(i);
            if (mIsDistributionWhiteSpacing && i != rowCount - 1) {
                row.layout(true, top);
            } else {
                row.layout(false, top);
            }
            top += row.mHeight + mVerticalChildGap;
        }
    }

    private class Row {
        private List<View> mViews = new ArrayList<>();
        private int mWidth;
        private int mNewWidth;
        private int mHeight;
        private int mMaxWidth;

        public Row(int sizeWidth) {
            mMaxWidth = sizeWidth - getPaddingLeft() - getPaddingRight();
        }

        public boolean addChild(View child) {
            if (isOutOfMaxWidth(child.getMeasuredWidth())) {
                return false;
            } else {
                mViews.add(child);
                mWidth = mNewWidth;

                int childHeight = child.getMeasuredHeight();
                mHeight = mHeight < childHeight ? childHeight : mHeight;
                return true;
            }
        }

        private boolean isOutOfMaxWidth(int childWidth) {
            if (mViews.size() == 0) {
                mNewWidth = mWidth + childWidth;
            } else {
                mNewWidth = mWidth + mHorizontalChildGap + childWidth;
            }
            return mNewWidth > mMaxWidth;
        }

        /**
         * @param isNeedSplit 是否需要平均分配空白区域给每一个子孩子
         * @param top
         */
        public void layout(boolean isNeedSplit, int top) {
            if (mViews.size() == 0) {
                return;
            }

            int left = getPaddingLeft();
            int count = mViews.size();
            int splitWidth = (mMaxWidth - mWidth) / count;
            View view;
            for (int i = 0; i < count; i++) {
                view = mViews.get(i);
                int childWidth = view.getMeasuredWidth();
                int childHeight = view.getMeasuredHeight();
                if (isNeedSplit) {
                    childWidth = childWidth + splitWidth;
                    view.getLayoutParams().width = childWidth;
                    if (splitWidth > 0) {
                        /**
                         * 1.EXACTLY:100dp,match_parent
                         * 2.AT_MOST:wrap_content
                         * 3.UNSPCIFIED:子控件想要多大就多大，很少见（ScrollView）
                         */
                        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidth, MeasureSpec.EXACTLY);
                        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeight, MeasureSpec.EXACTLY);
                        view.measure(widthMeasureSpec, heightMeasureSpec);
                    }
                }

                int topOffset = (int) ((mHeight - childHeight) / 2.0 + 0.5);
                view.layout(left, top + topOffset, left + childWidth, top + topOffset + childHeight);

                left += childWidth + mHorizontalChildGap;
            }
        }
    }

    public static int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }
}