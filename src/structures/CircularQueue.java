package structures;

public class CircularQueue {
    private int[] queue;
    private int front, rear, size, capacity;

    public CircularQueue(int capacity) {
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = capacity - 1;
        this.capacity = capacity;
    }

    public boolean isFull(){
        return this.size == this.capacity;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void enqueue(int item){
        if(isFull()){
            throw new CircularQueueException("A lista está cheia");
        }
        this.rear = (this.rear + 1) % this.capacity;
        this.queue[rear] = item;
        this.size++;
    }

    public int dequeue(){
        if(isEmpty()){
            throw new CircularQueueException("A lista está vazia");
        }
        int item = this.queue[this.front];
        front = (front + 1) % this.capacity;
        size--;
        return item;
    }

    public int peek(){
        if(isEmpty()){
            throw new CircularQueueException("Não há valores pois a lista está vazia");
        }
        return queue[front];
    }

    public boolean isInQueue(int item) {
        if (isEmpty()) return false;

        int count = 0;
        int index = front;

        while (count < size) {
            if (queue[index] == item) {
                return true;
            }
            index = (index + 1) % capacity;
            count++;
        }

        return false;
    }

    public int searchItemByAbsoluteIndex(int index){
        return this.queue[index];
    }
}
