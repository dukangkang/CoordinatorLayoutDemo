package com.docking.coordinatorlayout.anim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.docking.coordinatorlayout.coordinatorlayout.demo.R;

public class AnimActivity extends Activity {

    ImageView tween_image1 = null;
    Animation anim1 = null;

    ImageView tween_image2 = null;
    Animation anim2 = null;

    ImageView tween_image3 = null;
    Animation anim3 = null;
    ImageView tween_image4 = null;


    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        init();
    }

    private void init() {
        tween_image1 = findViewById(R.id.anim_image1);
        tween_image2 = findViewById(R.id.anim_image2);
        tween_image3 = findViewById(R.id.anim_image3);
        tween_image4 = findViewById(R.id.anim_image4);
        TextView tween_start = findViewById(R.id.anim_start);

        tween_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                tween_image.startAnimation(anim1);
//                tween_image2.startAnimation(anim2);
//                tween_image3.startAnimation(anim3);

                test1();
                test2();
            }
        });
    }

    private void test1() {
        FlipAnimation flipAnimation = new FlipAnimation();
        flipAnimation.setDuration(6000);//设置动画的持续时间
        flipAnimation.setFillAfter(true); //设置动画结束后效果
        flipAnimation.setRepeatCount(Animation.INFINITE);
        flipAnimation.setRepeatMode(Animation.RESTART);
        tween_image1.startAnimation(flipAnimation);
    }

    private void anim2() {
        FlipAnimation flipAnimation = new FlipAnimation();
        flipAnimation.setDuration(10000);//设置动画的持续时间
        flipAnimation.setFillAfter(true); //设置动画结束后效果
        flipAnimation.setRepeatCount(Animation.INFINITE);
        flipAnimation.setRepeatMode(Animation.RESTART);
        tween_image2.startAnimation(flipAnimation);
    }

    private void anim3() {
        FlipAnimation flipAnimation = new FlipAnimation();
        flipAnimation.setDuration(12000);//设置动画的持续时间
        flipAnimation.setFillAfter(true); //设置动画结束后效果
        flipAnimation.setRepeatCount(Animation.INFINITE);
        flipAnimation.setRepeatMode(Animation.RESTART);
        tween_image3.startAnimation(flipAnimation);
    }

    private void anim4() {
        FlipAnimation flipAnimation = new FlipAnimation();
        flipAnimation.setDuration(12000);//设置动画的持续时间
        flipAnimation.setFillAfter(true); //设置动画结束后效果
        flipAnimation.setRepeatCount(Animation.INFINITE);
        flipAnimation.setRepeatMode(Animation.RESTART);
        tween_image4.startAnimation(flipAnimation);
    }


    private void test2() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                anim2();
                anim3();
            }
        }, 3000);

    }



//    private void anim1() {
//        // 加载动画资源
//        anim1 = AnimationUtils.loadAnimation(this,R.anim.tween_anim);
//        //设置动画结束后保留结束状态
//        anim1.setFillAfter(true);
//        anim1.setRepeatMode(Animation.RESTART);
//        anim1.setRepeatCount(Animation.INFINITE);
//    }
//
//    private void anim2() {
//        // 加载动画资源
//        anim2 = AnimationUtils.loadAnimation(this,R.anim.tween_anim2);
//        //设置动画结束后保留结束状态
//        anim2.setFillAfter(true);
//        anim2.setRepeatMode(Animation.RESTART);
//        anim2.setRepeatCount(Animation.INFINITE);
//    }
//
//    private void anim3() {
//        // 加载动画资源
//        anim3 = AnimationUtils.loadAnimation(this,R.anim.tween_anim3);
//        //设置动画结束后保留结束状态
//        anim3.setFillAfter(true);
//        anim3.setRepeatMode(Animation.RESTART);
//        anim3.setRepeatCount(Animation.INFINITE);
//    }

    private void test() {
//        开始为1f,渐变后为0f,最后回到0.7f
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(tween_image1, "alpha", 1f, 0f, 0.7f);
        objectAnimator.start();

//        将图片，从初始0度，顺时针旋转360度，再逆时针旋转360度到0度
        objectAnimator = ObjectAnimator.ofFloat(tween_image1, "rotation", 0f, 360f, 0f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

//        沿着X轴放大两倍效果,然后再回到初始大小
        objectAnimator = ObjectAnimator.ofFloat(tween_image1, "scaleX", 1f, 2f, 1f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

//        动画从0f，沿着X轴向右移动60f，之后再回到0f    (负数为向左)
        objectAnimator = ObjectAnimator.ofFloat(tween_image1, "translationX", 0f, 60f, 0f);
        objectAnimator.setDuration(3000);
        objectAnimator.start();

//        再左右移动同时进行上下移动，移动完毕后，再进行透明度的变换
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(tween_image1,"translationX",0f,60f,0f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(tween_image1,"translationY",0f,60f,0f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(tween_image1,"alpha",1f,0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(objectAnimator1).with(objectAnimator2).before(objectAnimator3);
        animatorSet.setDuration(2000);
        animatorSet.start();

//        alpha	实现渐变效果
//        rotation	实现旋转旋转效果
//        translationX	实现水平移动效果(左或右移动)
//        translationY	实现纵向移动效果(向上或者向下移动)
//        scaleX	实现轴X缩放效果(放大或者缩小)
//        scaleY	实现轴Y缩放效果(放大或者缩小)

    }
}
