// SBlockChain.java

package ds1;
import java.util.NoSuchElementException;

import ds1.utils.*;

/** 
 * Implementation of a simple blockchain using sequences and balances.
 * - Each block contains a set of transactions, and the blockchain maintains a balance for each address.
 * - The blockchain supports processing transactions, adding new blocks, and verifying its integrity through the repOK method.
 * - Do not forget to implement all methods from the Blockchain interface.
 * - Do not forget the genesis block creation.
 * - Respect the runtime complexities indicated in the assignment. 
*/
public class SBlockchain implements Blockchain
{
    /**********
     * Fields *
     **********/
    private final Sequence<Block> blocks;   // chain of blocks
    private final Balance balances;         // set of balances for each address
    private final int transactionsPerBlock; // number of transactions for each block
    private final int initialBalance;       // initial balence for genesis block
    private Block currBlock;

    /***************
     * Constructor *
     ***************/
    public SBlockchain(int transactionsPerBlock, int initialBalance)
    {
        this.transactionsPerBlock = transactionsPerBlock;
        this.initialBalance = initialBalance;
        blocks = new ListoverLinkedList<Block>();
        balances = new BalanceImp();
        currBlock = new Block(0, 1, 0); // genesis block
        processTransaction("0", "0", initialBalance);
    }

    /**************
     * Operations *
     **************/
    @Override
    public void processTransaction(String fromAddress, String toAddress, int amount)
    {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        // 1. Adds transaction to current block
        Transaction newTransaction = new Transaction(fromAddress, toAddress, amount);
        currBlock.addTransaction(newTransaction);
        // 2. If block becomes full, automatically calls addBlock()
        if ( currBlock.isFull() ) {
            addBlock();
        }
    }

    @Override
    public void addBlock()
    {
        if(size() != transactionsPerBlock) {
            throw new IllegalStateException("the block is not full");
        }
        // 1. Execute all transactions in current block
        Transaction[] currTransactions = currBlock.getTransactions();
        String fromAddress = "";
        String toAddress = "";
        int transactionAmount = 0;
        int fromAddressBalance = 0;
        int toAddressBalance = 0;
        for (Transaction t: currTransactions) { // O(T|A|)
            fromAddress = t.getFromAddress();
            toAddress = t.getToAddress();
            transactionAmount = t.getAmount();            
            fromAddressBalance = balances.getBalance(fromAddress); // O(|A|)
            toAddressBalance = balances.getBalance(toAddress); // O(|A|)
            if (blocks.length() == 0) { // currBlock is the genesis block
                balances.updateBalance("0", initialBalance);
            } else if (transactionAmount <= fromAddressBalance) {
                if (!fromAddress.equals(toAddress)) {
                    balances.updateBalance(fromAddress, fromAddressBalance - transactionAmount); // O(|A|)
                    balances.updateBalance(toAddress, toAddressBalance + transactionAmount); // O(|A|)
                }
            } else {
                t.revert();
            }
        }
        // 2. Append current block to chain
        blocks.insertRear(currBlock);
        // 3. Contruct a new current block
        currBlock = new Block(currBlock.getBlockHash(), transactionsPerBlock, currBlock.getBlockNumber() + 1);
        if ( !repOK() ) {
            throw new IllegalStateException("SBlockchain.addBlock(): repOK() is false.");
        }
    }
    
    // Other interface methods implementation...
    // Runtime complexity not relevant for repOK
    public boolean repOK()
    {
        // 1) Check: (initial) balance equals sum of all non-reverted transactions
        boolean checkItem01 = true;
        int sumOfBalances = 0;
        String[] allAddresses = balances.getAllAddresses();
        for (String address: allAddresses) { sumOfBalances += balances.getBalance(address); }
        checkItem01 = (initialBalance == sumOfBalances);
        if ( !checkItem01 ) {
            System.out.println("01 is false.");
        }
        
        // 2) Check: correct hash linking (previousHash == currentHash - 1)
        boolean checkItem02 = true;
        int length = blocks.length();
        if (length > 1) {
            int i = 1;
            int prevHash = 0;
            int currHash = 0;
            while (checkItem02 && i < length) {
                prevHash = blocks.at(i-1).getBlockHash();
                currHash = blocks.at(i).getBlockHash();
                if (prevHash != currHash - 1) {
                    checkItem02 = false;
                } else {
                    i++;
                }
            }
        }
        int lastHash = blocks.at(length-1).getBlockHash();
        int currBlockHash = currBlock.getBlockHash();
        checkItem02 = checkItem02 && (lastHash == currBlockHash - 1);
        if ( !checkItem02 ) {
            System.out.println("02 is false.");
        }        

        // 3) Check: all blocks have same transaction capacity
        boolean checkItem03 = true;
        length = blocks.length();
        if (length > 1) {
            int i = 1; // start from the first block and skip the genesis block
            while (checkItem03 && i < length) {
                Transaction[] blockTransactions = blocks.at(i).getTransactions();
                int transactionsCapacity = blockTransactions.length;
                if (transactionsCapacity != transactionsPerBlock) {
                    checkItem03 = false;
                } else {
                    i++;
                }
            }
        }
        if ( !checkItem03 ) {
            System.out.println("03 is false.");
        }

        // 4) Check: block numbers are strictly increasing
        boolean checkItem04 = true;
        length = blocks.length();
        int i = 1;
        int currBlockNumber = 0;
        int prevBlockNumber = 0;
        while (checkItem04 && i < length) {
            currBlockNumber = blocks.at(i).getBlockNumber();
            prevBlockNumber = blocks.at(i-1).getBlockNumber();
            if (prevBlockNumber >= currBlockNumber) {
                checkItem04 = false;
            } else {
                i++;
            }
        }
        prevBlockNumber = blocks.at(i-1).getBlockNumber();
        currBlockNumber = currBlock.getBlockNumber();
        checkItem04 = checkItem04 && (currBlockNumber > prevBlockNumber);
        if ( !checkItem04 ) {
            System.out.println("04 is false.");
        }

        // The overall result of repOK
        return checkItem01 && checkItem02 && checkItem03 && checkItem04;
    }

    /***********
     * Getters *
     ***********/
    @Override
    public Block getBlock(int index)
    {
        if ( index < 0 || index >= size() ) {
            throw new IndexOutOfBoundsException("SBlockchain.getBlock(): index is invalid.");
        }
        if ( index == size() - 1 ) {
            return currBlock;
        } else {
            return blocks.at(index);
        }
    }

    @Override
    public int size() {
        return blocks.length() + 1; // 1 for current block that has not been appended into the block chain
    }

    @Override
    public Block getLastBlock() {
        return currBlock;
    }

    @Override
    public Sequence<Block> getBlocks() {
        return blocks;
    }

    @Override
    public int getBalance(String address) {
        return balances.getBalance(address);
    }

    @Override
    public String toString() {
        String strRep = "------------ Block Chain ------------\n\n";
        strRep += balances.toString();
        strRep += "Initial Balance: " + initialBalance + "\n";
        strRep += "Transactions Per Block: " + transactionsPerBlock + "\n\n";
        strRep += blocks.toString();
        strRep += "Current Block: \n" + currBlock.toString();
        strRep += "-------------------------------------\n";
        return strRep + "\n";
    }
}