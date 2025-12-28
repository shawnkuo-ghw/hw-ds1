package ds1;

import ds1.util.AVLTree;
import ds1.util.MaxHeapArray;
import ds1.util.StateMPT;

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
    /* =========================== Constructor ============================== */

    public UBlockchain(int transactionsPerBlock, int initialBalance) {
        super(transactionsPerBlock, initialBalance); 
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
    public void processCurrentBlockAndStartNewBlock() {
        processBlockTransactions();
        createNewBlock();
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

    @Override
    /** You can use part of old repOK and adapt it to the new structure
     *      
    **/
	public boolean repOK() {
        return true;
    }
}