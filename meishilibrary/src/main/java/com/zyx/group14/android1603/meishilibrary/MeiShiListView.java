package com.zyx.group14.android1603.meishilibrary;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by yixin on 2016/9/5.
 */
public class MeiShiListView extends ListView {
    public static final int SCROLL_DOWN = 1;
    public static final int SCROLL_UP = 2;

    private Context mContext;
    private MeiShiListViewCallBack mCallBack;
    private View header;
    private ImageView loadBgIv;
    private ImageView panIv;
    private TextView hintTv;
    private TextView lastTimeTv;
    private RelativeLayout rl;
    private int refreshHeight;
    private boolean isHeaderVisible = false;
    private boolean isOnRefreshing = false;
    private ClipDrawable pullProgress;
    private Calendar calendar;
    private long lastLoadTime;

    private int lastScrollState = 0;
    private int lastFirstVisibleItem = 0;
    private int lastFirstGetTop = 0;
    private int headerHeight;

    public MeiShiListView(Context context) {
        this(context,null);
    }

    public MeiShiListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MeiShiListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        refreshHeight = ScreenInfo.dpToPx(context,60);
        calendar = Calendar.getInstance();
        lastLoadTime = calendar.getTimeInMillis();
        initView();
        setOnListener();
    }

    public void setCallBack(MeiShiListViewCallBack callBack){
        mCallBack = callBack;
    }

    private void initView() {
        header = LayoutInflater.from(mContext).inflate(R.layout.meishi_header,this,false);
        addHeaderView(header);
        loadBgIv = (ImageView) header.findViewById(R.id.meishi_load_bg_iv);
        panIv = (ImageView) header.findViewById(R.id.refresh_iv);
        pullProgress = (ClipDrawable) panIv.getBackground();
        hintTv = (TextView) header.findViewById(R.id.refresh_top_tv);
        lastTimeTv = (TextView) header.findViewById(R.id.refresh_bottom_tv);
        rl = (RelativeLayout) header.findViewById(R.id.meishi_list_view_rl);
    }
    private void setOnListener() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        if(isListViewReachTop()){
                            setHeaderVisible();
                            loadBgIv.setBackgroundColor(Color.parseColor("#aaaaaa"));
                            panIv.setImageResource(R.drawable.refreshpot);
                            hintTv.setText(R.string.refresh_top_tv_1);
                            calendar = Calendar.getInstance();
                            long time = calendar.getTimeInMillis() - lastLoadTime;
                            lastTimeTv.setText("上次更新:" + Time.timeInMillisSwitch(time));
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if(isHeaderVisible){
                            if(header.getBottom() < refreshHeight){
                                smoothScrollBy(header.getBottom(),1000);
                            }else  {
                                smoothScrollBy(header.getBottom() - refreshHeight,500);
                                if(!isOnRefreshing){
                                    loadBgIv.setBackgroundColor(Color.parseColor("#ffffff"));
                                    pullProgress.setLevel(0);
                                    panIv.setImageResource(R.drawable.pan_anim);
                                    AnimationDrawable animationDrawable = (AnimationDrawable) panIv.getDrawable();
                                    animationDrawable.start();
                                    hintTv.setText(R.string.refresh_top_tv_3);
                                    calendar = Calendar.getInstance();
                                    lastLoadTime = calendar.getTimeInMillis();
                                    isOnRefreshing = true;

                                    mCallBack.startRefresh();
                                }
                            }
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if(isHeaderVisible&&!isOnRefreshing){
                            if(header.getBottom() <= refreshHeight) {
                                pullProgress.setLevel(header.getBottom() * 10000 / refreshHeight);
                                hintTv.setText(R.string.refresh_top_tv_1);
                            }else {
                                pullProgress.setLevel(10000);
                                hintTv.setText(R.string.refresh_top_tv_2);
                            }
                        }
                        break;
                    default:break;
                }
                return false;
            }

        });
        setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {}

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastScroll(firstVisibleItem, visibleItemCount, totalItemCount);
                if(lastScrollState == SCROLL_DOWN && !isOnRefreshing) {
                    rl.setVisibility(GONE);
                    isHeaderVisible = false;
                }
            }
        });
    }

    private void setHeaderVisible() {
        if(!isHeaderVisible){
            setSelection(1);
        }
        rl.setVisibility(VISIBLE);
        headerHeight = rl.getMeasuredHeight();
        isHeaderVisible = true;
    }

    private boolean isListViewReachTop() {
        boolean result = false;
        if(!isHeaderVisible && getChildCount() !=0 && getFirstVisiblePosition() == 0 && getChildAt(0).getTop() == 0) {
            result = true;
        }
            return result;
    }
    private void lastScroll(int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(getChildCount()!=0) {
//            Log.i("zyx", firstVisibleItem + "  " + totalItemCount + "  " + visibleItemCount);
            if (firstVisibleItem > lastFirstVisibleItem && lastFirstVisibleItem!=0) {
                lastScrollState = SCROLL_DOWN;
            } else if (firstVisibleItem < lastFirstVisibleItem) {
                lastScrollState = SCROLL_UP;
            } else {
                int getTop = getChildAt(0).getTop();
                if (getTop < lastFirstGetTop && firstVisibleItem != 0) {
                    lastScrollState = SCROLL_DOWN;
                } else if (getTop > lastFirstGetTop) {
                    lastScrollState = SCROLL_UP;
                }
            }
            lastFirstGetTop = getChildAt(0).getTop();
            lastFirstVisibleItem = firstVisibleItem;
        }
    }
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            rl.setVisibility(GONE);
            if(getFirstVisiblePosition()==0) {
                setSelection(0);
            }
            isHeaderVisible = false;
            isOnRefreshing = false;
        }
    };
    public void refreshComplete(){
        smoothScrollBy(header.getBottom(),500);
        mHandler.sendEmptyMessageDelayed(0,500);

    }
}

