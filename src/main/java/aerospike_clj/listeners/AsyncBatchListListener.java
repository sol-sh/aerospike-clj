package aerospike_clj.listeners;

import com.aerospike.client.listener.BatchListListener;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.BatchRead;
import com.aerospike.client.AerospikeException;

import java.util.concurrent.CompletableFuture;
import java.util.List;

public class AsyncBatchListListener implements BatchListListener {
    private final CompletableFuture<List<BatchRead>> opFuture;

    public AsyncBatchListListener(CompletableFuture opFuture) {
        this. opFuture = opFuture;
    }

    public void onFailure(AerospikeException exception) {
        opFuture.completeExceptionally(exception);
    }

    public void onSuccess(List<BatchRead> records) {
        opFuture.complete(records);
    }
}
