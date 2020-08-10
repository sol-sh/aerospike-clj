package aerospike_clj.listeners;

import com.aerospike.client.listener.DeleteListener;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.AerospikeException;

import java.util.concurrent.CompletableFuture;

public class AsyncDeleteListener implements DeleteListener {
    private final CompletableFuture<Boolean> opFuture;

    public AsyncDeleteListener(CompletableFuture opFuture) {
        this. opFuture = opFuture;
    }

    public void onFailure(AerospikeException exception) {
        opFuture.completeExceptionally(exception);
    }

    public void onSuccess(Key key, boolean existed) {
        opFuture.complete(existed);
    }
}
