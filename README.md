# myJournal - FOLIO
A social journaling app.

## Project Outline


### What it does:

Folio is a social journaling app, written in Java. Read and write to your heart’s content!


### How to run:

Get python.

Open a terminal, navigate to where this project is.

Run


```
pip install -r requirements.txt
```


Then run.


```
python run_client.py
```


Create an account, then run again.


```
python run_client.py
```





## UML Class Diagram:


![UML Diagram](https://i.imgur.com/P2QcZ07.png "A bit hard to see as well")


A bit outdated...


## Collections


### Set

Set was used for its uniqueness properties in keeping track of the accounts who have liked, viewed, or followed our Followable data structures. It was used in:



*   Account, for subscribed and journalIds.
*   AccountStatistics, for followers.
*   Feed, for subscribed.
*   JournalOptions, for owners, contributors, and viewers.
*   JournalStatistics, for likers, followers, and viewers.
*   PageStatistics, for likers and viewers.


### List

ArrayList was used for its quick indexed access for the pseudo database inside of DBCommunication.java.

A List was used to store pageIds in Feed, so that elements could be accessed in the future.

A List was used in JSONArray to store JSONElement values.


### Queue

A Queue was used to store pages in Feed because pages must be retrieved in a first in, first out manner.


### Map

A Map was used in JSONObject to map Object keys to values.


## Design


### Original Feature Descriptions

We will write the backend for a social journaling app that does the following:



*   Allows user to create an account.
    *   Accounts can be followed.
    *   Account profile information can be edited.
    *   Creates an individualized feed based on followed accounts/journals.
*   Allows user to create journals.
    *   Journals can be public or private.
    *   Journals can have multiple contributors.
    *   Journals can be followed.
    *   Journals keep track of likes and views.
*   Allows user to write pages in journals.
    *   Pages keep track of likes and views.
*   Interacts with database
*   Has network endpoints

A basic terminal frontend will be created:



*   That will allow user to interact with backend as described above

A GUI will NOT be created (unless we finish backend early)


### Changes



*   Terminal frontend was created in Python, rather than Java, because of its expressiveness and because it is easier to write than Java.
*   There are no interactions with a database, instead run-time data structures were used to store our data structures. This was done mostly because of time constraints, so that the product would actually be functional in time for submission. The only downside to this change is that data does not persist if the server is stopped and started, so in the future the data storage will definitely be migrated to a database.
*   Added JSON classes for easy serialization of our DataStructures. This makes it so that the python frontend (or any other client application) can make sense of the data sent through the api endpoints. The advantage of rolling our own JSON serialization is that behavior specific to our application can be integrated into the JSON classes and that we have a system that is hopefully less complex and more extensible for our purposes in JSON. For example, our JSON classes can be used to deserialize JSON that we would store in the database in the future.
*   Created intermediary classes (JournalId, PageId, AccountId) that act as go-betweens for our DataStructures classes so that they don’t ever directly interact with the database. These intermediary classes reduce the complexity of stored DataStructures (especially helpful when sending them through our API), while still allowing DataStructures classes to act on the DataStructures the intermediaries represent.
