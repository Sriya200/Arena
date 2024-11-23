import java.util.Random;

public class MagicalArena {
    static Random randomGenerator = new Random();

    public static int rollDice() {
        return randomGenerator.nextInt(6) + 1;
    }

    public static void engageCombat(Player attacker, Player defender) {
        int attackRoll = rollDice();
        int defenseRoll = rollDice();

        int totalAttack = attacker.attack * attackRoll;
        int totalDefense = defender.strength * defenseRoll;

        int damageInflicted = Math.max(0, totalAttack - totalDefense);
        defender.health -= damageInflicted;

        System.out.println("Attacker dice rolled: " + attackRoll + ", Defender dice rolled: " + defenseRoll);
        System.out.println("Total attack: " + totalAttack + ", Total defense: " + totalDefense);
        System.out.println("Defender takes " + damageInflicted + " damage, health now: " + defender.health + "\n");
    }

    public static void main(String[] args) {
        Player warriorOne = new Player(50, 5, 10);
        Player warriorTwo = new Player(100, 10, 5);

        while (warriorOne.health > 0 && warriorTwo.health > 0) {
            if (warriorOne.health < warriorTwo.health) {
                engageCombat(warriorOne, warriorTwo);
                if (warriorTwo.health <= 0) {
                    System.out.println("Warrior One is victorious!");
                    break;
                }
                engageCombat(warriorTwo, warriorOne);
                if (warriorOne.health <= 0) {
                    System.out.println("Warrior Two is victorious!");
                    break;
                }
            } else {
                engageCombat(warriorTwo, warriorOne);
                if (warriorOne.health <= 0) {
                    System.out.println("Warrior Two is victorious!");
                    break;
                }
                engageCombat(warriorOne, warriorTwo);
                if (warriorTwo.health <= 0) {
                    System.out.println("Warrior One is victorious!");
                    break;
                }
            }
        }
    }
}

class Player {
    int health;
    int attack;
    int strength;

    public Player(int health, int strength, int attack) {
        this.health = health;
        this.attack = attack;
        this.strength = strength;
    }
}
