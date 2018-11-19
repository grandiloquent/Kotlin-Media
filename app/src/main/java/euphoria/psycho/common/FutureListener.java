
package euphoria.psycho.common;

import euphoria.psycho.common.Future;

public interface FutureListener<T> {
    public void onFutureDone(Future<T> future);
}
