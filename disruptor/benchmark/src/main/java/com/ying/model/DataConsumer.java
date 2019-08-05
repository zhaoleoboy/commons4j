package com.ying.model;


import com.lmax.disruptor.EventHandler;
import com.ying.Constants;

public class DataConsumer implements EventHandler<Data> {

    private long startTime;
    private int i;

    public DataConsumer() {
        this.startTime = System.currentTimeMillis();
    }

    @Override
    public void onEvent(Data data, long l, boolean b) throws Exception {
        i++;
        if(i == Constants.EVENT_NUM_OHM){
            long endTime = System.currentTimeMillis();
            System.out.println("cost time  = " + (endTime - startTime));
        }
    }
}
