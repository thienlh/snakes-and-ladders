fun main(args: Array<String>) {
    if (args.size == 1 && args[0] == "h") {
        println("Syntax:\n[numSquares] [numLadders] [numSnakes] [numPlayers] for default player names.\n[numSquares] [numLadders] [numSnakes] [Player name 1] [Player name 2]...\n")
        return
    }
    if (args.size < 4) throw IllegalArgumentException("Not enough arguments. Call with :h for help")

    val numSquares = args[0].toInt()
    val numLadders = args[1].toInt()
    val numSnakes = args[2].toInt()
    val game: SnakesAndLaddersGame = if (args.size == 4) {
        val numPlayers = args[3].toInt()
        SnakesAndLaddersGame(numSquares, numLadders, numSnakes, numPlayers)
    } else {
        SnakesAndLaddersGame(numSquares, numLadders, numSnakes, args.drop(3).map { Player(it) })
    }

    while (game.winner == null) {
        game.nextMove()
    }
}