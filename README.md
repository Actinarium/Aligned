# Aligned

Aligned is a tiny library for Android that makes putting text on baseline infinitely easier.

![Aligned](https://github.com/Actinarium/Aligned/blob/master/images/sample.png)

At the moment it does only one thing _(and does it freaking rad!)_ — provides a `TextView` implementation that allows
setting arbitrary leading and some other metrics on the text, helping you to place it nicely on the
[Material 8dp grid][mdspec-metrics], pixel-perfect, cross-device¹, _effortless!_

## How to use

Add a dependency. It's on its way to jCenter, so for now add it to your `build.gradle` like this:

```
repositories {
    maven {
        url  "http://dl.bintray.com/actinarium/maven"
    }
}

dependencies {
    compile 'com.actinarium.aligned:aligned:0.1'
}
```

Now you can use `com.actinarium.aligned.TextView` in your layouts. It’s just like `TextView`, but offers three
additional properties:

* `app:leading` — forces the lines to have specified leading. You probably want to take the values from [the spec][mdspec-leading];
* `app:firstLineLeading` — allows to set special leading for the first line, otherwise `app:leading` will be used. You
  may want to use this instead of top margins.
* `app:lastLineDescent` — specifies the distance from the baseline of the last line to the bottom of the view. Set this
  to a multiple of 4dp so that the bottom bound of the view also lands on the grid, and the next view is not misaligned.

![Leading attributes explained](https://github.com/Actinarium/Aligned/blob/master/images/leading.png)

```
<com.actinarium.aligned.TextView
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textAppearance="@style/TextAppearance.AppCompat.Body1"
        android:textSize="14sp"
        app:leading="16sp"
        app:firstLineLeading="24sp"
        app:lastLineDescent="8sp" />
```

Alternatively, you can use `com.actinarium.aligned.Utils.setExactMetrics()` method to programmatically adjust these
properties of existing `TextView`s in your layouts.

## License

The library is licensed under Apache 2.0 License, meaning that you can freely use it in any of your projects.

The full license text is [here][license].

## A personal appeal

If you already feel that this library is a godsend that will save you lots of time, please consider adding me on
[Google+][gplus] and/or [Twitter][twitter]. I am about to publish a free, no-ads productivity app soon, and it will be
super-awesome if you hear of it as soon as possible and check it out.

Also feel free to check [Rhythm][rhythm], my other typesetting adoration lib.

---
¹ — In case you find it to be _not really_ cross-device, please open an issue.

[mdspec-metrics]: http://www.google.com/design/spec/layout/metrics-keylines.html
[mdspec-leading]: http://www.google.com/design/spec/style/typography.html#typography-line-height
[license]: https://raw.githubusercontent.com/Actinarium/Aligned/master/LICENSE
[rhythm]: https://github.com/Actinarium/Rhythm