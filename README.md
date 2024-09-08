# Instaflix

Android Native app in Kotlin and using Jetpack Compose. 

## Features 

- View Movies Catalog from at leat two categories.
- View Series Catalog from at leat two categories.
- View Detail information on any (Serie/Movie) selected.
- Offline Navigation should be supprted
- Fetch information using TMDB (https://developer.themoviedb.org/docs/getting-started)

### Bonus 

- Unit test.
- Jetpack Components use.
- Integrated testing.
- Animations and Transitions.
  
 ## Libraries

- Room : For local DB that will allow us to use information even while offline, this Lib is also part of Jetpack
- Hilt: This lib simplifies the implementation of Dagger and allows us to use DI in the project, Also part of Jetpack
- Retrofit: Lib used to fetch information from the DB
- Coil: to get imgs from the Url of the API.

## Structure

 This App will be structured using a mix from different concepts mainly MVI making use of android Jetpack Compose and its avantages to display information on screens depending on the state. Also concepts from Clean Arquitecture.

## Previews
![Screenshot 2024-09-07 at 8 10 43 PM](https://github.com/user-attachments/assets/396269a1-84d8-4a99-a85c-dc21aab41dfc)

![Screenshot 2024-09-07 at 8 10 54 PM](https://github.com/user-attachments/assets/61128aba-f76a-4031-ae09-7543e3fea16e)

### Test Results for DAO

![Screenshot 2024-09-07 at 7 42 37 PM](https://github.com/user-attachments/assets/68405f38-bbf4-4ec2-aeb2-dc8ae4c4f437)

## Improvements missing: 
- Message for users to display when fetching from DB and when from the API 
- Refresh functions
- Pagination to fetch more shows at the end of the list
- Theme management
- UI



