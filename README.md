# Snakes and Ladders Game

Implementation for Snakes and Ladders game.

## Build

For testing, please run:

```shell
./gradlew test
```

For building, please run:

```shell
./gradlew build
```

## Running the MVP game via command line

Run with `h` option for help.

```shell
./gradlew run --args "h"
```

As you can see from the help screen, there are two ways to run the application, with default names or specify the names.

__Example__: You can run the following command to generate a game with 50 squares, 5 ladders, 5 snakes and 2 players
with default names.

```shell
./gradlew run --args "50 5 5 2"
```

or run the following to specify the names, number of arguments starting from the fourth argument will be count as player
names.

```shell
./gradlew run --args "50 5 5 'First player' 'Second player'"
```

will generate a game with the same structure with the first example, but using custom player names.


