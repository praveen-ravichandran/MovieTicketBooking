# Movie Ticket Booking System with Ticket Confirmation Broker

The system focuses on a use case where multiple users trying to book tickets to the same movie show.

```
There are two main components,
 - A Ticket Booking System
 - A Ticket Confirmation System
```

## MovieTicketBookingSystem [🔗](./MovieTicketBookingSystem)

#### Ticket Booking API

```
/api/ticket/book - POST
```
##### Request JSON Body:

```
{
  "userMail": <email-id>,
  "movieShowId": <movie-show-id>,
  "seats": <array-of-seat-ids-pertaining-to-theatre-hall-and-hall-class>
}
```

##### Response on Success:

A unique request Id will be returned. The system is designed in such a way that the reqId shall be used for the Third party Payments and as well as to push/track the ticket status through Websocket.

```
{
  "reqId": <unique-transaction-id>
}
```

## MovieTicketBookingConfirmationBroker [🔗](./MovieTicketBookingConfirmationBroker)

The Ticket Confirmation Broker System is an autonomous scheduled task that is configured to run at a fixed delay of one second between worker execution.

```
The role of the worker is to,
 - Retrieve all the booked tickets that are yet to be processed
 - Check and confirm the tickets based on the criteria
```

Future Scope:
A Websocket instance which pushes the ticket booking status pertaining to the unique reqId.