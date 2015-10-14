package com.cliff.libcoverflow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;

import java.util.Random;

public class CoverFlowExt extends FancyCoverFlow {
    public static final int INVALID_POINTER_ID = -1;
    //当前活动触目点Id
    private int mActivePointerId = INVALID_POINTER_ID;
    //最大旋转角度
    private static final double DISORDERED_MAX_ROTATION_RADIANS = Math.PI / 64;
    private final Random mRandom = new Random();
    private final Rect boundsRect = new Rect();
    private final Rect childRect = new Rect();
    private final Matrix mMatrix = new Matrix();

    private final Rect flipRect = new Rect();

    //TODO: determine max dynamically based on device speed
    private GestureDetector mGestureDetector;
    private int mFlingSlop;


    //记录按下触目点位置
    private float mLastTouchX;
    private float mLastTouchY;
    private View mTopCard;
    private int mCardIndex;
    private int mTouchSlop;
    private boolean mDragging;

    public CoverFlowExt(Context context) {
        super(context);
        setGravity(Gravity.CENTER);
        init();
    }

    public CoverFlowExt(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CoverFlowExt(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private enum Model {
        Horizon,
        Vertical,
        None
    }

    //手势监听获取滑动模式
    private Model model = Model.None;


    private void init() {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        //获得允许执行一个fling手势动作的最小速度值
        mFlingSlop = viewConfiguration.getScaledMinimumFlingVelocity();
        //获得能够进行手势滑动的距离
        mTouchSlop = viewConfiguration.getScaledTouchSlop();

//        mGestureDetector = new GestureDetector(getContext(), new GestureListener());

    }


    /**
     * 获取view的旋转角度
     *
     * @return
     */
    private float getDisorderedRotation() {
//        return (float) Math.toDegrees(mRandom.nextGaussian() * DISORDERED_MAX_ROTATION_RADIANS);
        return 0.0f;
    }

    private float mDownX, mDownY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        //判断是否满足切换
//        if (mGestureDetector.onTouchEvent(event)) {
//            return true;
//        }
        final float x, y;
        final float dx, dy;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (model == Model.None) {
                    model = Math.abs(mDownX - event.getX()) > Math.abs(mDownY - event.getY()) ? Model.Horizon : Model.Vertical;
                    if (model == Model.Vertical) {
                        mTopCard = getSelectedView();
                        mCardIndex = getSelectedItemPosition();
                        mTopCard.getHitRect(childRect);
                        x = event.getX();
                        y = event.getY();
                        if (!childRect.contains((int) x, (int) y)) {
                            reset();
                            model = Model.Horizon;
                            return super.onTouchEvent(event);
                        }
                        mLastTouchX = x;
                        mLastTouchY = y;

                        float[] points = new float[]{x, y};// (y - mTopCard.getTop())};
                        mTopCard.getMatrix().invert(mMatrix);
                        mMatrix.mapPoints(points);
                        mTopCard.setPivotX(points[0]);
                        mTopCard.setPivotY(points[1]);
                    }
                } else if (model == Model.Vertical) {
                    x = event.getX();
                    y = event.getY();


                    dx = x - mLastTouchX;
                    dy = y - mLastTouchY;

                    if (Math.abs(dx) > mTouchSlop || Math.abs(dy) > mTouchSlop) {
                        mDragging = true;
                    }

                    if (!mDragging) {
                        return true;
                    }
                    mTopCard.setTranslationX(mTopCard.getTranslationX() + dx);
                    mTopCard.setTranslationY(mTopCard.getTranslationY() + dy);
//                    mTopCard.setRotation(40 * mTopCard.getTranslationX() / (getWidth() / 2.f));
                    mLastTouchX = x;
                    mLastTouchY = y;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:

                if (model == Model.Vertical) {
                    if (!mDragging) {
                        return true;
                    }
                    mDragging = false;
                    mActivePointerId = INVALID_POINTER_ID;
                    //创建复位动画
                    moveBack();
                } else if (model == Model.Horizon) {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            reset();
                        }
                    }, 800);
                }
                break;
        }
        if (model == Model.Horizon)
            return super.onTouchEvent(event);
        else
            return true;
    }

    /**
     * 恢复原始位置
     */
    private void moveBack() {
        if (mTopCard != null) {
            ValueAnimator animator = ObjectAnimator.ofPropertyValuesHolder(mTopCard,
                    PropertyValuesHolder.ofFloat("translationX", 0),
                    PropertyValuesHolder.ofFloat("translationY", 0),
//                  PropertyValuesHolder.ofFloat("rotation", getDisorderedRotation()),
                    PropertyValuesHolder.ofFloat("pivotX", mTopCard.getWidth() / 2.f),
                    PropertyValuesHolder.ofFloat("pivotY", mTopCard.getHeight() / 2.f)
            ).setDuration(250);
            animator.setInterpolator(new AccelerateInterpolator());
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (moveBackListener != null)
                        moveBackListener.onMoveback(mTopCard, mCardIndex);
                    reset();
                }
            });
            animator.start();
        }
    }

    public void removeView(final View view, int position) {
        Rect rect = new Rect();
        view.getHitRect(rect);
        //移除动画
        view.animate()
                .setDuration(250)
                .alpha(.75f)
                .setInterpolator(new LinearInterpolator())
                .y(rect.top - view.getHeight())
                .rotation(getDisorderedRotation())//Math.copySign(45, velocityX))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        removeViewInLayout(view);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        onAnimationEnd(animation);
                    }
                });

    }

    private OnMoveBackListener moveBackListener;

    public void setMoveBackListener(OnMoveBackListener moveBackListener) {
        this.moveBackListener = moveBackListener;
    }

    /**
     * 手势事件监听
     */
    public interface OnMoveBackListener {
        public void onMoveback(View view, int position);
    }

    private class GestureListener extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("Fling", "Fling with " + velocityX + ", " + velocityY);
            final View topCard = mTopCard;
            float dx = e2.getX() - e1.getX();
            if (Math.abs(dx) > mTouchSlop &&
                    Math.abs(velocityX) > Math.abs(velocityY) &&
                    Math.abs(velocityX) > mFlingSlop * 3) {
                float targetX = topCard.getX();
                float targetY = topCard.getY();
                long duration = 0;
                //扩充容器边界
                boundsRect.set(0 - topCard.getWidth() - 100,
                        0 - topCard.getHeight() - 100,
                        getWidth() + 100, getHeight() + 100);
                //计算控件动画结束点
                while (boundsRect.contains((int) targetX, (int) targetY)) {
                    targetX += velocityX / 10;
                    targetY += velocityY / 10;
                    duration += 100;
                }

                duration = Math.min(500, duration);

                mTopCard = getChildAt(getChildCount() - 2);

                if (mTopCard != null)
                    mTopCard.setLayerType(LAYER_TYPE_HARDWARE, null);

                //移除动画
                topCard.animate()
                        .setDuration(duration)
                        .alpha(.75f)
                        .setInterpolator(new LinearInterpolator())
                        .x(targetX)
                        .y(targetY)
                        .rotation(getDisorderedRotation())//Math.copySign(45, velocityX))
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                removeViewInLayout(topCard);
                                reset();
                            }

                            @Override
                            public void onAnimationCancel(Animator animation) {
                                onAnimationEnd(animation);
                            }
                        });
                return true;
            } else
                return false;
        }
    }

    private void reset() {
        model = Model.None;
        mTopCard = null;
        mDownX = mDownY = 0;
        mCardIndex = -1;
    }
}
