package com.dailu.springbootshardingjdbc.readwrite.algorithm.table;

import lombok.Getter;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistentHashAlgorithm {

    @Getter
    private SortedMap<Long, String> virtualNodes = new TreeMap<>();

    //当节点的数目很少时，容易造成数据的分布不均，所以增加虚拟节点来平均数据分布
    //虚拟节点的数目；虚拟节点的生成主要是用来让数据尽量均匀分布
    //虚拟节点是真实节点的不同映射而已
    //比如真实节点user1的hash值为100，那么我们增加3个虚拟节点user1-1、user1-2、user1-3，分别计算出来的hash值可能就为200，345，500；通过这种方式来将节点分布均匀
    //虚拟节点最小为1，为1时等于实际节点
    private static final int VIRTUAL_NODES = 1;


    public ConsistentHashAlgorithm() {
    }


    public ConsistentHashAlgorithm(SortedMap<Long, String> virtualTableNodes, Collection<String> tableNodes) {
        if (Objects.isNull(virtualTableNodes)) {
            virtualTableNodes = initNodesToHashLoop(tableNodes);
        }

        this.virtualNodes = virtualTableNodes;
    }

    //以虚拟节点名称的hash值作为key,value为虚拟节点名称
    public SortedMap<Long, String> initNodesToHashLoop(Collection<String> tableNodes) {
        SortedMap<Long, String> virtualTableNodes = new TreeMap<>();
        for (String node : tableNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = node + "-VN" + i;
                long hash = getHash(virtualNodeName);
                virtualTableNodes.put(hash, virtualNodeName);
            }
        }
        return virtualTableNodes;
    }

    /**
     * 通过计算key的hash
     * 计算映射的表节点
     */
    public String getTableNode(String key) {
        String virtualNode = getVirtualTableNode(key);
        //虚拟节点名称截取后获取真实节点
        if (!ObjectUtils.isEmpty(virtualNode)) {
            return virtualNode.substring(0, virtualNode.indexOf("-"));
        }
        return null;
    }

    /**
     * 获取虚拟节点
     */
    public String getVirtualTableNode(String key) {
        long hash = getHash(key);
        // 得到大于该Hash值的所有Map
        SortedMap<Long, String> subMap = virtualNodes.tailMap(hash);
        if (!subMap.isEmpty()) {
            //第一个Key就是顺时针过去离node最近的那个结点
            Long i = subMap.firstKey();
            //返回对应的服务器
            return subMap.get(i);
        }
        //取virtualNodes第一个节点
        Long i = virtualNodes.firstKey();
        return virtualNodes.get(i);
    }

    /**
     * 使用FNV1_32_HASH算法计算key的Hash值
     */
    public static long getHash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // 如果算出来的值为负数则取其绝对值
        return Math.abs(hash);
    }

    public static void main(String[] args) {
        System.out.println(getHash("key2022-04-07"));
    }
}
