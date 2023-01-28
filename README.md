Please upload the initial project creation to Github as a private repo and share the project with bfazeli (bfazeli1@gmail.com). When completed reply to this email with a link to the repo (please commit frequently as you work on your assignment)!  Do not commit once you have submitted the link to the repo


What to build:
The goal of this assignment is to build a small app to give the user information about the hot and new restaurant in their current location. This is an opportunity for you to show off your skills!
The app should use the Yelp Fusion API https://www.yelp.com/developers/documentation/v3

token: OyFRR5PRd5I5hJ1f1ihFkqyANxelEJi0L6T06z3OvrthiWSan7_0ZZSZ_IhganUVxsCMwcxA-qmCeJJGkcyN-zW5CMWm-IVlyc0JZx2Ya92MU6Smr9OTuHuoy2EzY3Yx


Overview: The Hot and New Restaurant app should display the best matched hottest and newest restaurants around the user’s current location.


This app will consist of two pages; the landing page to show the best matched restaurants and a details page to show more fine-grained information about the restaurant and what customers have to say.


Upon loading the app and hitting the landing 'home' page it should request permission to use the user’s location
On "Allow while using the app" or "Allow always": Refresh restaurant list to show current user location's hot and new restaurants.
On "Don't Allow”: Do nothing
When the user allows location permission the landing page should show at least the following:
(Don't worry too much about design here, just get something working with something like a simple RecyclerView with an empty state.)
A title
A list of the hottest and newest restaurants in the user's current location where each item should consist something of the following:
The name of the restaurant
The $ of the restaurant
An image url of the restaurant
The rating of the restaurant
How far away the restaurant is from the user’s current location in miles
Upon tapping a restaurant item the user should be sent to the details screen for more information
The details page should at least show the following information:
A header region that shows more information on the restaurant the user selected:
Name of the restaurant
The $ of the restaurant
An image url of the restaurant
The overall rating of the restaurant
The phone number of the restaurant
The address of the restaurant
A body region that shows at most 3 reviews for that restaurant
Each review item should include the following:
name of the user
An associated image icon of the user (if one doesn’t exist, a placeholder icon can be used)
The rating
The date of the review
What the user had to say

What to use:
Project must be in Kotlin
Use any libraries (Retrofit, for instance, for making api calls), but if you know them, please include the use of Koin, HILT or Dagger (be prepared to answer questions on the DI of choice) and either RXJava, LiveData, Flow (or any of the other reactive libraries).
You can use either traditional xml-based layouts or jetpack compose to build the UI side
Use a clear architecture pattern that you feel most comfortable with (MVC is still valid).
Be prepared to clearly explain how/why you architect your code.
Nice to have unit tests for business logic

Feel free to reach out to me if you have any questions
Looking forward to what you end up building


-----


Not too strict with this part, but for the time being if a user taps <<don’t allow>>
Then refresh the restaurant list with this default lat, lon (33.83422265228964, -118.21241904417631)

Not expecting to refresh on location change; however you may wish to implement some form of refresh that the user can trigger.

Not required*
If you’re feeling more ambitious you can throw up a snack bar explaining what’s happening when user taps on <<don’t allow>> and allow the user to open settings to grant location permission; if you feel like you want to enhance the experience even further and you have time you may do the above but instead of a snackbar, show a dialog that explains the current state and a button that would open settings


----- 

If app is killed, user hasn’t granted location permission and they launch the app, then you may prompt with the location permission dialog. However, if you do opt for this, don’t show them the location permission dialog if they background and foreground the app.

