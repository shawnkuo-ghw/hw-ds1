package hw1;
 
public interface AttackStrategy {

    void add(int newAttack); // add new attack into attack strategy
    
    boolean isEmpty();  // check whether attack stategy is empty
    
    void setTopAttack(int newAttack); // set the top attack
    
    int getTopAttack();   // get the top attack

    int popAttack();   // pop the top attack
}
