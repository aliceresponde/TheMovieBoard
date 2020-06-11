[![Build Status](https://travis-ci.com/fernandospr/android-movies.svg?branch=master)](https://travis-ci.com/fernandospr/android-movies)

# TheMovieBoard
Its a Kotlin Project that use https://developers.themoviedb.org/ as server to request Movies and Series, sorted by *Popularity*, and *Rated*. Using coroutines the heavi woth is isolated into IO Thread. 

## App Layers
![img](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

# Data Layer:
`Database` y `DAOs`and `Entity models` for  **Locale persostance**. Its need to support offline management.
`MoviesApi` y `Interceptor` `Responses` for call remote API **Remote**. used to interact with the REST API.
`Repository` Its related with the data source of the layer for network or local data.

## View Layer - MVVM
* Used MVVM with LiveData 
* Use ViewModel 
* Use DataBinding


## TODOs en el proyecto
* Complete dagger implementation because of @Binds (Modules are ready)
* Add unittesting.
* Add Domain layer related to the logic for use cases.
* Add Data sources

* UI/UX
	* It use Materia design menu at the botton using 2 tabs - [BottomNavigationView](https://developer.android.com/reference/android/support/design/widget/BottomNavigationView) 
   Note: [Material Design](https://material.io/design/components/bottom-navigation.html) suggest to user 3 or 4 opcions
	* Used a CardView to see the cataloge itemss.
	* Used SVG icons to keep the resolution
  * Used Constraint Layout to avoid over drawing, and improve the distribution of the Views in the XML
  * Coil Library to display image resources from assets or url. https://github.com/coil-kt/coil
  * Used Navigation Components and safe args.
