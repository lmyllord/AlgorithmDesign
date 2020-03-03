package test;


public class MyLinkedList<E> extends MyAbstractList<E>
{
	private Node<E> head = new Node<E>();
	private Node<E> tail;

	public MyLinkedList()
	{
	}

	public MyLinkedList(E[] e)
	{
		super(e);
	}

	public E getFirst()
	{
		if (size == 0)
		{
			return null;
		} else
		{
			return head.next.element;
		}
	}

	public E getLast()
	{
		if (size == 0)
		{
			return null;
		} else
		{
			return tail.element;

		}
	}

	public void addFirst(E e)
	{
		Node<E> newNode = new Node<E>(e);
		if (size != 0)
		{
			newNode.next = head.next;
			head.next = newNode;
			size++;
		} else
		{
			head.next = newNode;
			tail = newNode;
			size++;
		}
	}

	public void addLast(E e)
	{
		if (size == 0)
		{
			addFirst(e);
		} else
		{
			tail.next = new Node<E>(e);
			tail = tail.next;
			size++;
		}
	}

	@Override
	public void add(int index, E e)
	{
		if (index == 0)
		{
			addFirst(e);
		} else if (index >= size)
		{
			addLast(e);
		} else
		{
			Node<E> current = head.next;

			for (int i = 1; i < index; i++)
			{
				current = current.next;
			}

			Node<E> latter = current.next;
			current.next = new Node<E>(e);
			(current.next).next = latter;
			size++;
		}

	}

	public E reomveFirst()
	{
		if (size == 0)
		{
			return null;
		} else
		{
			if (size == 1)
			{
				E temp = head.element;
				tail = null;
				head.next = null;
				size--;
				return temp;
			} else
			{
				Node<E> old = head.next;
				head.next = (head.next).next;
				size--;
				return old.element;
			}
		}
	}

	public E removeLast()
	{
		if (size == 0)
		{
			return null;
		} else
		{
			if (size == 1)
			{
				Node<E> temp = head.next;
				tail = null;
				head.next = null;
				size = 0;
				return temp.element;
			} else
			{
				Node<E> before = head.next;
				for (int i = 0; i < size - 2; i++)
				{
					before = before.next;
				}
				E temp = tail.element;
				tail = before;
				tail.next = null;
				size--;
				return temp;
			}
		}
	}

	@Override
	public E remove(int index)
	{
		if (index < 0 || index > size - 1)
		{
			return null;
		} else
		{
			if (index == 0)
			{
				return reomveFirst();
			}
			if (index == size - 1)
			{
				return removeLast();
			}
			Node<E> before = head.next;
			Node<E> current;

			for (int i = 1; i < index; i++)
			{
				before = before.next;
			}

			current = before.next;
			before.next = current.next;
			size--;
			return current.element;
		}
	}
	
	public String toString()
	{
		StringBuilder result = new StringBuilder("[ ");
		Node<E> current = head.next;

		for (int i = 0; i < size; i++)
		{
			result.append(current.element);
			current = current.next;
			if (current != null)
			{
				result.append(", ");
			} else
			{
				result.append("]");
			}
		}
		return result.toString();
	}

	@Override
	public void clear()
	{
		tail = null;
		head.next = null;
		size = 0;
	}

	@Override
	public boolean contains(E e)
	{
		Node<E> current = head.next;

		for (int i = 1; i < size - 1; i++)
		{
			if (current.element.equals(e))
			{
				return true;
			}
			current = current.next;
		}
		return false;
	}

	@Override
	public E get(int index)
	{
		if (index < 0 || index > size - 1)
		{
			return null;
		} else
		{
			if (index == 0)
			{
				return head.next.element;
			}

			if (index == size - 1)
			{
				return tail.element;
			}

			Node<E> current = head.next;
			for (int i = 0; i < index; i++)
			{
				current = current.next;
			}

			return current.element;
		}
	}

	@Override
	public int indexOf(E e)
	{
		Node<E> current = head.next;

		for (int i = 0; i < size; i++)
		{
			if (e.equals(current.element))
			{
				return i;
			}
			if (current.next != null)
			{
				current = current.next;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(E e)
	{
		int index = -1;
		Node<E> current = head.next;

		for (int i = 0; i < size; i++)
		{
			if (e.equals(current.element))
			{
				index = i;
			}
			if (current.next != null)
			{
				current = current.next;
			}
		}
		return index;
	}

	@Override
	public Object set(int index, E e)
	{
		Node<E> current = head.next;

		if (index < 0 || index > size)
		{
			return null;
		}

		for (int i = 0; i < index; i++)
		{
			current = current.next;
		}
		E temp;
		temp = current.element;
		current.element = e;

		return temp;
	}

}
