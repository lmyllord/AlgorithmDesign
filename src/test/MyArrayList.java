package test;

public class MyArrayList<E> extends MyAbstractList<E>
{

	@SuppressWarnings("unchecked")
	private E[] list = (E[]) new Object[5];
	
	public MyArrayList()
	{
		
	}
	
	public MyArrayList(E[] objects)
	{
		for(int i = 0; i < objects.length; i++)
		{
			add(objects[i]);
		}
	}
	
	public int getCapacitysize()
	{
		return list.length;
	}

	@Override
	public int size()
	{
		return size;
	}

	@SuppressWarnings("unchecked")
	private void isOutOfBound()
	{
		if(size >= list.length)
		{
			E[] newList = (E[]) (new Object[size * 2 + 1]);
			System.arraycopy(list, 0, newList, 0, size);
			list = newList;
		}
	}
	
	@Override
	public void add(int index, E e)
	{
		isOutOfBound();
		if(index>size){
			list[size]=e;
		}
		else{
			for(int i = size - 1; i >= index; i--)
			{
				list[i + 1] = list[i];
			}
			list[index] = e;
		}

		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear()
	{
		list = (E[])new Object[5];
		size = 0;
		
	}

	@Override
	public boolean contains(E e)
	{
		for(int i = 0; i < list.length; i++)
		{
			if(e.equals(list[i]))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public E get(int index)
	{
		if(index < size)
		{
			return list[index];
		}
		return null;
	}

	@Override
	public int indexOf(E e)//绗竴涓尮閰嶆墍浠ヤ粠鍓嶅線鍚庢壘
	{
		for(int i = 0; i < list.length; i++)
		{
			if(e.equals(list[i]))
			{
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E e)//鏈�悗涓�釜鍖归厤搴斾粠鍚庡線鍓嶆壘
	{
		for(int i = size - 1; i > -1; i--)
		{
			if(e.equals(list[i]))
			{
				return i;
			}
		}
		return -1;
	}

	@Override
	public E remove(int index)
	{
		E e = list[index];
		for(int i = index; i < size - 1; i++)
		{
			list[i] = list[i+1];
		}
		
		size--;
		/*@SuppressWarnings("unchecked")
		E[] newList = (E[]) (new Object[size]);
		System.arraycopy(list, 0, newList, 0, size);
		list = newList;*/
		return e;
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder();
		for(int i = 0;i < this.size(); i++)
		{
			result.append(this.get(i).toString() + " ");
		}
		return result.toString();
	}

	@Override
	public Object set(int index, E e)
	{
		if(index >=0 || index < size)
		{
			E e1 = list[index];
			list[index] = e;
			return e1;
		}
		return null;
	}
}