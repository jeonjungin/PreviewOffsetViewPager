# PreviewOffsetViewPager

왼쪽, 오른쪽 아이템을 미리볼 수 있는 ViewPager 입니다.
ViewPager2 를 inflate 하여 작성되어, 동일하게 사용 가능합니다.

또한, 각 아이템 사이의 마진값과 미리볼 수 있는 아이템의 너비를 xml 상에서 간편하게 지정할 수 있습니다.

<img src="https://github.com/jeonjungin/ReadMoreView/assets/40448001/45e4fbf8-64c9-4a42-813d-d13a8fe356c5" width="30%"/>

## Dependency
### settings.gradle

```gradle

    repositories {
        // ...
        maven { url "https://jitpack.io" }    // add
    }

```

### app 수준 build.gradle

``` gradle

implementation 'com.github.jeonjungin:previewoffsetviewpager:1.0' // add

```

## Release

### v1.0
- 최초 배포

## xml
```xml
<com.willbegod.previewoffset.PreviewOffsetViewPager
    android:id="@+id/viewPager"
    android:layout_width="match_parent"
    android:layout_height="200dp"/>
```
## Attrs

### itemMarginHorizontal

``` xml
    app:itemMarginHorizontal="30dp"
```

`itemmarginHorizontal` 은 각 아이템 사이의 margin 을 지정하는 속성입니다.

### previewItemWidth

``` xml
    app:previewItemWidth="30dp"
```

`previewItemWidth` 는 스크롤 되지 않는 상태에서 미리 보이는 왼쪽, 오른쪽 아이템 width 를 지정하는 속성입니다.

## in kotlin

`PreviewOffsetViewPager` 은 내부에 ViewPager2 를 inflate 하고, 단순하게 양옆의 아이템을 미리보기 위한 설정을 추가한 Layout 입니다.
ViewPager2 와 동일하게 사용 가능합니다.

### setAdapter
```kotlin

    binding.viewPager.setAdapter(slideAdapter)

```
viewPager2 와 같이 `setAdapter` 메서드를 통해 RecylcerView Adapter 를 주입하여 사용합니다.

### registerOnPageChangeCallback
```kotlin
      binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            // ...
      })
```
ViewPager2 와 같이 `registerOnPageChangeCallback` 메서드를 통해 callback 을 설정할 수 있습니다.

### getCurrentItem, setCurrentItem
```kotlin
      val index = binding.viewPager.getCurrentItem()
      binding.viewPager.setCurrentItem(pos = 0, smooth = true)
```
`getCurrentItem` 을 통해 현재 포커스된 페이지 index 를 반환받을 수 있습니다.
`setCurrentItem` 을 통해 페이지를 이동시킬 수 있습니다.
