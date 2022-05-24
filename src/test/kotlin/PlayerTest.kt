import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {
    @Test
    internal fun `should make players with default names`() {
        val players = Player.make(3)
        assertEquals(3, players.size)
        assertTrue(players.contains(Player("Player 1")))
        assertTrue(players.contains(Player("Player 2")))
        assertTrue(players.contains(Player("Player 3")))
    }

    @Test
    internal fun `should start at square 1`() {
        assertEquals(1, Player("a player").currentPosition)
    }

    @Test
    internal fun `should advance position`() {
        val player = Player("a player")
        player.advance(3)
        assertEquals(4, player.currentPosition)
        player.advance(2)
        assertEquals(6, player.currentPosition)
        assertThrows<IllegalArgumentException> { player.advance(-3) }
        assertThrows<IllegalArgumentException> { player.advance(0) }
    }

    @Test
    internal fun `should travel to any positive square`() {
        val player = Player("a player")
        player.travelTo(100)
        assertEquals(100, player.currentPosition)

        assertThrows<IllegalArgumentException> { player.travelTo(-3) }
        assertThrows<IllegalArgumentException> { player.travelTo(0) }
    }
}