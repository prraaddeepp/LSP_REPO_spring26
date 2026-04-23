Part 1:

Shared Resource #1:
nextId

Shared Resource #2:
requests

Concurrency Problem:
A race condition may occur when multiple threads access and modify nextId and requests at the same time. This can cause duplicate request IDs, skipped IDs, or inconsistent updates to the list.

Why addRequest() is unsafe:
addRequest() is unsafe because it calls getNextId() and then adds to the shared ArrayList without synchronization. If two threads call addRequest() at the same time, they may interfere with each other while updating nextId or modifying the requests list.

Part 2:

Fix A:

This fix does not fully solve the concurrency problem. It protects nextId, so duplicate IDs are prevented, but requests is still an unsynchronized ArrayList. Multiple threads can still modify the list at the same time, which may cause data corruption or inconsistent behavior.

Fix B:

This fix correctly solves the concurrency problem for adding requests. Synchronizing addRequest() ensures that only one thread at a time can get an ID and add the request to the list. This protects both nextId and requests during the full operation.

Fix C:

This fix does not solve the concurrency problem. It only synchronizes access when returning the list, but it does not protect getNextId() or requests.add() inside addRequest(). Also, returning the actual list exposes the internal shared list to outside modification.

Part 3:

No, getNextId() should not be public. Based on Arthur Riel’s heuristics, classes should hide internal implementation details and expose only necessary behavior. The request ID is an internal detail of RequestManager, so outside classes should not directly call getNextId(). Instead, they should use addRequest(), which represents the meaningful public behavior.

Part 4:

Description:
An alternative approach is to use a Lock, such as ReentrantLock, instead of the synchronized keyword. The lock is acquired before accessing the shared resources and released in a finally block to guarantee that the lock is released even if an exception occurs.

Code Snippet:
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

private final Lock lock = new ReentrantLock();

public void addRequest(String studentName) {
    lock.lock();
    try {
        int id = nextId++;
        String request = "Request-" + id + " from " + studentName;
        requests.add(request);
    } finally {
        lock.unlock();
    }
}