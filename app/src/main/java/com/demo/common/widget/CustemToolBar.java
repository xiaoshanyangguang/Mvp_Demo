package com.demo.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.R;

/**
 * @创建者 Piper
 * @创建时间 2016/6/23 0:01
 * @描述 TODO
 */
public class CustemToolBar extends Toolbar{
    private Context mContext;
    private ImageView btnLeft;
    private TextView titleText;

    private Button btnRight;
    private RelativeLayout layoutBg;
    public CustemToolBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }


    public CustemToolBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();

    }

    private void initView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_toolbar, this);
        this.titleText = (TextView) view.findViewById(R.id.title_text);
        this.btnLeft = (ImageView) view.findViewById(R.id.title_left_btn);
        this.btnRight = (Button) view.findViewById(R.id.title_right_btn);
        this.layoutBg = (RelativeLayout) view.findViewById(R.id.title_bg_rl);

    }

    public void setBtnLeft(int icon) {
        Drawable drawable = mContext.getResources().getDrawable(icon);
        drawable.setBounds(0,0,64,64);
        btnLeft.setImageDrawable(drawable);
    }

    public void setBtnRight(int icon) {
        Drawable drawable = mContext.getResources().getDrawable(icon);
        drawable.setBounds(0, 0, 64, 64);
        btnRight.setBackground(drawable);
    }
    public void setBtnLeftInvisible() {
        btnLeft.setVisibility(View.INVISIBLE);
    }

    public void setBtnRightInvisible() {
        btnRight.setVisibility(View.VISIBLE);
    }


    /**
     * @param listener
     * 右边返回按钮点击事件
     */
    public void setBtnRightClick(OnClickListener listener)
    {
        if (btnRight!=null)
            btnRight.setOnClickListener(listener);
    }

    /**
     * @param color
     * 设置背景颜色
     */
    public void setBgColor(int color)
    {
        layoutBg.setBackgroundColor(color);
    }

    /**
     * @param left  按钮显示状态
     * @param right
     */
    public void setCommonVisiable(int left, int right)
    {
        btnLeft.setVisibility(left);
        btnRight.setVisibility(right);
    }
    /**
     * @param color  标题颜色
     */
    public void setTitleColor(int color)
    {
        titleText.setTextColor(color);
    }
    /**
     * @param txtStr  标题内容
     */
    public void setTitleText(int txtStr)
    {
        titleText.setText(getResources().getString(txtStr));
    }
    /**
     * @param txtStr  标题内容
     */
    public void setTitleText(String txtStr)
    {
        titleText.setText(txtStr);
    }
    /**
     * @param txtStr  右边按钮内容
     */
    public void setRightText(String txtStr)
    {
        btnRight.setVisibility(VISIBLE);
        btnRight.setText(txtStr);
    }

    public void destoryView()
    {


        if (btnRight != null)
        {
            btnRight.setText(null);
        }
        if (titleText != null)
        {
            titleText.setBackground(null);
        }
        if (btnLeft != null)
        {
            btnLeft.setImageDrawable(null);
        }
    }
}
