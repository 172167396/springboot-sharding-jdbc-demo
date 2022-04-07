package com.dailu.springbootshardingjdbc.readwrite.algorithm.table;

import io.shardingsphere.core.rule.DataNode;
import io.shardingsphere.core.rule.ShardingRule;
import io.shardingsphere.core.rule.TableRule;
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

    @PostConstruct
    public void init() {
        try {
            ShardingRule rule = ((ShardingDataSource) dataSource).getShardingContext().getShardingRule();
            Collection<TableRule> tableRules = rule.getTableRules();
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
