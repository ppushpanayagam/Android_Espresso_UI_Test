# Reach PLC - Native Mobile QA Engineer

This test is part of Reach's hiring process for a Native Mobile QA Engineer position.

## Objective

The goal of this technical test is to create a simple framework using espresso to test this simple application.

The application consists of two fragments which you can access by tapping the 2 options on the BottomNavigationView.

Each tab loads a list of facts about cats and load an image with a placeholder text on top of it, the difference between the two tabs is architectural pattern used MVI and MVVM.

### What we're expecting

We expect your code to work without bugs and implement the following features:

- Mock NetworkDataSource and CatFactsRepository in a way that the order and information are always in the same position
  - You'll notice that if you just launch the app each time the order and information will be slightly different
- Use HiltTestApplication in a custom test runner

- Implement the following tests:
  - Validate that once you tap a fact the zoomable image screen opens correctly
    - Don't worry about validating if the zoomable image can actually be zoomed in/out
  - Validate that the details of the first and last item of the list are correctly displayed
  - Validate that you can open an item from the MVI tab, then go back and open an item on the MVVM tab

The goal here is not to look at the tests you implemented but rather how you've structured the framework and how did you leveraged the use of custom matchers to improve re-usability.

We also expect your code to be a reflection of yourself at work, so we will be attentive to the choices you'll make regarding code readability and organisation.

The test should be completed within 7 days, although you should be able to send it back to us if you complete it earlier.

### What we're not expecting

That you implement more tests, the focus here is not to see how many tests you can implement but the quality of the framework you've built.

### Bonus features

If you have some time left and want to go a little bit further, here are some feature ideas you can add to this app:

- Implement a test to validate pulling and refreshing the feed on the MVVM tab
- Find a way to take screenshots of failures and fetch them from the device
- Create a test plan class that you can then use to group your tests and run them by running the test plan class

## Submission

The quickest way to submit your work is by creating a repo on Github, Gitlab, Bitbucket copy this repository to your personal space, and send us a link to your branch (if you make your repo private, you'll need to [invite us as collaborators](https://help.github.com/en/articles/inviting-collaborators-to-a-personal-repository)).
