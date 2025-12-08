package ds1;

import ds1.util.Sequence;

public class ABlockchain implements Blockchain {
    
    public ABlockchain(int transactionsPerBlock, int initialBalance) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

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

    @Override
    public Block getBlock(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBlock'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
    public Block getLastBlock() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLastBlock'");
    }

    @Override
    public Sequence<Block> getBlocks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBlocks'");
    }

    @Override
    public int getBalance(String address) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }

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
