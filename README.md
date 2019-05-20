# Simple Photo Viewer
To build the app in Debug mode, please run this in the command line of the application directory:
```
./gradlew assembleDebug
```
**Note**: Currently, the application had been tested to run only in Debug mode. Issues may appear if it was run in Release mode, since `proguard-rules` has not been configured and it may strip and obfuscate some classes and methods.


To execute unit tests and instrumentation test including its code coverage report generation, please run:
```
./gradlew jacocoTestReport
```
Code coverage report could be found in `{appDirectory}/app/build/reports/jacoco/jacocoTestReport/html/index.html`

**Note**:
- Instrumentation tests coverage wouldn't be able to be recorded in certain devices. I suspect it can be happened if there are spaces in the name of test execution data (which you can find in `{appDirectory}/app/build/outputs/code_coverage/debugAndroidTest/connected/*.ec`) There shouldn't be any problem if it is ran in emulator.

Sample code coverage where instrumentation tests wasn't recorded can be seen in `{appDirectory}/screenshots/partial-code-coverage.png`
Sample code coverage where instrumentation tests was recorded can be seen in `{appDirectory}/screenshots/full-code-coverage.png`
Sample debug APK can be found in `{appDirectory}/app-debug.apk`


Three features that I will build on top of this sample application:
1. Image and API Caching
Description: This is an important feature because Images size are quite heavy usually and the API response is quite large. If we could cache the Images and the API response for quite some time, that would reduce the data usages of the user. This feature could be added easily by making use of Picasso library for the image. To cache the API, we could make use of database and store each photo as a row in database and put the photo ID as the primary key. To integrate this, we could easily do it by creating a new class as the data source to retrieve and store images from the database. In PhotoRepository now, instead of calling the API and return it directly, we will parse the API response and store it in database and return the data stored in the database. Testing would be easy since the new class to retrieve and store images could be mocked when we test PhotoRepository.

2. Swipeable Images in Detail
Description: User could just swipe left/right to browse the images in detail screen without having to go back to the list screen
This feature could be added easily by moving the logic of loading image from PhotoDetailActivity to a Fragment. And then we need to add ViewPager in PhotoDetailActivity as the host of all the Fragments. Each fragment represent 1 photo and ViewPager would hold the repository to get the list of photos. To test this, we could make use of Espresso by providing fake photos data and simulate swiping left/right and verify whether correct images are shown in the Fragment.

3. Add to Favorites
Description: User could add the photos he/she wants to their own favorites list and the user could see all their favorite photos. To implement this, we could make use of local database where we store the ID of favorited photos. There will be another table which contains the response from the API (feature 1). From that table we could connect the favorite photos ID to the photo detail. To do this, we could make a FavoritePhotosRepository where we could retrieve and store favorite photos. To test this feature, we could mock database and test FavoritePhotosRepository easily. We could also use Espresso to simulate clicking favorite on the photo.