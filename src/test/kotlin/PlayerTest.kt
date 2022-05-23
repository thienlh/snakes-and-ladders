import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class PlayerTest {
    @Test
    internal fun `should make players with default names`() {
        val players = Player.make(3)
        assertEquals(players.size, 3)
        assertTrue(players.contains(Player("Player 1")))
        assertTrue(players.contains(Player("Player 2")))
        assertTrue(players.contains(Player("Player 3")))
    }

    @Test
    internal fun `should start at square 1`() {
        assertEquals(Player("a player").currentPosition, 1)
    }

    @Test
    internal fun `should advance position`() {
        val player = Player("a player")
        player.advance(3)
        assertEquals(player.currentPosition, 4)
        player.advance(2)
        assertEquals(player.currentPosition, 6)
        assertThrows<IllegalArgumentException> { player.advance(-3)  }
        assertThrows<IllegalArgumentException> { player.advance(0)  }
        assertThrows<IllegalArgumentException> { player.advance(7)  }
    }
}