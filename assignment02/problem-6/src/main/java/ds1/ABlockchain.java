package ds1;
import ds1.util.*;

public class ABlockchain implements Blockchain
{
    /* ===================== Fields and Constructor ========================= */

    private final PriorityQueue transactionsPool; // transaction pool
    private final AVLTree<Integer, Block> blocks; // blocks in chain, key = order of insertion
    private final int transactionsPerBlock; // transaction per block
    private final int initialBalance;       // initial balance
    private final Balance balances;         // record of balances of each address
    private int transactionsCounter;        // counter of transaction
    private Block currBlock;                // current block yet to add in the chain
    private Block prevBlock;                // last added block in the chain

    public ABlockchain(int transactionsPerBlock, int initialBalance) {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestTransaction'");
    }    

    /* ============================ Modifiers =============================== */

    @Override
    public void requestTransaction(String fromAddress, String toAddress, int amount, int fee) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'requestTransaction'");
    }

    @Override
    public boolean mineBlock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mineBlock'");
    }

    @Override
    public void processCurrentBlockAndStartNewBlock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'processCurrentBlockAndStartNewBlock'");
    }

    /* =========================== Getters ================================== */

    @Override
    public Block getBlock(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBlock'");
    }

    @Override
    public int size() { return blocks.size(); }

    @Override
    public Block getLastBlock() { return prevBlock; }

    @Override
    public Sequence<Block> getBlocks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBlocks'");
    }

    /**
     * Time complexity: O(log A)
     * Explaination:
     * <li> - this method is simply implemented by calling {@code balances.getBalance()}</li>
     * <li> - the time complexity of {@code balances.getBalance()} is O(log A)
     * @see ds1.BalanceImp#getBalance(String)
     */
    @Override
    public int getBalance(String address) { return balances.getBalance(address); }

    @Override
    public int getTransactionPoolSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTransactionPoolSize'");
    }

    @Override
    public Block getBlockByNumber(int number) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBlockByNumber'");
    }

    @Override
    public int getSuccessfulTransactionsCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSuccessfulTransactionsCount'");
    }

    @Override
    public int getRevertedTransactionsCount() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRevertedTransactionsCount'");
    }

    @Override
    public int getReturnedFees() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getReturnedFees'");
    }

	public boolean repOK() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'repOK'");
	}    
}
