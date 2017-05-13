# About jhuPool #

jhuPool is an Android application that matches members of the Johns Hopkins University community (affiliates with a JHED) going from Homewood campus to the same place, or from the same place returning to Homewood campus. Users sign in to jhuPool using their Facebook and JHED logins. The platform is intended to connect riders going the same place at the same time; they  then have the option to chat on Facebook messenger and decide whether to share an Uber, Lyft, Zipcar, or one of the rider’s car’s to their destination. The users are free to decide whether to split costs by cash, Venmo, or within the Uber/Lyft app.

### Fixes ###

The logout/login issue now works; when a user logs out, her/his Facebook signin and JHED signin are both cleared, and s/he is able to sign in from scratch via the Login Activity. (This works on all of our emulators.)

The ViewRide Activity does not have multiple Scrollables within each other, so the Rider and Notes sections are both individually scrollable. (This works on all of our emulators.)

Profile pictures for Notes now correctly populate (along with Rider). The profile pictures also no longer have the default Facebook silhouette bug. (These corrections work on all of our emulators.)

### Login ###

Users are required to sign in with both their Facebook username and their JHED (since we don't have access to a database of all of the JHEDs and their passwords, we have temporarily created an array of JHEDs to be accepted; this currently includes "wmattes2," "vtsai5," "szappon1," and "rkinney4").
