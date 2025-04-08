package hw1;
 
public interface Warrior {

    boolean alive();

    void addShield(int newShield);
    
    boolean repel(AttackStrategy anAttackStrategy);
    
    boolean repel(int anAttack);
    
    int shields();

    int remainingPower();
}
