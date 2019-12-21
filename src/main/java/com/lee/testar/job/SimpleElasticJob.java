package com.lee.testar.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleElasticJob
 *
 * @author Lee
 * @date 2019/12/21
 */
public class SimpleElasticJob implements SimpleJob {
    Logger logger = LoggerFactory.getLogger(SimpleElasticJob.class);

    @Override
    public void execute(ShardingContext shardingContext) {

        Long id = Thread.currentThread().getId();
        String sharding = shardingContext.getShardingParameter().toString();
        logger.info("I get : {}", sharding);
        logger.info("end");
    }
}
