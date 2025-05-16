package ex04.classes.implementations;

import ex04.classes.interfaces.ArmySquadron;
import ex04.classes.interfaces.AttackStrategy;
import ex04.classes.interfaces.Warrior;
import ex04.collections.implementations.maxHeap;
import ex04.collections.interfaces.Heap;

public class ArmySquadronImplementation implements ArmySquadron {

    private static final int MAX = 100;

    Heap<Warrior> squadron;

    public ArmySquadronImplementation() { squadron = new maxHeap<Warrior>(); }

    @Override
    public Warrior next() { return squadron.top(); }

    @Override
    public void addWarrior(Warrior w) { squadron.push(w); }

    @Override
    public Warrior popWarrior() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'popWarrior'");
    }

    @Override
    public int repel(AttackStrategy[] attackStrategies) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'repel'");
    }    
}
