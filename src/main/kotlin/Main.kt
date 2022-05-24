fun main(args: Array<String>) {
    if (args.size == 1 && args[0] == "h") {
        println("Syntax:\n[numSquares] [numLadders] [numSnakes] [numPlayers] for default player names.\n[numSquares] [numLadders] [numSnakes] [Player name 1] [Player name 2]...\n")
        return
    }
    if (args.size < 4) throw IllegalArgumentException("Not enough arguments. Call with :h for help")
    if (args.size == 4) {
        val game = SnakesAndLaddersGame(args[0].toInt(), args[1].toInt(), args[2].toInt(), args[3].toInt())
        while (game.winner == null) {
            game.nextMove()
        }
    }
}