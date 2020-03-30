package com.hkunitedauction.auction.task;

import com.hkunitedauction.auction.model.Lot;
import com.hkunitedauction.auction.model.LotPO;
import com.hkunitedauction.auction.model.LotStatus;
import com.hkunitedauction.auction.service.LotService;
import com.hkunitedauction.common.response.QueryResult;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private LotService lotService;

    @Scheduled(fixedRate = 60000)
    public void scheduleLot(){
        openLot();
        closeLot();
    }

    private void openLot(){
        Date now = new Date();

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("status", LotStatus.PUBLISHED.getValue());
        criteria.andLessThan("openTime", now);

        QueryResult<Lot> result = this.lotService.query(example, new RowBounds(0, 1));
        for(Lot lot : result.getList()){
            Lot value = new Lot();
            value.setStatus(LotStatus.ONSALE);
            this.lotService.patch(lot.getId(), value);
        }
    }

    private void closeLot(){
        Date now = new Date();

        Example example = new Example(LotPO.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andEqualTo("status", LotStatus.ONSALE.getValue());
        criteria.andLessThan("deadline", now);

        QueryResult<Lot> result = this.lotService.query(example, new RowBounds(0, 1));
        for(Lot lot : result.getList()){
            Lot value = new Lot();
            value.setStatus(LotStatus.CLOSED);
            this.lotService.patch(lot.getId(), value);
        }
    }
}
