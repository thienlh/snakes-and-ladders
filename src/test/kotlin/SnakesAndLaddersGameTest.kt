import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class SnakesAndLaddersGameTest {
    @Test
    internal fun `should construct game with correct values`() {
        val game = SnakesAndLaddersGame(50, 5, 5, 2)
        assertEquals(game.numSquares, 50)
        assertEquals(game.numLadders, 5)
        assertEquals(game.numSnakes, 5)
        assertEquals(game.numPlayers, 2)
        assertEquals(game.players.size, 2)
    }

    @Test
    internal fun `should have positive number of players`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(100, 11, 12, 0) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(100, 11, 12, -3) }
    }

    @Test
    internal fun `should have at least 10 squares`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(0, 0, 0, 1) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(-3, 1, 1, 1) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(9, 1, 1, 1) }
    }

    @Test
    internal fun `should never have negative number of ladders`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(20, -3, 1, 1) }
    }

    @Test
    internal fun `should never have negative number of snakes`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(20, 1, -3, 1) }
    }

    @Test
    internal fun `should not have too many tunnels`() {
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 10, 10, 1) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 5, 5, 1) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 3, 3, 1) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 3, 2, 1) }
        assertThrows<IllegalArgumentException> { SnakesAndLaddersGame(10, 2, 3, 1) }
    }

    @Test
    internal fun `should have ladders and snakes`() {
        val game = SnakesAndLaddersGame(100, 2, 3, 2)
        assertEquals(game.ladders.size, 2)
        assertEquals(game.snakes.size, 3)
    }

    @Test
    internal fun `should advance the game`() {
        val game = SnakesAndLaddersGame(100, 2, 5, 2)
        assertEquals(game.moves.size, 0)

        game.nextMove()

        assertEquals(game.moves.size, 1)

        val move = game.moves[0]
        assertEquals(move.player, Player("Player 1"))
        assertTrue(move.rolledNumber in 1..6)
        assertEquals(game.players[0].currentPosition, move.rolledNumber + 1)

        game.nextMove()
        assertEquals(game.moves[1].player, Player("Player 2"))

        game.nextMove()
        assertEquals(game.moves[2].player, Player("Player 1"))
    }

    @Test
    internal fun `should climb a ladder`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4)))
        val player = game.players[0]
        game.move(Move(player, 2))
        assertEquals(player.currentPosition, 4)
    }

    @Test
    internal fun `should climb multiple ladders`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4), Tunnel(4, 8)))
        val player = game.players[0]
        game.move(Move(player, 2))
        assertEquals(player.currentPosition, 8)
    }
}