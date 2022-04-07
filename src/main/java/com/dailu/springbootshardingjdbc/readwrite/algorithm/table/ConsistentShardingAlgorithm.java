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

public class ConsistentShardingAlgorithm
        implements PreciseShardingAlgorithm<Long>, RangeShardingAlgorithm<Long> {

    /**
     * 精确分片
     * 一致性hash算法
     */
    @Override
    public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Long> shardingValue) {

		//获取已经初始化的分表节点
        InitTableNodesToHashLoop initTableNodesToHashLoop =
                SpringContextUtils.getBean(InitTableNodesToHashLoop.class);
        if (CollectionUtils.isEmpty(availableTargetNames)) {
            return shardingValue.getLogicTableName();
        }

        //这里主要为了兼容当联表查询时，如果两个表非关联表则
        //当对副表分表时shardingValue这里传递进来的依然是主表的名称，
        //但availableTargetNames中确是副表名称，所有这里要从availableTargetNames中匹配真实表
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
