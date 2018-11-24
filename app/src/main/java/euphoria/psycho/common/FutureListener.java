
package euphoria.psycho.common;

public interface FutureListener<T> {
    public void onFutureDone(Future<T> future);
}
