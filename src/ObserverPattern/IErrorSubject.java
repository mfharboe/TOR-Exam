package ObserverPattern;

public interface IErrorSubject {
    public void register(IErrorObserver o);
    public void unregister(IErrorObserver o);
    public void notifyObservers();
}
