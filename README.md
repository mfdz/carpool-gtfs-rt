# Convert carpool trips to GTFS-RT

This project takes is a stub implementation of a service taking carpool trips and
converting them to (GTFS-RT)[https://developers.google.com/transit/gtfs-realtime/]
format so that they can be consumed by routing applications such as
OpenTripPlanner.

Fetching from a remote source is not implemented yet.

## Building

```
./gradlew run
```
## Testing

```
./gradlew test
```
