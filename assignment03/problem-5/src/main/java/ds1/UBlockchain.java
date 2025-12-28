package ds1;
import ds1.util.ListoverLinkedList;
import ds1.util.Sequence;

/** 
 * UBlockchain.java
 * This class represents an unoptimized blockchain implementation.
 * It extends the abstract ABlockchain class and provides concrete implementations
 * for creating the genesis block, processing blocks, and creating new blocks.
 * Students are expected to implement the missing methods.
 * Can use the inherited methods from ABlockchain for common functionality.
 * Or override them if needed.
 */

public class UBlockchain extends ABlockchain
{
    /* ============================= Fields ================================= */

    Sequence<String> stateRootHashList;

    /* =========================== Constructor ============================== */

    public UBlockchain(int transactionsPerBlock, int initialBalance) {
        super(transactionsPerBlock, initialBalance);
        stateRootHashList = new ListoverLinkedList<String>();
    }

    /* ============================ Getters ================================= */

    public String getStateMPTHash() { return balance.getStateHash(); }

    /* =========================== Modifiers ================================ */

    @Override
    protected Block createGenesisBlock(int initialBalance) {
        Block genesis = new Block(null, transactionsPerBlock, blockCounter++);
        chain.insertRear(genesis);
        blocksTree.insert(genesis);

        balance.updateBalance("0", initialBalance); // Infinite balance for genesis
        genesis.computeAndSetBlockHash(); // Hashing of genesis block is ""

        Block firstBlock = new Block(genesis.getBlockHash(), transactionsPerBlock, blockCounter++);
        chain.insertRear(firstBlock);
        blocksTree.insert(firstBlock);
        currentBlock = firstBlock;
        return genesis;
    }

    @Override
    protected void processBlockTransactions() {
        // Execute transactions and update balances for current block
        TransactionWithFee[] transactions = currentBlock.getTransactions();
        for (TransactionWithFee transaction : transactions) {
            System.out.println("Execute transaction: " + transaction.toString());
            String from = transaction.getFromAddress();
            String to = transaction.getToAddress();
            int amount = transaction.getAmount();
            int fromBalance = balance.getBalance(from);
            int fee = transaction.getFee();
            if (fromBalance >= amount + fee) {
                successfulTransactionsCount++;
                // Execute transaction
                balance.updateBalance(from, fromBalance - amount - fee);
                int toBalance = balance.getBalance(to);
                balance.updateBalance(to, toBalance + amount);
                balance.updateBalance("0", balance.getBalance("0") + fee); // collect fee
            } else {
                revertedTransactionsCount++;
                // Revert transaction
                if(fromBalance >= fee/2) {
                    balance.updateBalance(from, fromBalance - fee/2);
                    balance.updateBalance("0", balance.getBalance("0") + fee/2); // collect fee/2
                    returnedFees += fee/2;
                }
                transaction.revert();
            }
            System.out.println("Global State:        " + balance.toString());
        }
        // Retrieve the new stateRoot from the StateMPT
        String newStateRootHash = balance.getStateHash();
        // Save the new stateRoot for the Block.
        currentBlock.setStateRootHash(newStateRootHash);
        // Compute the hashing of current block
        currentBlock.computeAndSetBlockHash();
        // Record the hashing of stateRoot
        stateRootHashList.insertRear(newStateRootHash);
        System.out.println("current block hash:  " + currentBlock.getBlockHash());
    }

    @Override
    protected void createNewBlock() {
        // Create new current block
        Block newBlock = new Block(
            currentBlock.getBlockHash(), transactionsPerBlock, blockCounter++
        );
        chain.insertRear(newBlock);
        blocksTree.insert(newBlock);
        currentBlock = newBlock;
    }

    // Mine a new block from the transaction pool
    // It is similar to addBlock but selects transactions from the pool
    @Override
    public boolean mineBlock() { return super.mineBlock(); }

    /* ========================== Class Invariant =========================== */

    @Override
    /**
     * You can use part of old repOK and adapt it to the new structure
     * Time complexity: O(B), where B is the number of blocks in the chain
     **/
	public boolean repOK() {
        return stateRootInvariant() && hashInvariant(chain) && lastBlockValidity();
    }

    // O(1)
    private boolean stateRootInvariant() {
        boolean isSatisfied = true;
        int i = 1;
        while ( isSatisfied && i < stateRootHashList.length() ) {
            String stateRootHashInBlock = (String) chain.at(i).getStateRootHash();
            String stateRootHashInList = stateRootHashList.at(i-1);
            if ( stateRootHashInBlock.equals(stateRootHashInList) ) i++;
            else isSatisfied = false;
        }
        return isSatisfied;
    }

    // O(B)
    private boolean hashInvariant(Sequence<Block> chain) {
        boolean isSatisfied = true;
        int i = 1;
        while ( isSatisfied && i < chain.length() ) {
            Block prevBlock = chain.at(i-1);    
            Block currBlock = chain.at(i);
            if ( currBlock.getPreviousHash().equals(prevBlock.getBlockHash()) ) i++;
            else isSatisfied = false;
        }
        return isSatisfied;
    }

    // O(1)
    private boolean lastBlockValidity() {
        int currBlockNumber = currentBlock.getBlockNumber();
        if ( currBlockNumber == 1 ) return true; // chain has just been initialized
        else return chain.at(currBlockNumber - 1).getStateRootHash() == balance.getStateHash();
    }
}