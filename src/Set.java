public interface Set<E> {

    /**
     * 添加元素
     * @param e
     */
    void add(E e);

    /**
     * 删除元素
     * @param e
     */
    void remove(E e);

    /**
     * 查询元素
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * 获取集合容量
     * @return
     */
    int getSize();

    /**
     * 查询是否为空
     * @return
     */
    boolean isEmpty();
}
