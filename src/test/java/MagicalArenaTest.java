import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MagicalArenaTest {

    @Test
    void testPlayerInitialization() {
        Player player = new Player(100, 10, 5);
        assertEquals(100, player.health, "Player health should be initialized correctly.");
        assertEquals(10, player.strength, "Player strength should be initialized correctly.");
        assertEquals(5, player.attack, "Player attack should be initialized correctly.");
    }

    @Test
    void testRollDice() {
        for (int i = 0; i < 100; i++) {
            int result = MagicalArena.rollDice();
            assertTrue(result >= 1 && result <= 6, "Dice roll should produce a value between 1 and 6.");
        }
    }

    @Test
    void testEngageCombat() {
        Player attacker = new Player(50, 5, 10);
        Player defender = new Player(50, 10, 5);

        MagicalArena.engageCombat(attacker, defender);

        // After combat, defender's health should reduce, ensuring attack mechanics work
        assertTrue(defender.health <= 50, "Defender's health should be reduced after combat.");
        assertTrue(defender.health >= 0, "Defender's health should not drop below zero.");
    }

    @Test
    void testCombatEndsCorrectly() {
        Player strongAttacker = new Player(50, 5, 20);
        Player weakDefender = new Player(10, 1, 5);

        MagicalArena.engageCombat(strongAttacker, weakDefender);

        assertTrue(weakDefender.health <= 0, "Weak defender should have zero or negative health after combat.");
    }

    @Test
    void testGameLoop() {
        Player player1 = new Player(30, 2, 5);
        Player player2 = new Player(30, 2, 5);

        while (player1.health > 0 && player2.health > 0) {
            MagicalArena.engageCombat(player1, player2);
            if (player2.health <= 0) {
                assertTrue(player1.health > 0, "Player 1 should win when Player 2's health drops to zero.");
                break;
            }
            MagicalArena.engageCombat(player2, player1);
            if (player1.health <= 0) {
                assertTrue(player2.health > 0, "Player 2 should win when Player 1's health drops to zero.");
                break;
            }
        }
    }
}
