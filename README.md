# Android Loading Extensions

The purpose of this project is to provide android developers the ability to add a simple yet customisable(Convention over configuration) progressbar on every view by simply calling a Kotlin extension function. The library is extremely light weight with only half a dozen extension functions in a single Kotlin file.

The library uses [MaterialProgressbar](https://github.com/DreaminginCodeZH/MaterialProgressBar)

### vanilla use
simply call `targetView.loading(true)`

### aditional parameters
- **progressbarSize**
- **backgound color**: background color of the frame layoput that hosts the progressbar
- **progressBarColor**: color of progressBar
- **loadingView**: Pass a completely custom View for ultimate control.








Crrently views whose parents are Linear, Frame, Relative and Constraint Layout are supported.

Seeking collabarators(email saiedmomen@gmail.com)
