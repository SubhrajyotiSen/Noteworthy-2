package me.subhrajyoti.noteworthy

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlin.math.sqrt

open class CircularRevealFragment(@LayoutRes layout: Int) : Fragment(layout) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View?,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                view.removeOnLayoutChangeListener(this)
                val cx = requireArguments().getFloat("START_X").toInt()
                val cy = requireArguments().getFloat("START_Y").toInt()
                val width = view.measuredWidth.toFloat()
                val height = view.measuredHeight.toFloat()

                val finalRadius = sqrt(width * width + height * height)
                val anim = ViewAnimationUtils.createCircularReveal(v, cx, cy, 0f, finalRadius)
                anim.duration = 400
                anim.interpolator = FastOutSlowInInterpolator()
                anim.start()

                val colorAnimator = ValueAnimator().apply {
                    setIntValues(
                        ContextCompat.getColor(requireContext(), R.color.colorTransitionStart),
                        ContextCompat.getColor(requireContext(), R.color.colorTransitionEnd)
                    )
                    setEvaluator(ArgbEvaluator())
                    addUpdateListener { valueAnimator -> view.setBackgroundColor((valueAnimator.animatedValue as Int)) }
                    duration = 400
                    start()
                }
                colorAnimator.start()
            }

        })
    }
}
