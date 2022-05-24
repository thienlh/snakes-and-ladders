import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

internal class SnakesAndLaddersGameTest {
    @Test
    internal fun `should construct game with correct values`() {
        val game = SnakesAndLaddersGame(50, 5, 5, 2)
        assertEquals(50, game.numSquares)
        assertEquals(5, game.numLadders)
        assertEquals(5, game.numSnakes)
        assertEquals(2, game.players.size)

        val game2 = SnakesAndLaddersGame(50, 5, 5, listOf(Player("First player"), Player("Second player")))
        assertEquals(50, game2.numSquares)
        assertEquals(5, game2.numLadders)
        assertEquals(5, game2.numSnakes)
        assertEquals(2, game2.players.size)
        assertEquals("First player", game2.players[0].name)
        assertEquals("Second player", game2.players[1].name)
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
        assertEquals(2, game.ladders.size)
        assertEquals(3, game.snakes.size)
    }

    @Test
    internal fun `should advance the game`() {
        val game = SnakesAndLaddersGame(100, 0, 0, 2)
        assertEquals(0, game.moves.size)

        game.nextMove()

        assertEquals(1, game.moves.size)

        val move = game.moves[0]
        assertEquals(Player("Player 1"), move.player)
        assertTrue(move.rolledNumber in 1..6)
        assertEquals(move.rolledNumber + 1, game.players[0].currentPosition)

        game.nextMove()
        assertEquals(Player("Player 2"), game.moves[1].player)

        game.nextMove()
        assertEquals(Player("Player 1"), game.moves[2].player)
    }

    @Test
    internal fun `should climb a ladder`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4)), Player.make(1))
        val player = game.players[0]
        game.move(Move(player, 2))
        assertEquals(4, player.currentPosition)
    }

    @Test
    internal fun `should climb multiple ladders`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4), Tunnel(4, 8)), Player.make(1))
        val player = game.players[0]
        game.move(Move(player, 2))
        assertEquals(8, player.currentPosition)
    }

    @Test
    internal fun `should get eaten by snakes`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(4, 2)), Player.make(1))
        val player = game.players[0]
        game.move(Move(player, 3))
        assertEquals(2, player.currentPosition)
    }

    @Test
    internal fun `should travel any tunnels`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4), Tunnel(8, 2)), Player.make(1))
        val player = game.players[0]
        game.move(Move(player, 2))
        assertEquals(4, player.currentPosition)

        game.move(Move(player, 4))
        assertEquals(2, player.currentPosition)
    }

    @Test
    internal fun `should be able to win`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4), Tunnel(8, 2)), Player.make(1))
        assertNull(game.winner)

        val player = game.players[0]
        game.move(Move(player, 6))
        game.move(Move(player, 3))

        assertEquals(player, game.winner)
        assertEquals(10, game.winner!!.currentPosition)
    }

    @Test
    internal fun `should roll exactly the right number for the final move`() {
        val game = SnakesAndLaddersGame(10, setOf(Tunnel(3, 4), Tunnel(8, 2)), Player.make(2))
        val player1 = game.players[0]
        val player2 = game.players[1]

        game.move(Move(player1, 6))
        game.move(Move(player2, 4))
        game.move(Move(player1, 6))
        // next player should roll
        assertEquals(1, game.nextPlayerIndex)

        game.move(Move(player2, 6))
        // next player should roll
        assertEquals(0, game.nextPlayerIndex)

        assertNull(game.winner)
        assertEquals(7, player1.currentPosition)
    }
}