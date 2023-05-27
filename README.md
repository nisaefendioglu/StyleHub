# StyleHub

StyleHub is a simple Android application that allows users to browse and view product listings. It is built using Retrofit, MVVM architecture, and Data Binding.

## Demo
![Screenshot_4](https://github.com/nisaefendioglu/StyleHub/assets/48391281/54e5f5f6-4361-40fd-b896-8931eb1395d6)

## Features

- Fetches product data from the server using the following service URL: `https://dummyjson.com/products`
- Displays a list of products on the main screen
- Allows users to tap on a product to view its details on a separate screen
- Product List Screen displays the following information:
    - Title
    - Thumbnail
    - Brand
    - Price
- Product Detail Screen displays the following information:
    - Title
    - Description
    - Price
    - Images (shown in a slider)

## Technologies Used

- Retrofit: A type-safe HTTP client for Android networking
- MVVM Architecture: Separation of concerns using Model-View-ViewModel architectural pattern
- Data Binding: Binds UI components in layouts to data sources in the app

## Setup and Installation

1. Clone the repository: `git clone https://github.com/nisaefendioglu/StyleHub.git`
2. Open the project in Android Studio
3. Build and run the application on an Android device or emulator

## Dependencies

The following dependencies are used in this project:

- Retrofit: `implementation 'com.squareup.retrofit2:retrofit:2.9.0'`
- Retrofit Gson Converter: `implementation 'com.squareup.retrofit2:converter-gson:2.9.0'`
- ViewModel and LiveData: `implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0-alpha01'`
- Data Binding: `androidx.databinding:databinding-runtime:7.1.0-alpha01`

Make sure to sync the project after adding these dependencies.

## License

This project is licensed under the [MIT License](LICENSE).
