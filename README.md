# myNPU

![build status](https://travis-ci.org/FaytxZen/myNPU.svg?branch=master)

## Purpose

Civilian involvement is one of the most effective ways to bring about change at the grassroots level. By getting involved (directly and indirectly) with your community planning committees, you help reflect the needs of your community at the municipal level and further. In Atlanta, one of the supported channels for giving feedback on city planning efforts is the NPU system. This app was made to help locals learn about NPUs and find out what NPU they belong to.

## What is an NPU?

Read all of that at the Atlanta .gov page for it.

[http://www.atlantaga.gov/index.aspx?page=739](http://www.atlantaga.gov/index.aspx?page=739)

## Set Up

*This project was built using Android Studio, so it's recommended you use the same IDE to make run it and make changes.*

### Clone

Clone the project the way you normally would,

```bash
git clone https://github.com/FaytxZen/myNPU.git
```

### Open the Project in Android Studio

Go to **File**. Select **Open**. Find the folder git created when it cloned the project. Select it and choose to use your choice of gradle version.

### Add the Necessary Files
This app uses Firebase for getting the NPU event data. In order to get that service running, you'll need a `google-services.json` file within the `app/` directory.

For instructions on getting that set up, consult the [Firebase documentation](https://firebase.google.com/docs/android/setup).

### Optional Configuration
This app also uses Google Maps to display the NPU borders and geocode addresses. As a result, you'll need a Google Maps for Android API key. You can get this from the [Google API console](https://console.developers.google.com).

Add the API key to the `api_keys.xml` file. It should look like the following blurb:

```xml
<resources>
  <string name="google_maps_key" templateMergeStrategy="preserve" translatable="false">
    YOUR API KEY HERE
  </string>
</resources>
```


## Contributing

1. Make a branch.
  * If you're not a member, fork the repo.
2. Commit your changes to your branch.
3. Push and make a pull request (PR).
4. Let me know so I can review and merge.
