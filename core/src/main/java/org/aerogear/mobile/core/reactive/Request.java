package org.aerogear.mobile.core.reactive;

import java.util.concurrent.ExecutorService;

/**
 * This interface describes an implementation of the Single Observable Reactive pattern.
 * http://reactivex.io/documentation/single.html
 */
public interface Request<T> {


    /**
     * This configures a handler that will process responses from a request. The underlying request
     * object will begin processing its request when this method is invoked. You may cancel the
     * request with {@link #cancel()}, or you may disconnect the responder with
     * {@link #disconnect(Responder)}.
     *
     * @param responder a responder
     * @return a chainable instance of Request, not guaranteed to be the this reference
     */
    Request<T> respondWith(Responder<T> responder);

    /**
     * Requests can be run off the calling thread. This method configures that.
     *
     * @param executorService service to Run the thread on
     * @return a chainable instance of Request, not guaranteed to be the this reference
     */
    Request<T> requestOn(ExecutorService executorService);

    /**
     * Requests may be asynchronous and need to be cancelled.
     */
    void cancel();

    /**
     * This tells the Request chain to cache its value and not to rerun any underlying generating
     * function.
     *
     * @return a chainable instance of Request, not guaranteed to be the this reference
     */
    Request<T> cache();

    /**
     * Remove a responder from the request. If the request has already been run for this responder
     * then we will do nothing.
     *
     * @param responderToDisconnect The responder to be disconnected
     * @return a request instance that represents the request the parameter was disconnected from.
     */
    Request<T> disconnect(Responder<T> responderToDisconnect);


    /**
     * Responses can be run off the calling thread. This method configures that.
     *
     * @param executorService service to Run the thread on
     * @return a chainable instance of Request, not guaranteed to be the this reference
     */
    Request<T> respondOn(ExecutorService executorService);
}
