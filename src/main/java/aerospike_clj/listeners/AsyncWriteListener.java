package aerospike_clj.listeners;

import com.aerospike.client.listener.WriteListener;
import com.aerospike.client.Key;
import com.aerospike.client.AerospikeException;

import java.util.concurrent.CompletableFuture;

public class AsyncWriteListener implements WriteListener {
    private final CompletableFuture<Boolean> opFuture;

    public AsyncWriteListener(CompletableFuture opFuture) {
        this. opFuture = opFuture;
    }

    public void onFailure(AerospikeException exception) {
        opFuture.completeExceptionally(exception);
    }

    public void onSuccess(Key key) {
        opFuture.complete(true);
    }
}
