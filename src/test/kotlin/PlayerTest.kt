import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class PlayerTest {
    @Test
    internal fun `should make players with default names`() {
        val players = Player.make(3)
        assertEquals(players.size, 3)
        assertTrue(players.contains(Player("Player 1")))
        assertTrue(players.contains(Player("Player 2")))
        assertTrue(players.contains(Player("Player 3")))
    }
}