package Bases;

import java.util.Stack;

public class ObjectPool<T> {

    public interface CreateConstructor<T>
    {
        public T newObject();
    }

    public Stack<T> objectStack;
    public CreateConstructor<T> constructor;
    public int maxSize;

    /**
     *
     * @param constructor
     * @param maxSize 池最大容量
     */
    public ObjectPool(CreateConstructor<T> constructor,int maxSize)
    {
        this.constructor=constructor;
        this.maxSize=maxSize;
        this.objectStack=new Stack<T>();
    }

    public T creat()
    {
        if(objectStack.isEmpty())
            return constructor.newObject();
        else
            return objectStack.pop();
    }

    public void free(T object)
    {
        if(objectStack.size()<maxSize)
            objectStack.push(object);
    }
}