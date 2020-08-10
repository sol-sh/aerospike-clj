package aerospike_clj.listeners;

import com.aerospike.client.listener.ExistsListener;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.AerospikeException;

import java.util.concurrent.CompletableFuture;

public class AsyncExistsListener implements ExistsListener {
    private final CompletableFuture<Boolean> opFuture;

    public AsyncExistsListener(CompletableFuture opFuture) {
        this. opFuture = opFuture;
    }

    public void onFailure(AerospikeException exception) {
        opFuture.completeExceptionally(exception);
    }

    public void onSuccess(Key key, boolean exists) {
        opFuture.complete(exists);
    }
}
