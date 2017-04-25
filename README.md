 #Task 

 * Create an Android application that will fetch information about the current Bitcoin market price
 * and display the data in a graph of your choosing.
 * The application must:
 * 1. Make a network call to a remote api resource to fetch the current exchange rate data
 * 2. Effectively make use of threading and asynchronous behaviour,
 * 3. Show understanding of the Android SDK and application / activity lifecycles,
 * 4. Be performant and make appropriate use of background tasks,
 * 5. Show market price data over time using an appropriate graph.


Application is implemented with MVP pattern, clean architecture in mind and written in Kotlin language.
To display data full circle of model layers is 
 
For testability and IoC Dagger2 is applied. For background tasks RxJava. For networking OkHttp + Retrofit.
 
Couple of my local libraries used to speed up the process and provide cleaner code. 
- Arkitek module provides base classes for the app and assures mvp attach/detach lifecycle.
- LilWidgets provides loader widget with convenient showLoader/showError interface. 
- ElAdapter as a quickest possible way to implement RecyclerAdapter and handle items clicks.
 
Additional features - in memory cache implemented via MemoryCacheMembersDataSource class and tested with unit test.
 
Application supports rotation but do not support tablet screen.

Existing drawbacks
1. MainModule could be separated into MainModule and MarketPriceModule
1. There's a bug in loader dialog, so I had to use Handler().post workaround not to mess with the library.
