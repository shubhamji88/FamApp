## FamApp


## About The Project
This project provides a custom view (FamView) that require List<CardGroupModel> (Defined in project) and a ReloadClickListener to handle the pull down gesture case of
reloading data.

## APK LINK
https://drive.google.com/file/d/16hYMZ2rD1i5t5IWo0q2sxFjryV7geJPZ/view?usp=sharing


## Project's Files Structure

1. data:

    i. entity: entity classes for api response
  
    ii. repository: RemoteRepoImpl an implementation of RemoteRepo that returns Deferred<Response<FamCardEntity>>
  
    iii. FamApi: Interface for retrofit for fetching data
  
   iv. SharedPrefManager: Used for shared prefrences 
  
2. di: dependency injection files
3. domain:
  
    i. model: data classes used by FamView 
  
    ii. repository: Interface of remoteRepo
  
   iii. Usecases: used for fetching data using repo and converting entity to model
  
    iv. DesignTypes: used by FamView to determine type of cards 
4. ui:
  
    i.adapters: different adapters for different types of cards
  
   ii.customView: for FamView
  
    iii.Utils: common functions used by different adapters
  
    iv. Fragment and activity: example of how HomeFramgnet has used Famview
  
## App's Data flow
![image](https://user-images.githubusercontent.com/56815364/177750163-86ea39d6-bbcb-412e-906e-b140295daafb.png)

## FamView's Data Flow
![image](https://user-images.githubusercontent.com/56815364/177752400-f450c2f0-1ed1-4ea7-a06b-fdcfed5d8734.png)

## ScreenShots
![WhatsApp Image 2022-07-07 at 3 15 23 PM](https://user-images.githubusercontent.com/56815364/177744621-d0cdad9b-d069-4ddc-a43b-dddda0bd8d6d.jpeg)
![WhatsApp Image 2022-07-07 at 3 15 22 PM](https://user-images.githubusercontent.com/56815364/177744629-180ccaa7-7601-444d-a33c-f88a801371f0.jpeg)




## Development Setup

Before you begin, you should have already downloaded the Android Studio SDK and set it up correctly. You can find a guide on how to do this here: [Setting up Android Studio](http://developer.android.com/sdk/installing/index.html?pkg=studio).

## Building the Code

1. Clone the repository using HTTP: git clone https://github.com/shubhamji88/FamApp

2. Open Android Studio.

3. Click on 'Open an existing Android Studio project'

4. Browse to the directory where you cloned the repo and click OK.

5. Let Android Studio import the project.

6. Build the application in your device by clicking run button.
## Contact

Shubham shankaram - shankaram.shubham8@gmail.com

<p align="right">(<a href="#top">back to top</a>)</p>

