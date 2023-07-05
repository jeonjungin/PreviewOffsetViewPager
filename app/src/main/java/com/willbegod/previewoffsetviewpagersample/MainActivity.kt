package com.willbegod.previewoffsetviewpagersample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.viewpager2.widget.ViewPager2
import com.willbegod.previewoffsetviewpagersample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val slideAdapter = SlideAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val states = slideItems.map {
            makeItemState(it.first, it.second)
        }

        initViewPager()
        submitToAdapter(states)
    }

    private fun initViewPager() {
        binding.viewPager.setAdapter(slideAdapter)
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                binding.indexText = "index: $position"
            }
        })
    }

    private fun submitToAdapter(list: List<SlideItemState>) {
        slideAdapter.submitList(list)
    }

    private fun makeItemState(text: String, @ColorInt color: Int): SlideItemState {
        return SlideItemState(
            text = text,
            color = color,
            onClick = {
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
            }
        )
    }
}