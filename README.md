# Android Loading Extensions

The purpose of this project is to provide android developers the ability to add a simple yet customisable(Convention over configuration) progressbar on every view by simply calling a Kotlin extension function. The library is extremely light with half a dozen extension functions. 

The library uses [MaterialProgressbar]{https://github.com/DreaminginCodeZH/MaterialProgressBar}

### vanilla use
simply call `targetView.loading(true)`

### aditional parameters
progressbarSize,
backgound color: background color of the frame layoput that hosts the progressbar,
progressBarColor: color of progressBar. (Of type ColorStateList per )

Crrently views whose parents are Linear, Frame and Relative Layout are supported(ConstraintLayout not currently supported)

Seeking collabarators(email saiedmomen@gmail.com)
