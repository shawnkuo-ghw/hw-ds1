package ds1;

import ds1.util.Sequence;
import ds1.util.SequenceIterator;

public class UBlockchain extends ABlockchain {

    public UBlockchain(int transactionsPerBlock, int initialBalance) {
        super(transactionsPerBlock, initialBalance); 
    }

    @Override
    protected Block createGenesisBlock(int initialBalance) {
        Block genesis = new Block("", transactionsPerBlock, blockCounter++);
        chain.insertRear(genesis);
        blocksTree.insert(genesis);

        balance.updateBalance("0", initialBalance); // Infinite balance for genesis

        Block firstBlock = new Block(genesis.getBlockHash(), transactionsPerBlock, blockCounter++);
        chain.insertRear(firstBlock);
        blocksTree.insert(firstBlock);
        currentBlock = firstBlock;
        return genesis;
    }

    @Override
    public void processCurrentBlockAndStartNewBlock() {
        super.processCurrentBlockAndStartNewBlock();
    }

    @Override
    protected void processBlockTransactions() {
        super.processBlockTransactions();
    }

    @Override
    protected void createNewBlock() {
        // After processing, update the state root hash of the current block
        currentBlock.setStateRootHash(getStateMPTHash());
        String blockHash = currentBlock.computeAndSetBlockHash();
        // Create new current block
        Block newBlock = new Block(blockHash, 
                                 transactionsPerBlock, blockCounter++);
        chain.insertRear(newBlock);
        // also insert into blocksTree
        blocksTree.insert(newBlock);
        currentBlock = newBlock;
    }

    // Mine a new block from the transaction pool
    // It is similar to addBlock but selects transactions from the pool
    @Override
    public boolean mineBlock() {
        return super.mineBlock();
    }

    @Override
	public boolean repOK() {
        if(!super.repOK()) {
            return false;
        }

        // Additional checks for UBlockchain
        // 1) Check blockhash consistency in the chain against previous hashes
        Block blockBeforeCurrent = null;
        for (int i = 1; i < chain.length(); i++) {
            Block current = chain.at(i);
            Block previous = chain.at(i - 1);
            if (!current.getPreviousHash().equals(previous.getBlockHash())) {
                return false;
            }
            blockBeforeCurrent = previous;
        }


        // 2) Check state MPT hash consistency
        // beforeCurrent is the previous block of currentBlock
        // blockBeforeCurrent's state root hash should match the expected state hash
        // Only check is the blockBeforeCurrent is full (settled)
        String expectedStateHash = balance.getStateHash();
        if (blockBeforeCurrent.isFull() && !blockBeforeCurrent.getStateRootHash().equals(expectedStateHash)) {
            return false;
        }
        // 3) Check: currentBlock is the last block in the chain
        if (currentBlock != getLastBlock()) {
            return false;
        }
        
        return true;        

    }
    public String getStateMPTHash() {
        return balance.getStateHash();
    }
    /** 
     * Extend here with methods to support BlockchainAnalytics
     **/
   
    public String[] getAllAdresses() {
        return balance.getAllAddresses();
    }
    
    public Transaction[] getSuccessfulTransactions() {
        SequenceIterator<Block> blockItr = chain.getIterator();
        Transaction[] successfulTransactions = new Transaction[getSuccessfulTransactionsCount()];
        int i = 0;
        while ( blockItr.hasNext() ) {
            Block currBlock = blockItr.next();
            Transaction[] currTransactions = currBlock.getTransactions();
            for (Transaction t : currTransactions) {
                if ( !t.isReverted() ) { successfulTransactions[i++] = t; }
            }
        }
        return successfulTransactions;
    }
}