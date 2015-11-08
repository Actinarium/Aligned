# Aligned

Aligned is a tiny library for Android that makes putting text on baseline infinitely easier.

![Aligned](https://github.com/Actinarium/Aligned/blob/master/images/sample.png)

At the moment it does only one thing _(and does it freaking rad!)_ — provides a `TextView` implementation that allows
setting arbitrary leading and some other metrics on the text, helping you to place it nicely on the
[Material 8dp grid][mdspec], pixel-perfect, cross-device¹, _effortless!_

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




---
¹ — In case you find it to be _not really_ cross-device, please open an issue.

[mdspec]: http://www.google.com/design/spec/layout/metrics-keylines.html