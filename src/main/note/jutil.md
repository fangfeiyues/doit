基础源码
  (数组：HashMap, ConcurrentHashMap, TreeMap, ReentrantLock, AtmoInteger, PriorityQueue小顶堆, ConcurrentLinkedQueue, 并发工具)
  
---
### 1.HashMap 
http://www.tianxiaobo.com/2018/01/18/HashMap-%E6%BA%90%E7%A0%81%E8%AF%A6%E7%BB%86%E5%88%86%E6%9E%90-JDK1-8/

#### 1.构造参数
> 1. loadFactor       负载因子 = 0.75f,用来间接设置Entry数组的内存空间。值过大导致链表太长查询效率越低过于稀疏则浪费
> 2. initialCapacity  初始容量 = 16(始终在2的幂次方), 没有全局变量也不是在构造函数初始化的 
> 3. threshold        阈值 = capacity * loadFactor，当前 HashMap 所能容纳键值对数量的最大值，超过这个值则需扩容
> = tableSizeFor(initialCapacity) 不断的或运算 找到大于或等于 cap 的最小2的幂

#### 2.查询
取出桶的位置：first = tab[(n - 1) & hash] 等价于hash对length取余
链表查询 / 红黑树查询

#### 3.遍历
输出顺序一致却与插入的不同：数组桶的顺序遍历

#### 4.插入

**扩容**
> 1. table在已经初始化过情况下 若大于最大值了即不再扩容否则 newCap，newThr 各扩大一倍
> 2. oldThr > 0 即无参初始化HahMap() 下 newCap = oldThr
> 3. newCap = 16；newThr = DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY = 12 
> 4. 嵌套分支在计算过程中导致 newThr 溢出归零 newThr == 0 重新计算 newThr = newCap * loadFactor

**迁移**
> 1. 如果是 e.hash & (newCap - 1) 头结点则直接放入
> 2. 如果树节点TreeNode 则split()
> 3. 链表 因为扩容一倍hash计算结果分为lohead-lotail和hihead-hitail两条且排序正常

JDK 1.7 为了防止因 hash 碰撞引发的拒绝服务攻击，在计算 hash 过程中引入随机种子。以增强 hash 的随机性，使得键值对均匀分布在桶数组中。
在扩容过程中，相关方法会根据容量判断是否需要生成新的随机种子，并重新计算所有节点的 hash。而在 JDK 1.8 中，则通过引入红黑树替代了该种方式。
从而避免了多次计算 hash 的操作，提高了扩容效率。

#### 4.红黑树(VS 平衡二叉树)：
  拆分 红黑树转链表阈值 UNTREEIFY_THRESHOLD = 6;
      和拆分链表一样 (e.hash & bit) == 0 分成两条链因为node还保持着next

---

#### 2.HashSet 



    
---
#### 3.TreeMap



---
#### 4.LinkedHashMap
> 同时满足散列和保证顺序
















