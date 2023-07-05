package com.willbegod.previewoffset

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.willbegod.previewoffset.databinding.LayoutPreviewOffsetViewPagerBinding


/**
 * 왼쪽과 오른쪽 아이템이 보이는 ViewPager
 *
 * @see PreviewOffsetViewPager.itemHorizontalMargin ViewPager의 왼쪽, 오른쪽 끝과 중앙 아이템 사이의 마진 값
 * @see PreviewOffsetViewPager.previewItemWidth 스크롤 정지 상태의 왼쪽, 오른쪽 아이템 너비 값
 *
 * */
class PreviewOffsetViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private companion object {
        private const val defaultPreviewItemWidth = 50
        private const val defaultItemHorizontalMargin = 100
    }

    private lateinit var binding: LayoutPreviewOffsetViewPagerBinding

    private var previewItemWidth = defaultPreviewItemWidth
    private var itemHorizontalMargin = defaultItemHorizontalMargin

    init {
        if (isInEditMode) {
            inflate(context, R.layout.layout_preview_offset_view_pager, this)
        } else {
            binding = LayoutPreviewOffsetViewPagerBinding.inflate(LayoutInflater.from(context), this, true)

            initAttrs(attrs)
            initViewPager(previewItemWidth, itemHorizontalMargin)
        }
    }

    private fun initAttrs(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.PreviewOffsetSlideView).apply {
                previewItemWidth = getDimensionPixelSize(R.styleable.PreviewOffsetSlideView_previewItemWidth, defaultPreviewItemWidth)
                itemHorizontalMargin = getDimensionPixelSize(R.styleable.PreviewOffsetSlideView_itemMarginHorizontal, defaultItemHorizontalMargin)
                recycle()
            }
        }
    }

    private fun initViewPager(previewWidth: Int, itemMargin: Int) {
        val decoMargin = previewWidth + itemMargin
        val pageTransX = decoMargin + previewWidth
        val decoration = PageDecoration(decoMargin)

        binding.vp.also {
            it.offscreenPageLimit = 1
            it.addItemDecoration(decoration)
            it.setPageTransformer { page, position ->
                page.translationX = position * - pageTransX
            }
        }
    }

    fun <VH: RecyclerView.ViewHolder> setAdapter(adapter: RecyclerView.Adapter<VH>) {
        binding.vp.adapter = adapter
    }

    fun setCurrentItem(pos: Int, smooth: Boolean = false) {
        binding.vp.setCurrentItem(pos, smooth)
    }

    fun getCurrentItem() = binding.vp.currentItem

    fun registerOnPageChangeCallback(callback: ViewPager2.OnPageChangeCallback) {
        binding.vp.registerOnPageChangeCallback(callback)
    }

    private class PageDecoration(private val margin: Int): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            outRect.left = margin
            outRect.right = margin
        }
    }
}