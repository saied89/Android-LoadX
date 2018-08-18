# Android LoadX

Add progressbar on top of every view by simply calling a Kotlin extension function. This will shave at least one level off of view hierarchiy depth! The library is extremely light weight with only half a dozen extension functions in a single Kotlin file. It works by adding a  a view that contains a progressbar, to parent of the target view.

The library uses [MaterialProgressbar](https://github.com/DreaminginCodeZH/MaterialProgressBar)

### Requirements

minSdkVersion 16

### Setup
Add this snippet to your gradle. Make sure you have Jcenter in your repositories.
```groovy
implementation 'com.saied.android:loadX:1.0.2'
```

### Usage

Simply call `targetView.loadX()` to toggle the loading status of targetView

#### aditional parameters
- **showLoading**: if a loadingView show be added or removed. Default is toggle.
- **progressbarSize**: size of the progressbar size. Default is the minimum of height and width of the target view.
- **backgound color**: background color of the frame layoput that hosts the progressbar. Default is transparent.
- **progressBarColor**: color of progressBar. Default is theme accent color.
- **loadingView**: Pass a completely custom View for ultimate control. Default is a `FrameLayout` hosting a progressbar and is configurable by above parameters.

```kotlin
targetView.loadX(hideTarget = hideTarget, progressbarSize = progressBarSize, progressbarColor = progressColor, backgroundColor = progressBgColor)
```








Crrently views whose parents are Linear, Frame, Relative and Constraint Layout are supported.

