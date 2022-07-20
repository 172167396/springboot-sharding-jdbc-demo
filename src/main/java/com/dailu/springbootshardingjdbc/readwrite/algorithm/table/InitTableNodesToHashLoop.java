package com.dailu.springbootshardingjdbc.readwrite.algorithm.table;

import io.shardingsphere.core.rule.*;
import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class InitTableNodesToHashLoop {

    @Resource
    DataSource dataSource;

    @Getter
    private final HashMap<String, SortedMap<Long, String>> tableVirtualNodes = new HashMap<>();

    @Getter
    private final HashMap<String, SortedMap<Long, String>> datasourceVirtualNodes = new HashMap<>();

    @PostConstruct
    public void init() {
        try {
            ShardingRule rule = ((ShardingDataSource) dataSource).getShardingContext().getShardingRule();
            Collection<TableRule> tableRules = rule.getTableRules();
            //datasourceVirtualNodes的key为ds_,value为datasource的hash环,待实现
            Collection<MasterSlaveRule> masterSlaveRules = rule.getMasterSlaveRules();
            ConsistentHashAlgorithm consistentHashAlgorithm = new ConsistentHashAlgorithm();
            for (TableRule tableRule : tableRules) {
                String logicTable = tableRule.getLogicTable();
                tableVirtualNodes.put(logicTable,
                        consistentHashAlgorithm.initNodesToHashLoop(
                                tableRule.getActualDataNodes()
                                        .stream()
                                        .map(DataNode::getTableName)
                                        .collect(Collectors.toSet()))
                );
            }
        } catch (Exception e) {
            log.error("分表节点初始化失败 {}", e.getMessage(), e);
        }
    }

}
