package hw2;

/**
 * The implementaion of interface {@code Warrior}
 */
public class WarriorImplementation implements Warrior
{    
    private Queue shieldsQueue;

    @Override
    public boolean alive() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'alive'");
    }

    @Override
    public void addShield(int newShield) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addShield'");
    }

    @Override
    public boolean repel(int anAttack) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repel'");
    }

    @Override
    public boolean repel(AttackStrategy anAttackStrategy) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repel'");
    }

    @Override
    public int shields() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shields'");
    }

    @Override
    public int remainingPower() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remainingPower'");
    }

    public String toString() {
        String result = "w: ";
        result += shieldsQueue.toString();
        return result;
    }

    private String allInfo(AttackStrategy anAttackStrategy) {
        String allInfo = "";
        allInfo += anAttackStrategy.toString() + "\n";
        allInfo += toString() + "\n";
        allInfo += "shields: " + shields() + "\n";
        allInfo += "remainning power: " + remainingPower() + "\n\n";
        return allInfo;
    }

    @Override
    public int add(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }
}