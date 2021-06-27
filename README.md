# The Blue Alliance Java api wrapper

A simple, easy-to-use, api wrapper for TBA's api.

---
[![](https://jitpack.io/v/DAflamingFOX/The-Blue-Alliance-Java-api-wrapper.svg)](https://jitpack.io/#DAflamingFOX/The-Blue-Alliance-Java-api-wrapper)

---
## Getting started

This api wrapper is designed to be as simple as possible; to get started you need an api token from TBA. You can get one [here](https://www.thebluealliance.com/account), you can make a new key by scrolling down to where it says *Read API Keys*. Once you make a new key, copy the `X-TBA-Auth-Key` value, that's the important part.

The next step is to create an instance of `TBAApi` in your code, you are going to need your api key for this part; it should look as follows:
```java
public class Main {
    public static void main(String[] args) {
        // Create a new instance of The Blue Alliance Api.
        TBAApi api = new TBAApi("your-api-read-key");
    }
}
```

Thats it; api instance created. All of the methods are within nested classes of the api (`TBA`, `lists`, `team`, `event`, `match`, and `district`), so for instance; to get the TBA status; you can do as follows:
```java
// ...
// Gets an APIStatus object from the api.
APIStatus apiStatus = api.TBA.getStatus(); 
// ...
```

All info that the object contain are stored as public class attributes; so you can get the data from them without a method.

```java
// ...
// All of the objects contain their data as attributes.
apiStatus.isDataFeedDown // -> false (returns true if FRC datafeed is down)
// ...
```

All of this is pretty simple, so it only takes two lines of code to get the data:
```java
public class Main {
    public static void main(String[] args) {
        // Create a new instance of The Blue Alliance Api.
        TBAApi api = new TBAApi("your-api-read-key");
        System.out.println(api.TBA.getStatus().isDataFeedDown); // -> false
    }
}
```

If you have any issues or questions please create an issue; and we'll have a look at your problem.

---


## Contributors
<!-- Add your name here if you contribute-->
Project created by [**Jeffrey Morris**](https://www.github.com/DAflamingFOX) (7125).

---
**Powerd by The Blue Alliance**