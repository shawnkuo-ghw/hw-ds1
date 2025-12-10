package ds1;
import ds1.util.*;

public class ABlockchain implements Blockchain
{
    /* ===================== Fields and Constructor ========================= */

    private final PriorityQueue transactionsPool; // transaction pool
    private final AVLTree<Integer, Block> blocks; // blocks in chain
    private final Balance balances;               // record of balances of each address
    private final int transactionsPerBlock;       // transaction per block
    private final int initialBalance;             // initial balance
    private int transactionsCounter;              // counter of transaction
    private int successfulTransactionsCount;      // total number of successful transactions
    private int revertedTransactionsCount;        // total number of successful transactions
    private int returnedFees;                     // total amount fees returned due to reverted transactions
    private Block currBlock;                      // current block yet to add in the chain

    public ABlockchain(int transactionsPerBlock, int initialBalance) {
        transactionsPool = new PriorityQueue();
        balances = new BalanceImp();
        blocks = new AVLTreeImp<Integer, Block>(AVLNode.class, Block.class);
        this.transactionsPerBlock = transactionsPerBlock;
        this.initialBalance = initialBalance;
        this.transactionsCounter = 0;
        this.successfulTransactionsCount = 0;
        this.revertedTransactionsCount = 0;
        this.returnedFees = 0;
        // genesis block (hash = 1, number = 0)
        this.currBlock = new Block(0, 1, 0);
        // initialize 0-address and genesis block
        requestTransaction("0", "0", initialBalance, 0);
        boolean genesisMined = mineBlock();
        if ( !genesisMined )
            throw new IllegalStateException("ABlockchain(): fail to init genesis block.");
    }

    /* ============================ Modifiers =============================== */

    /**
     * Time complexity: O(log TP), where TP is the number of transactions in the pool
     * Explaination:
     * <li> - In the implementation of this method, {@code transactionPool.enqueue} is called </li>
     * <li> - {@code transactionPool.enqueue} is implemented by calling {@code GenericMaxPQ.enqueue},
     *        whose time complexity is O(log N) </li>
     * <li> - In this case, the number of elements in priority queue is TP, that means N = TP,
     *        therefore the time complexity of {@code requestTransaction} is O(log TP) </li>
     * @see ds1.util.PriorityQueue#enqueue(TransactionWithFee)
     * @see ds1.util.GenericMaxPQ#enqueue(Comparable)
     */
    @Override
    public void requestTransaction(String fromAddress, String toAddress, int amount, int fee) {
        if ( fromAddress == null || toAddress == null )
            throw new IllegalArgumentException("Addresses must not be null");
        if ( fromAddress.isEmpty() || toAddress.isEmpty() )
            throw new IllegalArgumentException("Addresses must not be empty");
        if ( fee < 0 )
            throw new IllegalArgumentException("Fee must be non-negative");
        if ( amount <= 0 )
            throw new IllegalArgumentException("Amount must be positive");
        TransactionWithOrder t = new TransactionWithOrder(
            fromAddress, toAddress, amount, 
            fee, ++transactionsCounter
        );
        transactionsPool.enqueue(t); // O(log TP)
    }

    /**
     * Time complexity: O(TB * (log TP + log A)), where
     * TB is the number of transactions per block,
     * TP is the number of transactions in the pool and
     *  A is the number of address with balance.
     * <p> Explaination: </p>
     * <li> - Firstly, A is larger than TB, since to get A many addre</li>
     * @see ds1.ABlockchain#processCurrentBlockAndStartNewBlock()
     */
    @Override
    public boolean mineBlock() {
        boolean mined = true;
        // 1. Selects transactions from the transaction pool based on priority to fill the current block
        while ( !currBlock.isFull() && !transactionsPool.isEmpty() ) // TB * O(log TP + log TB) = O(TB * (log TP + log TB))
        {
            TransactionWithFee transWithOrder = transactionsPool.dequeue(); // O(log TP)
            TransactionWithFee transWithOutOrder = new TransactionWithFee(
                transWithOrder.getFromAddress(),
                transWithOrder.getToAddress(),
                transWithOrder.getAmount(),
                transWithOrder.getFee()
            );
            currBlock.addTransaction(transWithOutOrder); // O(log TB) = O(log A), since TB <= A
        }
        // 2. If the block becomes full
        if ( currBlock.isFull() ) processCurrentBlockAndStartNewBlock(); // O(TB * log A)
        // 3. Otherwise returns false
        else mined = false;
        return mined;
    }

    /**
     * Time complexity: O(TB * log A)
     * <p> Explaination: </p>
     * <li> - There are TB many transactions waiting to be executed </li>
     * <li> - For each transaction being executed, the worst time complexity is reached 
     *        when transaction succeeds, where {@code balances.updateBalance} is called 
     *        three times </li>
     * <li> - The time complexity of {@code balances.updateBalance} is O(log A) </li>
     * <li> - Therefore the overall time complexity is TB * 3 * O(log A) = O(TB * log A) </li>
     * @see ds1.BalanceImp#updateBalance(String, int)
     */
    @Override
    public void processCurrentBlockAndStartNewBlock() {
        TransactionWithFee[] currTransactions = currBlock.getTransactions(); // O(TB * log TB)
        // process all transactions in the current block
        for ( TransactionWithFee t: currTransactions ) { // TB * O(log A) = O(TB * log A)
            String fromAddress = t.getFromAddress();
            String toAddress   = t.getToAddress();
            int amount         = t.getAmount();
            int fee            = t.getFee();
            int zeroAddBalacne = balances.getBalance("0");
            int fromAddBalance = balances.getBalance(fromAddress);
            int toAddBalance   = balances.getBalance(toAddress);
            // init genesis block
            if ( fromAddress.equals("0") && toAddress.equals("0") && blocks.size() == 0 )
                balances.updateBalance("0", initialBalance);
            // transaction succeeds
            else if ( fromAddBalance >= amount + fee ) {
                balances.updateBalance(fromAddress, fromAddBalance - (amount + fee)); // O(log A)
                balances.updateBalance(toAddress, toAddBalance + amount); // O(log A)
                zeroAddBalacne = balances.getBalance("0");
                balances.updateBalance("0", zeroAddBalacne + fee); // O(log A)
                successfulTransactionsCount++;
            // transaction fails
            } else {
                if ( fromAddBalance >= fee ) {
                    balances.updateBalance(fromAddress, fromAddBalance - fee / 2); // O(log A)
                    balances.updateBalance("0", zeroAddBalacne + (fee - fee / 2)); // O(log A)
                    returnedFees += fee / 2;
                }
                t.revert();
                revertedTransactionsCount++;
            }
        }
        // add the current block into block chain
        blocks.insertTree(currBlock.getBlockNumber(), currBlock);
        // start a new block
        currBlock = new Block(
            currBlock.getBlockHash(), 
            transactionsPerBlock, 
            currBlock.getBlockNumber() + 1
        );
        // System.out.println(balances.toString());
        if ( !repOK() )
            throw new IllegalStateException(
                "ABlockchain.processCurrentBlockAndStartNewBlock(): RI is not satisfied."
            );
    }

    /* =========================== Getters ================================== */

    /**
     * Time complexity: O(log B), where B is the number of blocks
     * <p> Explaination: </p>
     * <li> - This method is implemented by calling {@code searchTree}, whose time complexity is O(log B)</li>
     * @see ds1.util.AVLTree#searchTree(Comparable)
     */
    @Override
    public Block getBlock(int index) {
        if ( index < 0 || index >= size() ) 
            throw new IndexOutOfBoundsException("index out of range");
        if ( index == size() - 1 ) return currBlock; // the last one is the current block
        else return blocks.searchTree(index);
    }

    /**
     * Time complexity: O(log B)
     */
    @Override
    public Block getBlockByNumber(int number) {
        if ( number < 0 || number >= size() )
            throw new IndexOutOfBoundsException("number out of range");
        if ( number == size() - 1 ) return currBlock; // the last one is the current block
        else return blocks.searchTree(number);
    }

    @Override
    public int size() { return blocks.size() + 1; } // 1 for the current block

    @Override
    public Block getLastBlock() { return currBlock; }

    @Override
    public Sequence<Block> getBlocks() {
        Block[] blocksArr = blocks.toArray();
        Sequence<Block> blocksSeq = new ListoverLinkedList<Block>(Block.class);
        for ( int i = 0; i < blocks.size(); i++ ) blocksSeq.insertRear(blocksArr[i]);
        return blocksSeq;
    }

    /**
     * Time complexity: O(log A)
     * <p> Explaination: </p>
     * <li> - this method is simply implemented by calling {@code balances.getBalance} </li>
     * <li> - the time complexity of {@code balances.getBalance} is O(log A) </li>
     * @see ds1.Balance#getBalance(String)
     */
    @Override
    public int getBalance(String address) { return balances.getBalance(address); }

    @Override
    public int getTransactionPoolSize() { return transactionsPool.size(); }

    @Override
    public int getSuccessfulTransactionsCount() { return successfulTransactionsCount; }

    @Override
    public int getRevertedTransactionsCount() { return revertedTransactionsCount; }

    @Override
    public int getReturnedFees() { return returnedFees; }

    /* =================== Representation Invariant ========================= */

	public boolean repOK() {
        // if ( !correctHashLinking() )
        //     System.out.println("hash link");
        // if ( !allBlocksHaveSameTransactionCapacity() )
        //     System.out.println("same transaction capcity");
        // if ( !blockNumbersStrictlyIncrease() )
        //     System.out.println("block number");
        return 
            correctHashLinking() &&
            allBlocksHaveSameTransactionCapacity() &&
            blockNumbersStrictlyIncrease();
    }

    private boolean correctHashLinking() {
        boolean isCorrect = true;
        Block[] blocksArrary = blocks.toArray();
        int length = blocksArrary.length;
        int i = 1; // start from the second block
        while ( isCorrect && i < length ) {
            int prevHash = blocksArrary[i-1].getBlockHash();
            int currHash = blocksArrary[i].getBlockHash();
            if ( prevHash != currHash - 1 )
                isCorrect = false;
            else
                i++;
        }
        return isCorrect;
    }

    private boolean allBlocksHaveSameTransactionCapacity() {
        boolean isCorrect = true;
        Block[] blocksArray = blocks.toArray();
        int length = blocksArray.length;
        int i = 1; // skip genesis block
        while ( isCorrect && i < length ) {
            Transaction[] currBlockTransactions = blocksArray[i].getTransactions();
            if ( currBlockTransactions.length != transactionsPerBlock )
                isCorrect = false;
            else
                i++;
        }
        return isCorrect;
    }
    
    private boolean blockNumbersStrictlyIncrease() {
        boolean isCorrect = true;
        Block[] blocksArray = blocks.toArray();
        int length = blocksArray.length;
        int i = 1; // skip genesis block
        while ( isCorrect && i < length ) {
            int prevBlockNumber = blocksArray[i-1].getBlockNumber();
            int currBlockNumber = blocksArray[i].getBlockNumber();
            if ( prevBlockNumber >= currBlockNumber )
                isCorrect = false;
            else
                i++;
        }
        return isCorrect;
    }
}