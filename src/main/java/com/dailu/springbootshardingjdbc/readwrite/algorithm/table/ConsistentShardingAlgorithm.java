package com.dailu.springbootshardingjdbc.readwrite.algorithm.table;

import com.dailu.springbootshardingjdbc.readwrite.utils.SpringContextUtils;
import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;

/**
 * 感觉database和table的可以分开，用一个类没法区分是对哪个分片
 */
@SuppressWarnings("unused")
public class ConsistentShardingAlgorithm
        implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    /**
     * 精确分片
     * 一致性hash算法
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

        InitTableNodesToHashLoop initTableNodesToHashLoop =
                SpringContextUtils.getBean(InitTableNodesToHashLoop.class);
        if (CollectionUtils.isEmpty(availableTargetNames)) {
            return shardingValue.getLogicTableName();
        }
        ArrayList<String> availableTargetNameList = new ArrayList<>(availableTargetNames);
        String logicTableName = availableTargetNameList.get(0).replaceAll("[^(a-zA-Z_)]", "");
        //获取初始化的节点
        SortedMap<Long, String> tableHashNode =
                initTableNodesToHashLoop.getTableVirtualNodes().get(logicTableName);
        //初始化节点为空时，构造对象时重新初始化一次
        ConsistentHashAlgorithm consistentHashAlgorithm = new ConsistentHashAlgorithm(tableHashNode,
                availableTargetNames);
        return consistentHashAlgorithm.getTableNode(String.valueOf(shardingValue.getValue()));
    }

    /**
     * 范围查询规则
     * 可以根据实际场景进行修改
     * Sharding.
     *
     * @param availableTargetNames available data sources or tables's names
     * @param shardingValue        sharding value
     * @return sharding results for data sources or tables's names
     */
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, RangeShardingValue<Long> shardingValue) {
        return availableTargetNames;
    }
}
