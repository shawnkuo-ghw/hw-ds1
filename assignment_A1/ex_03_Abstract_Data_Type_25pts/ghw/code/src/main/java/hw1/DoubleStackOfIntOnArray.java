package hw1;

public class DoubleStackOfIntOnArray implements DoubleStackOfInt {
    private int[] arr;
    private int lhead;
    private int rhead;

    public DoubleStackOfIntOnArray() {}
    public DoubleStackOfIntOnArray(int size) {}
    
    @Override
    public void push_head(int elem) {}
    @Override
	public void push_tail(int elem) {}
    @Override
	public void pop_head() {}
    @Override
    public void pop_tail() {}
    @Override
	public boolean empty_head() { return true; }
    @Override
	public boolean empty_tail() { return true; }
    @Override
    public int top_head() { return 0; }
    @Override
    public int top_tail() { return 0; }
}