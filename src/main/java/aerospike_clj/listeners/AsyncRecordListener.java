package aerospike_clj.listeners;

import com.aerospike.client.listener.RecordListener;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.AerospikeException;

import java.util.concurrent.CompletableFuture;

public class AsyncRecordListener implements RecordListener {
    private final CompletableFuture<Record> opFuture;

    public AsyncRecordListener(CompletableFuture opFuture) {
        this. opFuture = opFuture;
    }

    public void onFailure(AerospikeException exception) {
        opFuture.completeExceptionally(exception);
    }

    public void onSuccess(Key key, Record record) {
        opFuture.complete(record);
    }
}
