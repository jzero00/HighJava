package a_2_hw;

import java.util.ArrayList;
import java.util.List;

public class CollectionInterface {
	/*
	 * boolean	add(E e)
	 * 
	 * void	add(int index, E element)
	 * 
	 * boolean	addAll(Collection<? extends E> c)
	 * 
	 * boolean	addAll(int index, Collection<? extends E> c)
	 * 
	 * void	clear()
	 * 
	 * Object	clone()
	 * 
	 * boolean	contains(Object o)
	 * 
	 * void	ensureCapacity(int minCapacity)
	 * 
	 * void	forEach(Consumer<? super E> action)
	 * 
	 * E	get(int index)
	 * 
	 * int	indexOf(Object o)
	 * 
	 * boolean	isEmpty()
	 * 
	 * Iterator<E>	iterator()
	 * 
	 * int	lastIndexOf(Object o)
	 * 
	 * ListIterator<E>	listIterator()
	 * 
	 * ListIterator<E>	listIterator(int index)
	 * 
	 * E	remove(int index)
	 * 
	 * boolean	remove(Object o)
	 * 
	 * boolean	removeAll(Collection<?> c)
	 * 
	 * boolean	removeIf(Predicate<? super E> filter)
	 * 
	 * protected void	removeRange(int fromIndex, int toIndex)
	 * 
	 * void	replaceAll(UnaryOperator<E> operator)
	 * 
	 * boolean	retainAll(Collection<?> c)
	 * 
	 * E	set(int index, E element)
	 * 
	 * int	size()
	 * 
	 * void	sort(Comparator<? super E> c)
	 * 
	 * Spliterator<E>	spliterator()
	 * 
	 * List<E>	subList(int fromIndex, int toIndex)
	 * 
	 * Object[]	toArray()
	 * 
	 * <T> T[]	toArray(T[] a)
	 * 
	 * void	trimToSize()
	 */
	public static void main(String[] args) {
		List<Object> list = new ArrayList<>();
		list.add(1);
		System.out.println(list);
		list.add(0, "Hi");
		System.out.println(list);
		list.addAll(list);
		System.out.println(list);
		list.addAll(0, list);
		System.out.println(list);
		list.clear();
		System.out.println(list);
		list.contains(list);
		System.out.println(list);
		list.add(2);
		System.out.println(list);
//		list.
		
		
		
	}
}
