package ds1;

import ds1.util.AVLTree;
import ds1.util.ListoverLinkedList;
import ds1.util.MaxHeapArray;
import ds1.util.Sequence;

public class ABlockchain implements Blockchain {
    // The blockchain is represented as a sequence of blocks
    protected final Sequence<Block> chain; 

    // add counters for successful and reverted transactions
    protected int successfulTransactionsCount;
    protected int revertedTransactionsCount;
    protected int returnedFees;
    // blocks management in O(log B)
    protected AVLTree<Block> blocksTree;
    protected Block currentBlock;
    // balance management is done via Balance interface
    // the implementation is done in BalanceImp but can change later
    protected Balance balance;
    protected int transactionsPerBlock;
    protected int blockCounter;

    // transaction pool using Max-Heap 
    protected MaxHeapArray transactionPool;

    protected int orderCounter;
    
    public ABlockchain(int transactionsPerBlock, int initialBalance) {
        this.chain = new ListoverLinkedList<Block>();
        this.transactionPool = new MaxHeapArray(10000); // initial capacity
        this.successfulTransactionsCount = 0;
        this.revertedTransactionsCount = 0;
        this.returnedFees = 0;
        // we may need the chain and the blocksTree
        this.transactionsPerBlock = transactionsPerBlock;
        this.balance = new BalanceImp();
        this.blockCounter = 0;
        this.blocksTree = new AVLTree<Block>();
        // insert genesis block into blocksTree
        createGenesisBlock(initialBalance);
        orderCounter = 0;
    }

    
    protected Block createGenesisBlock(int initialBalance) {
        Block genesis = new Block("-1", transactionsPerBlock, blockCounter++);
        chain.insertRear(genesis);
        blocksTree.insert(genesis);

        balance.updateBalance("0", initialBalance); // Infinite balance for genesis

        Block firstBlock = new Block("0", transactionsPerBlock, blockCounter++);
        chain.insertRear(firstBlock);
        blocksTree.insert(firstBlock);
        currentBlock = firstBlock;
        return genesis;
    }

    

    public void requestTransaction(String fromAddress, String toAddress, int amount, int fee) {
        if (amount <= 0 || fee < 0) {
            throw new IllegalArgumentException("Amount must be positive and fee non-negative");
        }        
        orderCounter++;
        TransactionWithOrder transaction = new TransactionWithOrder(fromAddress, toAddress, amount, fee, orderCounter);
        transactionPool.insert(transaction);
    }

    public void processCurrentBlockAndStartNewBlock() {
        processBlockTransactions();
        // Mark failed transactions as reverted
        createNewBlock();
    }

    protected void createNewBlock() {
        // Create new current block
        Block newBlock = new Block(currentBlock.getBlockHash(), 
                                 transactionsPerBlock, blockCounter++);
        chain.insertRear(newBlock);
        // also insert into blocksTree
        blocksTree.insert(newBlock);
        currentBlock = newBlock;
    }

    protected void processBlockTransactions() {
        // Execute transactions and update balances for current block
        TransactionWithFee[] transactions = currentBlock.getTransactions();
        for (TransactionWithFee transaction : transactions) {
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
        }
    }

    // Mine a new block from the transaction pool
    // It is similar to addBlock but selects transactions from the pool
    public boolean mineBlock() {
        int transactionsAdded = 0;
        // Fill the new block with transactions from the pool
        while (transactionsAdded < transactionsPerBlock && !transactionPool.isEmpty()) {
            TransactionWithFee transaction = transactionPool.extractMax(); // Get highest priority transaction
             // Remove from pool
            currentBlock.addTransaction(transaction);
            transactionsAdded++;
        }
        if (currentBlock.isFull()) {
            processCurrentBlockAndStartNewBlock();
            return true;
        }
        return false;
    }

        @Override
    public Block getBlock(int index) {
        if (index < 0 || index >= chain.length()) {
            throw new IndexOutOfBoundsException("Invalid block index");
        }
        return chain.at(index);
    }

    @Override
    public int size() {
        return chain.length();
    }

    @Override
    public Block getLastBlock() {
        if (chain.length() == 0) {
            throw new IllegalStateException("Blockchain is empty");
        }
        return chain.at(chain.length() - 1);
    }

    @Override
    public Sequence<Block> getBlocks() {
        return chain;
    }

    @Override
    public int getBalance(String address) {
        return balance.getBalance(address);
    }

    // Get block by its number in O(log B)
    public Block getBlockByNumber(int number) {
        // Create a dummy block with the given number for searching
        Block dummyBlock = new Block("0", transactionsPerBlock, number);
        Block  b = blocksTree.searchGet(dummyBlock);
        return b;
    }
    public int getSuccessfulTransactionsCount() {
        return successfulTransactionsCount;
    }
    public int getRevertedTransactionsCount() {
        return revertedTransactionsCount;
    }
    public int getReturnedFees() {
        return returnedFees;
    }


	public boolean repOK() {
        // Check basic invariants
        if (transactionsPerBlock <= 0 || blockCounter < 0) {
            return false;
        }

        // Check blocks in the tree
        Block[] blocks = getBlocks().toArray();
        for (Block block : blocks) {
            if (block.getTransactionCount() > transactionsPerBlock) {
                return false;
            }
        }
        // 2) Check: balances are non-negative
        for (String address : balance.getAllAddresses()) {
            if (balance.getBalance(address) < 0) {
                return false;
            }
        }
        // 3) Check: transaction pool is a valid max-heap
        transactionPool.repOK();

        return true;
    
    }

    public int getTransactionPoolSize() {
        return transactionPool.size();
    }
}
