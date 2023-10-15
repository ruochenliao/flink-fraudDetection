package frauddetection;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

import java.util.List;

public class MyTransactionSource implements SourceFunction<MyTransaction> {

    private volatile boolean running = true;
    private final List<MyTransaction> transactionList;

    public MyTransactionSource(List<MyTransaction> transactionList){
        this.transactionList = transactionList;
    }

    @Override
    public void run(SourceContext<MyTransaction> sourceContext) throws Exception {
        for(MyTransaction transaction:transactionList){
            sourceContext.collect(transaction);
            //每一秒发送一个 transaction 对象
            Thread.sleep(1000);
            if(!running){
                break;
            }
        }
    }

    @Override
    public void cancel() {
        running = false;
    }
}
