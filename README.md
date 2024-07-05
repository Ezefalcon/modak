The task was made using Spring Boot:

### Requirements:

- Java 21
- Maven 3

## Run App

    mvn spring-boot:run

## Run tests

    mvn test

Or you can just import the project in Intellij and get everything preconfigured

### Task
The task was accomplished in the NotificationService send()
method.

### Notes
Integration tests were made because it was the easiest way to test the app.

There's a commented test under NotificationsServiceTest that also checks out if the time works correctly.
    
    notification_sendWithNotificationConfig_limitSleep

Even though I didn't add any endpoints for this app, it is something that can be accomplished easily.
Also we can add Swagger to show the endpoints.

In memory H2 database was used to avoid using real db.

## [Backend] Rate-Limited Notification Service
The task
We have a Notification system that sends out email notifications of various types (status update, daily news, project invitations, etc). We need to protect recipients from getting too many emails, either due to system errors or due to abuse, so let’s limit the number of emails sent to them by implementing a rate-limited version of NotificationService.

The system must reject requests that are over the limit.

Some sample notification types and rate limit rules, e.g.:

Status: not more than 2 per minute for each recipient

News: not more than 1 per day for each recipient

Marketing: not more than 3 per hour for each recipient

Etc. these are just samples, the system might have several rate limit rules!

NOTES:

Your solution will be evaluated on code quality, clarity and development best practices. Take into account that this is the initial assessment of the work quality that you can produce in a productive environment so show your best face.

Feel free to use the programming language, frameworks, technologies, etc that you feel more comfortable with.

Below you'll find a code snippet that can serve as a guidance of one of the implementation alternatives in Java. Feel free to use it if you find it useful or ignore it otherwise; it is not required to use it at all nor translate this code to your programming language.



Please share with us the link of the repository containing the application source code when finished and let us know to schedule the meeting to review the app.