import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class MoveTest {
    @Test
    internal fun `should only accept rolledNumber within a dice roll range`() {
        assertThrows<IllegalArgumentException> { Move(Player("a player"), 7) }
        assertThrows<IllegalArgumentException> { Move(Player("a player"), -3) }
        assertThrows<IllegalArgumentException> { Move(Player("a player"), 0) }

        Move(Player("a player"), 3)
    }
}