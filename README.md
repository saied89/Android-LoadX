# Android LoadX
[ ![Download](https://api.bintray.com/packages/saied89/loadx/LoadX/images/download.svg) ](https://bintray.com/saied89/loadx/LoadX/_latestVersion)

You don't need to bake the Loading into your layouts anymore!  Add ProgressBar on top of every view simply by calling a Kotlin Extension function. Guaranteed to reduce the depth of your layout tree by at least 1.

 The library is extremely light weight with only half a dozen extension functions in a single Kotlin file. It works by adding a  a view that contains a progressbar, to parent of the target view.

The library uses [MaterialProgressbar](https://github.com/DreaminginCodeZH/MaterialProgressBar)

### Requirements

minSdkVersion 16

### Setup
Add this snippet to your gradle dependencies. Make sure you have Jcenter in your repositories.
```groovy
implementation 'com.saied.android:loadX:1.0.2'
```

### Try it out
Get the [app](https://github.com/saied89/Android-LoadX/releases/download/1.0.2/app-release.apk)

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
### Java Usage
You can call Kotlin extention functions form java. For this library its like this:
```Java
ViewExtensionsKt.loadX(targetView);
```
### Return param
The function returns the `loadingView` so you can further customize it.

### Views Supported
Currently views whose parents are Linear, Frame, Relative and Constraint Layout are supported.

PR's and issues are cherished!

