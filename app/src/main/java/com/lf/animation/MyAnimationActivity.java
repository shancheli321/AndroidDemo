package com.lf.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.lf.main.R;


/*

    Animation一般动画就是我们前面学的帧动画和补间动画！Animator则是本节要讲的属性动画！

     1. 补间动画 Interpolator

        用来控制动画的变化速度，可以理解成动画渲染器，当然我们也可以自己实现Interpolator 接口，
        自行来控制动画的变化速度，而Android中已经为我们提供了五个可供选择的实现类：

            LinearInterpolator：动画以均匀的速度改变
            AccelerateInterpolator：在动画开始的地方改变速度较慢，然后开始加速
            AccelerateDecelerateInterpolator：在动画开始、结束的地方改变速度较慢，中间时加速
            CycleInterpolator：动画循环播放特定次数，变化速度按正弦曲线改变： Math.sin(2 * mCycles * Math.PI * input)
            DecelerateInterpolator：在动画开始的地方改变速度较快，然后开始减速
            AnticipateInterpolator：反向，先向相反方向改变一段再加速播放
            AnticipateOvershootInterpolator：开始的时候向后然后向前甩一定值后返回最后的值
            BounceInterpolator： 跳跃，快到目的值时值会跳跃，如目的值100，后面的值可能依次为85，77，70，80，90，100
            OvershottInterpolator：回弹，最后超出目的值然后缓慢改变到目的值

    2. 属性动画

 */


public class MyAnimationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_alpha;
    private Button btn_scale;
    private Button btn_tran;
    private Button btn_rotate;
    private Button btn_set;
    private Button btn_property;
    private Button btn_property_build;

    private ImageView img_show;
    private Animation animation = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animation);

        bindViews();

        // 动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    private void bindViews() {
        btn_alpha = (Button) findViewById(R.id.btn_alpha);
        btn_scale = (Button) findViewById(R.id.btn_scale);
        btn_tran = (Button) findViewById(R.id.btn_tran);
        btn_rotate = (Button) findViewById(R.id.btn_rotate);
        btn_set = (Button) findViewById(R.id.btn_set);
        img_show = (ImageView) findViewById(R.id.img_show);
        btn_property = (Button)findViewById(R.id.btn_property);
        btn_property_build = findViewById(R.id.btn_property_build);

        btn_alpha.setOnClickListener(this);
        btn_scale.setOnClickListener(this);
        btn_tran.setOnClickListener(this);
        btn_rotate.setOnClickListener(this);
        btn_set.setOnClickListener(this);
        btn_property.setOnClickListener(this);
        btn_property_build.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_alpha:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_alpha);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_scale:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_scale);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_tran:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_translate);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_rotate:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_rotate);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_set:
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_set);
                img_show.startAnimation(animation);
                break;
            case  R.id.btn_property:
                setPropertyAnimator();
            case R.id.btn_property_build:
                setPropertyBuilderAnimator();
        }
    }


    /**
     * 设置view 从底部弹出
     */
    private void setAnimation() {

        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为500ms
        final TranslateAnimation ctrlAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
        ctrlAnimation.setDuration(500l);     //设置动画的过渡时间
        img_show.postDelayed(new Runnable() {
            @Override
            public void run() {
                img_show.setVisibility(View.VISIBLE);
                img_show.startAnimation(ctrlAnimation);
            }
        }, 2000);
    }


    private void setPropertyAnimator() {

        // 放大三倍再还原
        ObjectAnimator animator = ObjectAnimator.ofFloat(btn_alpha, "scaleY", 1f, 3f, 1f);
        animator.setDuration(5000);
        animator.start();


        // 将btn_rotate先向左移出屏幕，然后再移动回来
        float curTranslationX = btn_rotate.getTranslationX();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn_rotate, "translationX", curTranslationX, -500f, curTranslationX);
        animator2.setDuration(5000);
        animator2.start();

        // 旋转
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(btn_set, "rotation", 0f, 360f);
        animator3.setDuration(5000);
        animator3.start();

    }

    // extView先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作
    private void setPropertyBuilderAnimator() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(btn_alpha, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(btn_alpha, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(btn_alpha, "alpha", 1f, 0f, 1f);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();


        // 设置动画监听
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {


            }
        });


        btn_property.animate().x(500).y(500).setDuration(5000);

    }
}